package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Cart;
import se.kth.faisalo.webappdemo2.bo.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            String query = "SELECT i.idItem, i.Itemname, i.Itemprice, i.Itemdescription, i.Itemtype " +
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

                cart.add(new DbItem(name, type, description, id, price));
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
}

