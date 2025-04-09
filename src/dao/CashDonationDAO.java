package dao;

import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class CashDonationDAO {

    public void addDonation(String donorName, double amount) {
        Connection conn = DBConnUtil.getConnection("db.properties");

        if (conn == null) {
            System.out.println("Database connection failed.");
            return;
        }

        String query = "INSERT INTO cashdonations (donor_name, amount, donation_date) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, donorName);
            stmt.setDouble(2, amount);
            stmt.setDate(3, new Date(System.currentTimeMillis())); // current date
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to insert cash donation: " + e.getMessage());
        }
    }
}
