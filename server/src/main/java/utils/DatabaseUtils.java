package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {
    public static Connection connectDB() {
        Connection conn = null;
        try {java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

            // db parameters
            String url = "jdbc:sqlite:users.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            return conn;
        }
    }

    public static void disconnectDB(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
