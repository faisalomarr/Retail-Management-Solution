package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.db.DbItem;

import java.util.List;

public class Item {

    private String name;
    private String type;
    private String description;
    private int id;
    private int price;

    protected Item(String name, String type, String description, int id, int price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.id = id;
        this.price = price;
    }

    static public List<Item> searchItems(){
        return DbItem.searchItems();
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
}
