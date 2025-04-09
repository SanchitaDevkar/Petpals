package dao;

import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDonationDAO {

    public void addDonation(String donorName, String itemDescription) {
        String query = "INSERT INTO itemdonations (donor_name, item_description, donation_date) VALUES (?, ?, CURDATE())";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, donorName);
            stmt.setString(2, itemDescription);
            stmt.executeUpdate();
            System.out.println(" Item donation inserted in database.");

        } catch (SQLException e) {
            System.out.println(" Error adding item donation: " + e.getMessage());
        }
    }
}
