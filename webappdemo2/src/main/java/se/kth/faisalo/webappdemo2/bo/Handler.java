package se.kth.faisalo.webappdemo2.bo;

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
            itemInfos.add(new ItemInfo(item.getName(),item.getDescription(),item.getId()));
        }
        return itemInfos;

    }

    public static List<ItemInfo> getItemsInCart(int idCart) {
        List<Item> itmes = Cart.getItmesInCart(idCart);
        List<ItemInfo> itemInfos = new ArrayList<>();
        for (Item item : itmes) {
            itemInfos.add(new ItemInfo(item.getName(), item.getDescription(),item.getId()));
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
}
