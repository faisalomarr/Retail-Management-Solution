package se.kth.faisalo.webappdemo2.ui;

public class CartInfo {
    private int idcart;
    private int iduser;

    public CartInfo(int idcart, int iduser) {
        this.idcart = idcart;
        this.iduser = iduser;
    }


    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
}
