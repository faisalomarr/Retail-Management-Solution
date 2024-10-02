package se.kth.faisalo.webappdemo2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    // Singleton instance
    private static DbManager dbManager = null;
    private Connection connection = null;

    // Private constructor to prevent instantiation
    private DbManager() {
        String user = "root";
        String pwd = "Afcsaka1!kth2";
        String server = "jdbc:mysql://localhost:3306/testdb";
        System.out.println("hej");
        try {
            System.out.println("Connecting to database...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(server, user, pwd);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DbManager getInstance() {
        if (dbManager == null) {
            dbManager = new DbManager();
        }
        return dbManager;
    }

    public static Connection getConnection() {
        return getInstance().connection;
    }


    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
