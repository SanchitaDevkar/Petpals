package dao;

import exception.InsufficientFundsException;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CashDonation extends Donation {
    private LocalDateTime donationDate;

    public CashDonation(String donorName, double amount, Date date) {
        super(donorName, amount);
        this.donationDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public Date getDonationDate() {
        return Date.from(donationDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setDonationDate(LocalDateTime donationDate) {
        this.donationDate = donationDate;
    }

    @Override
    public void recordDonation() throws InsufficientFundsException {
        if (amount < 10) {
            throw new InsufficientFundsException("Donation amount must be at least ₹10.");
        }

        System.out.println("Cash Donation Recorded:");
        System.out.println("Donor: " + donorName);
        System.out.println("Amount: ₹" + amount);
        System.out.println("Date: " + donationDate);

        Connection conn = DBConnUtil.getConnection("db.properties");
        if (conn == null) {
            System.out.println("Database connection failed.");
            return;
        }

        String query = "INSERT INTO cashdonations (donor_name, amount, donation_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, donorName);
            stmt.setDouble(2, amount);
            stmt.setDate(3, java.sql.Date.valueOf(donationDate.toLocalDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to insert donation: " + e.getMessage());
        }
    }
}
