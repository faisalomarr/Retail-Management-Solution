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

    public DBuser(String username, String password, int userId) {
        super(username, password, userId);
    }

    public static List<User> ListUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Connection connection = DbManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from user");
            while (set.next()) {
                String username = set.getString("username");
                String password = set.getString("password");
                int userId = set.getInt("iduser");
                users.add(new DBuser(username, password, userId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public static int CheckUser(String username, String password) {
        List<User> users = ListUsers();  // Assuming ListUsers fetches all users
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getUserId();  // Return the userId if the username and password match
            }
        }
        return -1;  // Return -1 if no match is found
    }
    }


