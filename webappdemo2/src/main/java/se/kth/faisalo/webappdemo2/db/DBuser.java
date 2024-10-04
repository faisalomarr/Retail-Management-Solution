package se.kth.faisalo.webappdemo2.db;

import se.kth.faisalo.webappdemo2.bo.Item;
import se.kth.faisalo.webappdemo2.bo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBuser extends User {

    public DBuser(String username, String password, int userId, Role role) {
        super(username, password, userId, role);
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
                String roleString = set.getString("role");
                User.Role role=User.Role.valueOf(roleString.toUpperCase());
                users.add(new DBuser(username, password, userId, role));
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

    public static boolean createUser(String username, String password, int userId, User.Role role) {
        try {
            Connection connection = DbManager.getConnection();
            // Create SQL INSERT query
            String query = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";

            // Use PreparedStatement to securely set parameters
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);            // Set the username
            statement.setString(2, password);            // Set the password
            statement.setString(3, role.name().toLowerCase());  // Set the role (converted to lowercase string)

            // Execute the query (returns the number of affected rows)
            int AmountrowsInserted = statement.executeUpdate();

            // Return true if the user was successfully created
            return AmountrowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

