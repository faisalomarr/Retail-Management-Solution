package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.db.DbItem;
import se.kth.faisalo.webappdemo2.db.Dbcart;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int cartId;

    private List<Item> cartList;



    protected Cart (int cartId, List<Item> cartList){
        this.cartId=cartId;
        this.cartList=cartList;
    }


    public int getCartId() {
        return cartId;
    }

    public List<Item> getCartList() {
        return cartList;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setCartList(List<Item> cartList) {
        this.cartList = cartList;
    }

    static public List<Item> getItmesInCart(int cartId){
        return Dbcart.getItemsInCart(cartId);
    }

    static public int getCartIdFromUser(int userid){
        return Dbcart.getCartIdForUser(userid);
    }

    static public boolean addToCart(int cartId, int itemId) {
        return Dbcart.addToCart(cartId,itemId);
    }

}
