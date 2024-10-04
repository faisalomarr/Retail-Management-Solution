package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Cart;
import se.kth.faisalo.webappdemo2.bo.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dbcart extends Cart {
    protected Dbcart(int cartId, List<Item> cartList) {
        super(cartId, cartList);
    }

    public static List<Item> getItemsInCart(int cartId) {
        List<Item> cart = new ArrayList<Item>();
        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();

            String query = "SELECT i.idItem, i.Itemname, i.Itemprice, i.Itemdescription, i.Itemtype, i.quantity " +
                    "FROM item i " +
                    "JOIN cart_item ci ON i.idItem = ci.idItem " +
                    "WHERE ci.idcart = " + cartId;

            ResultSet set = statement.executeQuery(query);

            while (set.next()) {
                int id = set.getInt("idItem");
                String name = set.getString("Itemname");
                int price = set.getInt("Itemprice");
                String description = set.getString("Itemdescription");
                String type = set.getString("Itemtype");
                int quantity = set.getInt("quantity");

                cart.add(new DbItem(name, type, description, id, price,quantity));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cart;
    }

    public static int getCartIdForUser(int userId) {
        int cartId = -1;

        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT idcart FROM cart WHERE iduser = " + userId;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                cartId = resultSet.getInt("idcart");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

            // Prepare update query to reduce item quantity
            String updateItemQuery = "UPDATE item SET quantity = quantity - 1 WHERE idItem = ? AND quantity > 0";
            PreparedStatement updateItemStmt = connection.prepareStatement(updateItemQuery);

            // Process each item
            while (rs.next()) {
                int itemId = rs.getInt("idItem");

                // Set the itemId for the update query and execute it
                updateItemStmt.setInt(1, itemId);
                int rowsAffected = updateItemStmt.executeUpdate();

                // If no rows were affected, it means the item is out of stock
                if (rowsAffected == 0) {
                    connection.rollback();  // Rollback the transaction if any item is out of stock
                    return false;
                }
            }

            connection.commit();  // Commit the transaction if all updates were successful
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

}

