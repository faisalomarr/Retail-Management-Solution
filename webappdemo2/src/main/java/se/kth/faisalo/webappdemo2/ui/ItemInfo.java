package se.kth.faisalo.webappdemo2.ui;

public class ItemInfo {
    private String name;
    private String description;
    private int id;
    private int quantity;

    private int price;
    private String type;
    public ItemInfo(String name, String description, int id, int quantity, int price, String type) {
        this.name=name;
        this.description=description;
        this.id=id;
        this.quantity=quantity;
        this.price=price;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}