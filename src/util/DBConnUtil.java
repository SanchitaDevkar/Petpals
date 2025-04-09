package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection(String propertyFileName) {
        Connection conn = null;
        try {
            String connectionString = DBPropertyUtil.getConnectionString(propertyFileName);
            if (connectionString != null) {
                conn = DriverManager.getConnection(connectionString);
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return conn;
    }
}
