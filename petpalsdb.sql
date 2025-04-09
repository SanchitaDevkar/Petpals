-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2025 at 10:43 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `petpalsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `adoptionevents`
--

CREATE TABLE `adoptionevents` (
  `event_id` int(11) NOT NULL,
  `event_date` date DEFAULT curdate(),
  `description` varchar(255) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `adoptionevents`
--

INSERT INTO `adoptionevents` (`event_id`, `event_date`, `description`, `location`) VALUES
(1, '2025-04-10', 'Spring Pet Adoption Fair', NULL),
(3, '2025-04-09', 'Monthly Adoption Event', NULL),
(7, '2025-04-09', 'Save animal ', 'Pune');

-- --------------------------------------------------------

--
-- Table structure for table `cashdonations`
--

CREATE TABLE `cashdonations` (
  `donation_id` int(11) NOT NULL,
  `donor_name` varchar(100) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `donation_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cashdonations`
--

INSERT INTO `cashdonations` (`donation_id`, `donor_name`, `amount`, `donation_date`) VALUES
(4, 'Madhu', 4777.00, '2025-04-09'),
(7, 'diksha', 4770.00, '2025-04-09'),
(9, 'Sanchita', 4000.00, '2025-04-09');

-- --------------------------------------------------------

--
-- Table structure for table `eventparticipants`
--

CREATE TABLE `eventparticipants` (
  `participant_id` int(11) NOT NULL,
  `event_id` int(11) DEFAULT NULL,
  `shelter_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `eventparticipants`
--

INSERT INTO `eventparticipants` (`participant_id`, `event_id`, `shelter_name`) VALUES
(2, 1, 'Animal Rescue League'),
(4, 1, 'sahas'),
(6, 7, 'Animal Safety ');

-- --------------------------------------------------------

--
-- Table structure for table `itemdonations`
--

CREATE TABLE `itemdonations` (
  `donation_id` int(11) NOT NULL,
  `donor_name` varchar(100) NOT NULL,
  `item_description` varchar(255) NOT NULL,
  `donation_date` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `itemdonations`
--

INSERT INTO `itemdonations` (`donation_id`, `donor_name`, `item_description`, `donation_date`) VALUES
(1, 'Sneha Kapoor', 'Pack of dog food', '2025-04-09'),
(2, 'Amit Kumar', 'Cat toys and grooming kit', '2025-04-09'),
(6, 'Ram', 'Medicine', '2025-04-09');

-- --------------------------------------------------------

--
-- Table structure for table `pets`
--

CREATE TABLE `pets` (
  `pet_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `breed` varchar(50) DEFAULT NULL,
  `type` enum('Dog','Cat') NOT NULL,
  `dog_breed` varchar(50) DEFAULT NULL,
  `cat_color` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pets`
--

INSERT INTO `pets` (`pet_id`, `name`, `age`, `breed`, `type`, `dog_breed`, `cat_color`) VALUES
(1, 'Tommy', 3, 'Labrador', 'Dog', 'Golden Labrador', NULL),
(2, 'Bruno', 5, 'Beagle', 'Dog', 'Beagle', NULL),
(10, 'moti', 3, 'Labrador', 'Dog', 'Labrador', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adoptionevents`
--
ALTER TABLE `adoptionevents`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `cashdonations`
--
ALTER TABLE `cashdonations`
  ADD PRIMARY KEY (`donation_id`);

--
-- Indexes for table `eventparticipants`
--
ALTER TABLE `eventparticipants`
  ADD PRIMARY KEY (`participant_id`),
  ADD KEY `event_id` (`event_id`);

--
-- Indexes for table `itemdonations`
--
ALTER TABLE `itemdonations`
  ADD PRIMARY KEY (`donation_id`);

--
-- Indexes for table `pets`
--
ALTER TABLE `pets`
  ADD PRIMARY KEY (`pet_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adoptionevents`
--
ALTER TABLE `adoptionevents`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `cashdonations`
--
ALTER TABLE `cashdonations`
  MODIFY `donation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `eventparticipants`
--
ALTER TABLE `eventparticipants`
  MODIFY `participant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `itemdonations`
--
ALTER TABLE `itemdonations`
  MODIFY `donation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pets`
--
ALTER TABLE `pets`
  MODIFY `pet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `eventparticipants`
--
ALTER TABLE `eventparticipants`
  ADD CONSTRAINT `eventparticipants_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `adoptionevents` (`event_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
