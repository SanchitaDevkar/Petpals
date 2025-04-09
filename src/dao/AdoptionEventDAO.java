package dao;

import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AdoptionEventDAO {

    
    public int hostEvent(String description) {
        String query = "INSERT INTO adoptionevents (description, event_date) VALUES (?, CURDATE())";
        int generatedId = -1;

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, description);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                System.out.println(" Adoption event recorded in DB with ID: " + generatedId);
            }

        } catch (SQLException e) {
            System.out.println(" Error recording event: " + e.getMessage());
        }

        return generatedId;
    }

    // New method with location
    public int hostEvent(String description, String location) {
        String query = "INSERT INTO adoptionevents (description, location, event_date) VALUES (?, ?, CURDATE())";
        int generatedId = -1;

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, description);
            stmt.setString(2, location);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                System.out.println("Adoption event recorded in DB with ID: " + generatedId);
            }

        } catch (SQLException e) {
            System.out.println("Error recording event: " + e.getMessage());
        }

        return generatedId;
    }
}
