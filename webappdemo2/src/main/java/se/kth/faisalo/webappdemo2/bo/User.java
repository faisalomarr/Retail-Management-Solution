package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.db.DBuser;

import java.util.List;

public class User {
    private String username;
    private String password;
    private int userId;
    public enum Role {KUND, ADMIN, LAGERPERSONAL}

    public User(String username, String password, int userId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
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

    static public List<User> getUsers() {
        return DBuser.ListUsers();
    }

    static public boolean checkUser(String username, String password) {
        return DBuser.checkUser(username, password);
    }
}
