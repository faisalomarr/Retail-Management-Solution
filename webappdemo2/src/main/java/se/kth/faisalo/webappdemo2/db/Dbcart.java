package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Cart;
import se.kth.faisalo.webappdemo2.bo.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dbcart extends Cart {


    protected Dbcart(int cartId, int userId, Status status, Packedstatus packedstatus) {
        super(cartId, userId, status, packedstatus);
    }

    public static List<Item> getItemsInCart(int cartId) {
        List<Item> cart = new ArrayList<Item>();

        try {
            Connection connection = DbManager.getConnection();

            // Prepare query to retrieve all items associated with the cartId
            String query = "SELECT i.idItem, i.Itemname, i.Itemprice, i.Itemdescription, i.Itemtype, i.quantity " +
                    "FROM item i " +
                    "JOIN cart_item ci ON i.idItem = ci.idItem " +
                    "WHERE ci.idcart = " + cartId;
            Statement statement = connection.createStatement();

            // Execute query and process the result set
            ResultSet set = statement.executeQuery(query);

            // Iterate over the result set and build the list of items in the cart
            while (set.next()) {
                int id = set.getInt("idItem");
                String name = set.getString("Itemname");
                int price = set.getInt("Itemprice");
                String description = set.getString("Itemdescription");
                String type = set.getString("Itemtype");
                int quantity = set.getInt("quantity");

                // Add each item to the cart list
                cart.add(new DbItem(name, type, description, id, price, quantity));
            }

        } catch (SQLException e) {
            // Handle any SQL errors during the query execution
            e.printStackTrace();
            throw new RuntimeException("Error retrieving items in cart", e);
        }

        // Return the list of items in the cart
        return cart;
    }


    public static int getCartIdForUser(int userId) {
        int cartId = -1;

        try {
            Connection connection = DbManager.getConnection();

            // Check if the user has an active cart
            String query = "SELECT idcart FROM cart WHERE iduser = ? AND status = 'active'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // User has an active cart, return the cart_id
                cartId = resultSet.getInt("idcart");
            } else {
                // No active cart found, create a new cart
                String insertCartQuery = "INSERT INTO cart (iduser, status) VALUES (?, 'active')";
                PreparedStatement insertStatement = connection.prepareStatement(insertCartQuery, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setInt(1, userId);

                int rowsInserted = insertStatement.executeUpdate();

                if (rowsInserted > 0) {
                    // Get the generated cart_id for the newly created cart
                    ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        cartId = generatedKeys.getInt(1);  // Get the new cart_id
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving or creating cart for user", e);
        }

        return cartId;
    }


    public static boolean addToCart(int cartId, int itemId) {
        try {
            // Get database connection
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();

            // SQL query to insert the item into the cart
            String query = "INSERT INTO cart_item (idcart, idItem) VALUES (" + cartId + ", " + itemId + ")";

            // Execute the query
            int rowsAffected = statement.executeUpdate(query);

            // If rowsAffected > 0, it means the insert was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        }
    }

    public static boolean purchaseItemsInCart(int cartId) {
        Connection connection = null;
        try {
            connection = DbManager.getConnection();
            connection.setAutoCommit(false);  // Begin transaction

            // Get all item IDs in the cart
            String getItemsQuery = "SELECT idItem FROM cart_item WHERE idcart = ?";
            PreparedStatement getItemsStmt = connection.prepareStatement(getItemsQuery);
            getItemsStmt.setInt(1, cartId);
            ResultSet rs = getItemsStmt.executeQuery();

            // Finalize the cart by updating the status
            String finalizeCart = "UPDATE cart SET status = 'finalized' WHERE idcart = ?";
            PreparedStatement finalizeStmt = connection.prepareStatement(finalizeCart);
            finalizeStmt.setInt(1, cartId);  // Set the cart ID

            // Execute the update (since we are modifying data, not retrieving it)
            int cartRowsAffected = finalizeStmt.executeUpdate();

            if (cartRowsAffected == 0) {
                System.out.println("Failed to finalize cart. Cart may not exist.");
                connection.rollback();  // Rollback since the cart wasn't finalized
                return false;
            }

            // Prepare update query to reduce item quantity
            String updateItemQuery = "UPDATE item SET quantity = quantity - 1 WHERE idItem = ? AND quantity > 0";
            PreparedStatement updateItemStmt = connection.prepareStatement(updateItemQuery);

            // Process each item in the cart
            while (rs.next()) {
                int itemId = rs.getInt("idItem");

                // Set the itemId for the update query and execute it
                updateItemStmt.setInt(1, itemId);
                int itemRowsAffected = updateItemStmt.executeUpdate();

                // If no rows were affected, it means the item is out of stock or does not exist
                if (itemRowsAffected == 0) {
                    System.out.println("Item " + itemId + " is out of stock or not available.");
                    connection.rollback();  // Rollback the transaction if any item is out of stock
                    return false;
                }
            }

            connection.commit();  // Commit the transaction if all updates were successful
            System.out.println("Purchase successful and cart finalized.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();  // Rollback the transaction in case of an error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;

        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true);  // Reset auto-commit to true
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


    }



    public static List<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        try {
            Connection connection = DbManager.getConnection();

            // SQL query to get all carts
            String query = "SELECT idcart, iduser, status, packedstatus FROM cart";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process each cart in the result set
            while (resultSet.next()) {
                int cartId = resultSet.getInt("idcart");
                int userId = resultSet.getInt("iduser");
                String statusString = resultSet.getString("status");
                String packedstatusString = resultSet.getString("packedstatus");

                Cart.Status status = Cart.Status.valueOf(statusString.toUpperCase());
                Cart.Packedstatus packedstatus = Cart.Packedstatus.valueOf(packedstatusString.toUpperCase());

                // Create a new Cart object and add it to the list
                Cart cart = new Dbcart(cartId, userId, status,packedstatus);
                carts.add(cart);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return carts;  // Return the list of all carts
    }


    public static boolean updatePackedStatus(int cartId) {
        try {
            // Get database connection
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();

            // SQL query to update packedstatus to 'PACKED'
            String query = "UPDATE cart SET packedstatus = 'PACKED' WHERE idcart = " + cartId;

            // Execute the query
            int rowsAffected = statement.executeUpdate(query);

            // If rowsAffected > 0, it means the update was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        }
    }





}


