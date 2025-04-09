package dao;

import entity.Cat;
import entity.Dog;
import entity.Pet;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    // Add a pet to the database
    public void addPet(Pet pet) {
        String query = "INSERT INTO pets (name, age, breed, dog_breed, cat_color, type) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, pet.getName());
            stmt.setInt(2, pet.getAge());
            stmt.setString(3, pet.getBreed());

            if (pet instanceof Dog) {
                stmt.setString(4, ((Dog) pet).getDogBreed());
                stmt.setNull(5, Types.VARCHAR);
                stmt.setString(6, "dog");
            } else if (pet instanceof Cat) {
                stmt.setNull(4, Types.VARCHAR);
                stmt.setString(5, ((Cat) pet).getCatColor());
                stmt.setString(6, "cat");
            } else {
                stmt.setNull(4, Types.VARCHAR);
                stmt.setNull(5, Types.VARCHAR);
                stmt.setString(6, "unknown");
            }

            stmt.executeUpdate();
            System.out.println("✅ Pet added to the database.");
        } catch (SQLException e) {
            System.out.println("❌ Error adding pet: " + e.getMessage());
        }
    }

    // Get all pets from DB
    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM pets";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String breed = rs.getString("breed");
                String type = rs.getString("type");
                Pet pet;

                if ("dog".equalsIgnoreCase(type)) {
                    pet = new Dog(name, age, breed, rs.getString("dog_breed"));
                } else if ("cat".equalsIgnoreCase(type)) {
                    pet = new Cat(name, age, breed, rs.getString("cat_color"));
                } else {
                    continue;
                }

                pets.add(pet);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error reading pets: " + e.getMessage());
        }

        return pets;
    }

    // Remove a pet by name
    public void removePetByName(String name) {
        String query = "DELETE FROM pets WHERE name = ?";
        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            int count = stmt.executeUpdate();

            if (count > 0) {
                System.out.println("Pet removed from the database.");
            } else {
                System.out.println(" Pet not found in database.");
            }

        } catch (SQLException e) {
            System.out.println("Error removing pet: " + e.getMessage());
        }
    }
}
