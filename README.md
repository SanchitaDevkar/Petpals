PetPals – The Pet Adoption Platform
PetPals is a Java-based object-oriented application designed to streamline pet adoption processes and manage pet shelters, donor records, and adoption events. This project demonstrates proficiency in core Java concepts, including Object-Oriented Programming, Exception Handling, Collections, Interfaces, and JDBC for seamless interaction with a relational database.

Project Structure
graphql
Copy code
PetPals/
├── src/
│   ├── entity/            # Entity/model classes (Pet, Dog, Cat, PetShelter)
│   ├── dao/               # Interfaces and implementation classes (Donation, Events)
│   ├── exception/         # Custom exception classes
│   ├── util/              # Utility classes for database connection
│   └── main/              # MainModule with menu-driven logic
├── db.properties          # Database configuration file
├── README.md              # Project documentation
Key Features
Pet Registration and Management
Add, remove, and view pets (Dog and Cat with specific attributes).

Pet Shelter Management
Manage shelter inventory and animal profiles.

Donation Management
Record and classify both cash and item donations.

Adoption Events
Schedule and register participants for adoption events.

Adoption Interface
Interface-driven adoption logic to promote object-oriented principles.

Robust Exception Handling
Handles various application-specific and system-level exceptions such as:

InvalidPetAgeException

NullPetInfoException

InsufficientFundsException

FileHandlingException

AdoptionException

Database Integration via JDBC
Perform CRUD operations on pets, donations, and adoption events.

Utility-Driven Database Connectivity
Configured using external properties file for easy maintainability.

Technologies Used
Java (JDK 8 or higher)

JDBC (Java Database Connectivity)

MySQL (via XAMPP)

Eclipse IDE

No Maven/Gradle used

Database Setup
Database Name: PetPalsDB

Execute the following SQL commands to set up the required tables:

sql
Copy code
CREATE DATABASE PetPalsDB;

USE PetPalsDB;

-- Pets table
CREATE TABLE pets (
    pet_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    breed VARCHAR(100),
    type VARCHAR(50),
    dog_breed VARCHAR(100),
    cat_color VARCHAR(50)
);

-- Donations table
CREATE TABLE donations (
    donation_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_name VARCHAR(100),
    amount DECIMAL(10,2),
    donation_type VARCHAR(20),
    donation_date DATE,
    item_type VARCHAR(100)
);

-- Adoption Events table
CREATE TABLE adoption_events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100),
    event_date DATE
);

-- Participants table
CREATE TABLE participants (
    participant_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    participant_name VARCHAR(100),
    FOREIGN KEY (event_id) REFERENCES adoption_events(event_id)
);
Database Configuration
Configure your database settings in the db.properties file as shown below:

properties
Copy code
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/PetPalsDB
username=root
password=
Ensure MySQL is installed and running via XAMPP and that the JDBC driver (mysql-connector-java) is included in your build path.

How to Run the Application
Start the MySQL Server using XAMPP.

Create the database and tables using the provided SQL script.

Open the project in Eclipse IDE.

Add the MySQL JDBC driver (mysql-connector-java-x.x.x.jar) to the project build path.

Navigate to the MainModule.java class inside the src/main/ package.

Run the program to launch the menu-driven console application.

Author
This project is developed as part of the Hexaware Hexavarsity Java Full Stack Training Program.

Mentorship and guidance provided by Hexaware Trainers.

