package dao;

public class ItemDonation extends Donation {
    private String itemType;

    // Constructor
    public ItemDonation(String donorName, double amount, String itemType) {
        super(donorName, amount);
        this.itemType = itemType;
    }

    // Getter and Setter
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    // Implementation of abstract method
    @Override
    public void recordDonation() {
        System.out.println("Item Donation Recorded:");
        System.out.println("Donor: " + donorName);
        System.out.println("Amount (Estimated): $" + amount);
        System.out.println("Item Type: " + itemType);
    }
}
