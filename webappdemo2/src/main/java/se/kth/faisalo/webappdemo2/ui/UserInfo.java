package se.kth.faisalo.webappdemo2.ui;

import se.kth.faisalo.webappdemo2.bo.User;

public class UserInfo {
    private String username;
    private String password;
    private int userId;
    private int cartId;

    private User.Role role;

    public UserInfo(String username, String password, int userId, int cartId, User.Role role) {
        this.username = username;
        this.password = password;
        this.userId= userId;
        this.cartId= cartId;
        this.role= role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cardId) {
        this.cartId = cardId;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}