package dao;

import exception.InsufficientFundsException;

public abstract class Donation {
    protected String donorName;
    protected double amount;

    // Constructor
    public Donation(String donorName, double amount) {
        this.donorName = donorName;
        this.amount = amount;
    }

    // Getters and Setters
    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Abstract Method with exception
    public abstract void recordDonation() throws InsufficientFundsException;
}
