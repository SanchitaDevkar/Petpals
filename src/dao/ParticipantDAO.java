package dao;

import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    
    public void addParticipant(int eventId, String shelterName) {
        Connection conn = DBConnUtil.getConnection("db.properties");

        if (conn == null) {
            System.out.println("Database connection failed.");
            return;
        }

        String query = "INSERT INTO eventparticipants (event_id, shelter_name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, eventId);
            stmt.setString(2, shelterName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding participant: " + e.getMessage());
        }
    }

    // Method to fetch all shelter names participating in a given event
    public List<String> getParticipants(int eventId) {
        List<String> participants = new ArrayList<>();
        Connection conn = DBConnUtil.getConnection("db.properties");

        if (conn == null) {
            System.out.println("Database connection failed.");
            return participants;
        }

        String query = "SELECT shelter_name FROM eventparticipants WHERE event_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                participants.add(rs.getString("shelter_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving participants: " + e.getMessage());
        }

        return participants;
    }
}
