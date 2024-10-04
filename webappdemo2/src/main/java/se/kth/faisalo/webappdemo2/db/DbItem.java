package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbItem extends Item {

    protected DbItem(String name, String type, String description, int id, int price, int quantity) {
        super(name, type, description, id, price, quantity);
    }

    public static List<Item> searchItems(){
        List<Item> items = new ArrayList<Item>();
        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from item");
            while (set.next()) {
                int id = set.getInt("idItem");
                String name = set.getString("Itemname");
                int price = set.getInt("Itemprice");
                String description = set.getString("Itemdescription");
                String type = set.getString("Itemtype");
                int quantity = set.getInt("quantity");
                items.add(new DbItem(name,type,description,id,price,quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }











}
