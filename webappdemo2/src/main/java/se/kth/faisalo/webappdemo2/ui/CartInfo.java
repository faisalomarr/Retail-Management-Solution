package se.kth.faisalo.webappdemo2.ui;

import se.kth.faisalo.webappdemo2.bo.Item;

import java.util.List;

public class CartInfo {
    private int idCart;
    private List<ItemInfo> cartList;

    public CartInfo(int idCart, List<ItemInfo> cartList) {
        this.idCart = idCart;
        this.cartList = cartList;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public List<ItemInfo> getCartList() {
        return cartList;
    }

    public void setCartList(List<ItemInfo> cartList) {
        this.cartList = cartList;
    }
}
