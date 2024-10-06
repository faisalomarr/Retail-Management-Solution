package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbItem extends Item {

    protected DbItem(String name, String type, String description, int id, int price, int quantity) {
        super(name, type, description, id, price, quantity);
    }

    public static List<Item> searchItems() {
        List<Item> items = new ArrayList<Item>();

        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();

            // Execute a query to retrieve all items from the "item" table
            ResultSet set = statement.executeQuery("SELECT * FROM item");

            // Loop through the result set and process each row
            while (set.next()) {
                // Retrieve item details from the current row
                int id = set.getInt("idItem");
                String name = set.getString("Itemname");
                int price = set.getInt("Itemprice");
                String description = set.getString("Itemdescription");
                String type = set.getString("Itemtype");
                int quantity = set.getInt("quantity");

                // Add the retrieved item to the list of items
                items.add(new DbItem(name, type, description, id, price, quantity));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving items from the database", e);
        }

        // Return the list of items retrieved from the database
        return items;
    }

    public static boolean addItemToStock(String name, String type, String description, int price, int quantity) {
        try {
            // Get the database connection
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();

            // SQL query for inserting a new item
            String query = "INSERT INTO item (Itemname, Itemtype, Itemdescription, Itemprice, quantity) " +
                    "VALUES ('" + name + "', '" + type + "', '" + description + "', " + price + ", " + quantity + ")";

            // Execute the query and check how many rows were affected
            int rowsAffected = statement.executeUpdate(query);

            // Return true if at least one row was inserted, indicating success
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Print the error and return false in case of an exception
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editItem(int itemId, String name, String type, String description, int price, int quantity) {
        try {
            // Get the database connection
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();

            // SQL query for updating an existing item
            String query = "UPDATE item SET Itemname = '" + name + "', Itemtype = '" + type + "', Itemdescription = '" + description +
                    "', Itemprice = " + price + ", quantity = " + quantity + " WHERE idItem = " + itemId;

            // Execute the query and check how many rows were affected
            int rowsAffected = statement.executeUpdate(query);

            // Return true if at least one row was updated
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Print the error and return false in case of an exception
            e.printStackTrace();
            return false;
        }
    }

}
