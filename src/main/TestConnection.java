package main;

import java.sql.Connection;
import util.DBConnUtil;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DBConnUtil.getConnection("db.properties");

        if (conn != null) {
            System.out.println("Database connection successful.");
        } else {
            System.out.println("Failed to connect to database.");
        }
    }
}
