package se.kth.faisalo.webappdemo2.ui;

import se.kth.faisalo.webappdemo2.bo.Cart;
import se.kth.faisalo.webappdemo2.bo.Item;

import java.util.List;

public class CartInfo {
    private int idCart;
    private  int idUser;
    private Cart.Status status;
    private  Cart.Packedstatus packedstatus;

    public CartInfo(int idCart, int idUser, Cart.Status status, Cart.Packedstatus packedstatus) {
        this.idCart = idCart;
        this.idUser = idUser;
        this.status = status;
        this.packedstatus = packedstatus;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Cart.Status getStatus() {
        return status;
    }

    public void setStatus(Cart.Status status) {
        this.status = status;
    }

    public Cart.Packedstatus getPackedstatus() {
        return packedstatus;
    }

    public void setPackedstatus(Cart.Packedstatus packedstatus) {
        this.packedstatus = packedstatus;
    }
}
