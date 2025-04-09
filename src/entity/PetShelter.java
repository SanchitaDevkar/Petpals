package entity;

import dao.IAdoptable;
import exception.FileReadException;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetShelter implements IAdoptable {
    private List<Pet> availablePets;

    // Constructor
    public PetShelter() {
        availablePets = new ArrayList<>();
    }

    // Add pet to the shelter
    public void addPet(Pet pet) {
        availablePets.add(pet);
    }

    // Remove pet from the shelter
    public void removePet(Pet pet) {
        availablePets.remove(pet);
    }

    // List all available pets
    public void listAvailablePets() {
        if (availablePets.isEmpty()) {
            System.out.println("No pets available for adoption.");
        } else {
            for (Pet pet : availablePets) {
                try {
                    if (pet == null || pet.getName() == null || pet.getAge() <= 0) {
                        throw new NullPointerException("Pet info is missing or invalid.");
                    }
                    System.out.println(pet.toString());
                } catch (NullPointerException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    // Get list (for internal use if needed)
    public List<Pet> getAvailablePets() {
        return availablePets;
    }

    // Fetch pets from the database
    public void fetchPetsFromDatabase() throws FileReadException {
        Connection conn = DBConnUtil.getConnection("db.properties");
        if (conn == null) throw new FileReadException("Failed to connect to DB");

        String query = "SELECT * FROM pets";
        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            availablePets.clear();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String breed = rs.getString("breed");
                String type = rs.getString("type");
                String catColor = rs.getString("cat_color");
                String dogBreed = rs.getString("dog_breed");

                Pet pet = null;
                if ("Cat".equalsIgnoreCase(type)) {
                    pet = new Cat(name, age, breed, catColor);
                } else if ("Dog".equalsIgnoreCase(type)) {
                    pet = new Dog(name, age, breed, dogBreed);
                }

                if (pet != null) {
                    addPet(pet);
                }
            }
        } catch (SQLException e) {
            throw new FileReadException("Error reading pets from DB: " + e.getMessage());
        }
    }

    // Implementation of adopt() from IAdoptable
    @Override
    public void adopt() {
        System.out.println("Pet adopted from the shelter.");
    }
}
