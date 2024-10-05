package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.db.Dbcart;

import java.util.List;

public class Cart {
    private int cartId;
    private int userId;
    private Status status;
    private Packedstatus packedstatus;
    private List<Item> cartList;

    // Enum to represent the status of the cart
    public enum Status {
        ACTIVE,
        FINALIZED
    }

    public enum Packedstatus {
        PACKED,
        UNPACKED
    }

    // Constructor
    protected Cart(int cartId, int userId, Status status, Packedstatus packedstatus) {
        this.cartId = cartId;
        this.userId = userId;
        this.status = status;
        this.packedstatus = packedstatus;
    }

    // Getters and setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Item> getCartList() {
        return cartList;
    }

    public void setCartList(List<Item> cartList) {
        this.cartList = cartList;
    }

    public Packedstatus getPackedstatus() {
        return packedstatus;
    }

    public void setPackedstatus(Packedstatus packedstatus) {
        this.packedstatus = packedstatus;
    }

    // Static methods for interacting with the database via Dbcart
    public static List<Item> getItemsInCart(int cartId) {
        return Dbcart.getItemsInCart(cartId);
    }

    public static int getCartIdFromUser(int userId) {
        return Dbcart.getCartIdForUser(userId);
    }

    public static boolean addToCart(int cartId, int itemId) {
        return Dbcart.addToCart(cartId, itemId);
    }

    public static boolean purchaseItemsInCart(int cartId) {
        return Dbcart.purchaseItemsInCart(cartId);
    }

    public static List<Cart> ListCarts(){
        return Dbcart.getAllCarts();
    }

    public static boolean packCart(int cartId) {
        return Dbcart.updatePackedStatus(cartId);
    }


}
