package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.db.DbItem;

import java.util.List;

public class Item {

    private String name;
    private String type;
    private String description;
    private int id;
    private int price;
    private int quantity;

    protected Item(String name, String type, String description, int id, int price, int quantity) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    static public List<Item> searchItems(){
        return DbItem.searchItems();
    }

    public static boolean addItemToStock(String name, String type, String description, int price, int quantity) {
        return DbItem.addItemToStock(name, type, description, price, quantity);
    }

    public static boolean editItem(int itemId, String name, String type, String description, int price, int quantity) {
       return DbItem.editItem(itemId, name, type,description,price,quantity);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
