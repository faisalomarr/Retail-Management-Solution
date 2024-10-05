package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.db.Dbcart;
import se.kth.faisalo.webappdemo2.ui.CartInfo;
import se.kth.faisalo.webappdemo2.ui.ItemInfo;
import se.kth.faisalo.webappdemo2.ui.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class Handler {

    public static List<ItemInfo> getItems() {
        List<Item> items = Item.searchItems();
        List<ItemInfo> itemInfos = new ArrayList<>();
        for (Item item : items) {
            itemInfos.add(new ItemInfo(item.getName(),item.getDescription(),item.getId(),item.getQuantity(), item.getPrice(), item.getType()))
        }
        return itemInfos;

    }

    public static List<CartInfo> getCarts() {
        List<Cart> carts = Cart.ListCarts();
        List<CartInfo> cartInfos = new ArrayList<>();
        for (Cart cart: carts) {
            cartInfos.add(new CartInfo(cart.getCartId(),cart.getUserId(),cart.getStatus(),cart.getPackedstatus()));
        }
        return cartInfos;

    }

    public static List<ItemInfo> getItemsInCart(int idCart) {
        List<Item> itmes = Cart.getItemsInCart(idCart);
        List<ItemInfo> itemInfos = new ArrayList<>();
        for (Item item : itmes) {
            itemInfos.add(new ItemInfo(item.getName(),item.getDescription(),item.getId(),item.getQuantity(), item.getPrice(), item.getType()))
        }
        return itemInfos;
    }

    public static List<UserInfo> getUsers() {
        List<User> users = User.getUsers();
        List<UserInfo> userInfos = new ArrayList<>();
        for (User user : users) {
            userInfos.add(new UserInfo(user.getUsername(),user.getPassword(), user.getUserId(),user.getRole()));
        }
        return userInfos;
    }

    public static UserInfo ValidUser(String username, String password, List<UserInfo> users) {
        for (UserInfo userInfo : users) {
            if (userInfo.getUsername().equals(username) && userInfo.getPassword().equals(password)) {
                return userInfo;
            }
        }
        return null;
    }

    public static boolean createUser(UserInfo userInfo) {
        return User.createUser(userInfo.getUsername(), userInfo.getPassword(), userInfo.getUserId(), userInfo.getRole());
    }

    public static int getCartIdFromUser(UserInfo userInfo) {
        return Cart.getCartIdFromUser(userInfo.getUserId());
    }

    public static boolean addToCart(int cartId, int itemId){
        return Cart.addToCart(cartId,itemId);
    }

    public static boolean purchaseItemsInCart(int cartId){
        return Cart.purchaseItemsInCart(cartId);
    }

    public static boolean packOrder(int cartid) {
        return Cart.packCart(cartid);
    }


    public static boolean addItemToStock(String name, String type, String description, int price, int quantity) {
        return Item.addItemToStock(name,type,description,price,quantity);
    }

    public static boolean editItem(int itemId, String name, String type, String description, int price, int quantity){
        return Item.editItem(itemId, name, type,description,price,quantity);
    }

}
