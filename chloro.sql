-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 16, 2024 at 10:16 AM
-- Server version: 8.0.30
-- PHP Version: 8.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chloro`
--

-- --------------------------------------------------------

--
-- Table structure for table `alternative`
--

CREATE TABLE `alternative` (
  `alternative_id` int NOT NULL,
  `alternative_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `alternative`
--

INSERT INTO `alternative` (`alternative_id`, `alternative_name`) VALUES
(1, 'Router'),
(2, 'Server'),
(3, 'Switch'),
(4, 'Firewall');

-- --------------------------------------------------------

--
-- Table structure for table `criteria`
--

CREATE TABLE `criteria` (
  `criteria_id` int NOT NULL,
  `criteria_name` varchar(50) NOT NULL,
  `criteria_weight` double NOT NULL,
  `criteria_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `criteria`
--

INSERT INTO `criteria` (`criteria_id`, `criteria_name`, `criteria_weight`, `criteria_type`) VALUES
(1, 'Harga', 0.3, 'Cost'),
(2, 'Kualitas', 0.3, 'Benefit'),
(3, 'Urgensi Kebutuhan', 0.2, 'Benefit'),
(4, 'Perawatan', 0.2, 'Cost');

-- --------------------------------------------------------

--
-- Table structure for table `spk_detail`
--

CREATE TABLE `spk_detail` (
  `spk_id` int NOT NULL,
  `alternative_name` varchar(50) NOT NULL,
  `alternative_rank` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `spk_detail`
--

INSERT INTO `spk_detail` (`spk_id`, `alternative_name`, `alternative_rank`) VALUES
(1, 'dfadasda', 2),
(1, 'asdsad', 1),
(2, 'dfadasda', 2),
(2, 'asdsad', 1),
(3, 'dfadasda', 1),
(3, 'asdsad', 2),
(8, 'dfadasda', 1),
(8, 'asdsad', 2),
(9, 'dfadasda', 1),
(9, 'asdsad', 2),
(10, 'dfadasda', 1),
(10, 'asdsad', 2),
(11, 'dfadasda', 1),
(11, 'asdsad', 1),
(12, 'dfadasda', 1),
(12, 'asdsad', 1),
(13, 'dfadasda', 1),
(13, 'asdsad', 2),
(14, 'dfadasda', 1),
(14, 'asdsad', 2),
(15, 'dfadasda', 2),
(15, 'asdsad', 1),
(16, 'dfadasda', 2),
(16, 'asdsad', 1),
(17, 'dfadasda', 2),
(17, 'asdsad', 1),
(18, 'dfadasda', 2),
(18, 'asdsad', 1),
(21, 'dfadasda', 1),
(21, 'asdsad', 2),
(22, 'dfadasda', 1),
(22, 'asdsad', 2),
(48, 'TWG Tea - Silver Moon Tea', 2),
(48, 'Dilmah - Ceylon Supreme', 3),
(48, 'Teh Cap Poci - Melati', 4),
(48, 'Harney & Sons - Paris Tea', 1),
(53, 'Router', 2),
(53, 'Server', 4),
(53, 'Switch', 1),
(53, 'Firewall', 3);

-- --------------------------------------------------------

--
-- Table structure for table `spk_history`
--

CREATE TABLE `spk_history` (
  `spk_id` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `spk_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `spk_history`
--

INSERT INTO `spk_history` (`spk_id`, `created_at`, `user_id`, `spk_name`) VALUES
(10, '2024-07-02 02:54:42', 6, NULL),
(48, '2024-08-07 06:05:20', 10, 'Rekomendasi Bahan Teh  \"Rahaju Tea House\"'),
(53, '2024-11-09 17:14:48', 9, 'Perhitungan SPK Alat IT Terbaik');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `fullname`, `role`, `address`, `created_at`) VALUES
(9, 'Admin', 'c4ca4238a0b923820dcc509a6f75849b', 'Julianto', 'Admin', 'Unindra', '2024-07-27 05:10:59'),
(12, 'User', 'ee11cbb19052e40b07aac0ca060c23ee', 'User', 'Pengguna', 'Jakarta', '2024-08-08 04:36:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alternative`
--
ALTER TABLE `alternative`
  ADD PRIMARY KEY (`alternative_id`);

--
-- Indexes for table `criteria`
--
ALTER TABLE `criteria`
  ADD PRIMARY KEY (`criteria_id`);

--
-- Indexes for table `spk_history`
--
ALTER TABLE `spk_history`
  ADD PRIMARY KEY (`spk_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alternative`
--
ALTER TABLE `alternative`
  MODIFY `alternative_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `criteria`
--
ALTER TABLE `criteria`
  MODIFY `criteria_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `spk_history`
--
ALTER TABLE `spk_history`
  MODIFY `spk_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
