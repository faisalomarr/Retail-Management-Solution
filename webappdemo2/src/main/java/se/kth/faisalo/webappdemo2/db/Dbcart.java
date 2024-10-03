/*package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Cart;
import se.kth.faisalo.webappdemo2.bo.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*public class Dbcart extends Cart {
    protected Dbcart(int cartId, List<Item> cartList) {
        super(cartId, cartList);
    }

    public static List<Cart> searchCarts(){
        List<Cart> carts= new ArrayList<Cart>();
        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from cart");
            while (set.next()) {
                int id = set.getInt("idcart");
                int iduser = set.getInt("iduser");
                carts.add(new ());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }



}*/
