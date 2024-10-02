package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Item;
import se.kth.faisalo.webappdemo2.bo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBuser extends User {

    public static List<User> ListUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from user");
            while (set.next()) {
                String username = set.getString("username");
                String password = set.getString("password");
                users.add(new DBuser(username, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static boolean CheckUser(String username, String password) {
        List<User> users = ListUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }










    public DBuser(String username, String password) {
        super(username, password);
    }
}
