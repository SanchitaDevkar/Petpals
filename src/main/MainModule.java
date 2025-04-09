package main;

import java.util.Scanner;
import entity.*;
import dao.*;
import exception.*;
import java.io.*;
import java.sql.Connection;
import java.util.List;
import util.DBConnUtil;

public class MainModule {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetShelter shelter = new PetShelter();
        AdoptionEvent event = new AdoptionEvent();
        String dbFile = "db.properties";

        // DAO Instances
        PetDAO petDAO = new PetDAO();
        CashDonationDAO cashDonationDAO = new CashDonationDAO();
        ItemDonationDAO itemDonationDAO = new ItemDonationDAO();
        AdoptionEventDAO eventDAO = new AdoptionEventDAO();
        ParticipantDAO participantDAO = new ParticipantDAO();

        while (true) {
            System.out.println("\n===== PetPals Adoption Platform =====");
            System.out.println("1. Add a Pet");
            System.out.println("2. List Available Pets");
            System.out.println("3. Remove a Pet");
            System.out.println("4. Make a Cash Donation");
            System.out.println("5. Make an Item Donation");
            System.out.println("6. Host Adoption Event");
            System.out.println("7. Register Participant");
            System.out.println("8. Read Pets from File");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter pet type (dog/cat): ");
                        String type = scanner.nextLine().toLowerCase();

                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        if (age <= 0) {
                            throw new InvalidAgeException("Pet age must be a positive integer.");
                        }

                        System.out.print("Enter breed: ");
                        String breed = scanner.nextLine();

                        Pet pet = null;
                        if (type.equals("dog")) {
                            System.out.print("Enter dog breed: ");
                            String dogBreed = scanner.nextLine();
                            pet = new Dog(name, age, breed, dogBreed);
                        } else if (type.equals("cat")) {
                            System.out.print("Enter cat color: ");
                            String catColor = scanner.nextLine();
                            pet = new Cat(name, age, breed, catColor);
                        } else {
                            System.out.println("Invalid pet type.");
                            break;
                        }

                        petDAO.addPet(pet);
                        shelter.addPet(pet);
                        System.out.println("Pet added successfully to both system and database.");
                        break;

                    case 2:
                        List<Pet> pets = petDAO.getAllPets();
                        if (pets.isEmpty()) {
                            System.out.println("No pets available in the database.");
                        } else {
                            System.out.println("Available Pets:");
                            for (Pet p : pets) {
                                if (p.getName() == null || p.getAge() == 0) {
                                    throw new NullPropertyException("Pet information is incomplete.");
                                }
                                System.out.println(p);
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter pet name to remove: ");
                        String removeName = scanner.nextLine();

                        // First, check if the pet exists in DB
                        List<Pet> petsInDb = petDAO.getAllPets();
                        Pet petToRemove = null;

                        for (Pet p : petsInDb) {
                            if (p.getName().equalsIgnoreCase(removeName)) {
                                petToRemove = p;
                                break;
                            }
                        }

                        if (petToRemove != null) {
                            petDAO.removePetByName(removeName);
                            shelter.removePet(petToRemove); // In case it's in memory
                            System.out.println("Pet '" + removeName + "' removed from database.");
                        } else {
                            throw new AdoptionException("Pet not found.");
                        }
                        break;


                    case 4:
                        System.out.print("Enter donor name: ");
                        String donorName = scanner.nextLine();
                        System.out.print("Enter donation amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        if (amount < 10) {
                            throw new InsufficientFundsException("Minimum donation amount is ₹10.");
                        }

                        cashDonationDAO.addDonation(donorName, amount);
                        System.out.println("Cash donation recorded in database.");
                        break;

                    case 5:
                        System.out.print("Enter donor name: ");
                        String donorItemName = scanner.nextLine();
                        System.out.print("Enter item type: ");
                        String item = scanner.nextLine();

                        itemDonationDAO.addDonation(donorItemName, item);
                        System.out.println("Item donation recorded in database.");
                        break;

                    case 6:
                        System.out.print("Enter event description: ");
                        String eventDesc = scanner.nextLine();

                        System.out.print("Enter event location: ");
                        String eventLocation = scanner.nextLine();

                        int eventId = eventDAO.hostEvent(eventDesc, eventLocation);

                        for (IAdoptable participant : event.getParticipants()) {
                            if (participant instanceof PetShelter) {
                                participantDAO.addParticipant(eventId, "Pet Shelter");
                            }
                        }

                        event.hostEvent();
                        break;


                    case 7:
                        System.out.print("Enter Event ID to register for: ");
                        int eventIdManual = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Shelter Name to register: ");
                        String shelterName = scanner.nextLine();

                        participantDAO.addParticipant(eventIdManual, shelterName);
                        System.out.println("Shelter registered for the adoption event in database.");
                        break;

                    case 8:
                        System.out.print("Enter file name to read pets: ");
                        String fileName = scanner.nextLine();
                        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                System.out.println("File Line: " + line);
                            }
                        } catch (IOException e) {
                            throw new FileReadException("Error reading file: " + e.getMessage());
                        }
                        break;

                    case 9:
                        System.out.println("Exiting... Thank you for using PetPals.");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }

            } catch (InvalidAgeException | NullPropertyException | InsufficientFundsException |
                     AdoptionException | FileReadException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
