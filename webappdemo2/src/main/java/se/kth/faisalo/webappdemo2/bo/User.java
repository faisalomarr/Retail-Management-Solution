package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.db.DBuser;

import java.util.List;

public class User {
    private String username;
    private String password;
    private int userId;
    public enum Role {KUND, ADMIN, LAGERPERSONAL}

    private Role role;
    public User(String username, String password, int userId, Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role=role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}