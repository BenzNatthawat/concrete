-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2020 at 05:06 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `concrete`
--

-- --------------------------------------------------------

--
-- Table structure for table `deliverycharges`
--

CREATE TABLE `deliverycharges` (
  `id` int(11) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` decimal(8,2) DEFAULT NULL,
  `area` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `orders_id` int(11) NOT NULL,
  `items_id` int(11) DEFAULT NULL,
  `deliverycharges_id` int(11) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `list` varchar(45) DEFAULT NULL,
  `discount` decimal(8,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `createdAt`, `updatedAt`, `orders_id`, `items_id`, `deliverycharges_id`, `price`, `list`, `discount`, `description`, `quantity`) VALUES
(1, '2020-01-14 16:40:51', '2020-01-14 16:40:51', 1, 3, NULL, '2050.00', 'A210', '0.00', '', 5),
(2, '2020-01-14 16:40:51', '2020-01-14 16:40:51', 1, 5, NULL, '2150.00', 'A280', '0.00', '', 3),
(3, '2020-01-15 14:46:43', '2020-01-15 14:46:43', 2, 4, NULL, '2100.00', 'A240', '0.00', '', 5),
(4, '2020-01-15 14:46:43', '2020-01-15 14:46:43', 2, 2, NULL, '2000.00', 'A180', '0.00', '', 4),
(5, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 5, NULL, '2150.00', 'A280', '0.00', '', 2),
(6, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 5, NULL, '2150.00', 'A280', '0.00', '', 3),
(7, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 4, NULL, '2100.00', 'A240', '0.00', '', 1),
(8, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 2, NULL, '2000.00', 'A180', '0.00', '', 1),
(9, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 5, NULL, '2150.00', 'A280', '0.00', '', 1),
(10, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 4, NULL, '2100.00', 'A240', '0.00', '', 1),
(11, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 3, NULL, '2050.00', 'A210', '0.00', '', 1),
(12, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 2, NULL, '2000.00', 'A180', '0.00', '', 1),
(13, '2020-01-15 16:45:08', '2020-01-15 16:45:08', 3, 1, NULL, '1950.00', 'lean', '0.00', '', 1),
(14, '2020-01-21 16:56:26', '2020-01-21 16:56:26', 4, 3, NULL, '2050.00', 'A210', '0.00', '', 4),
(15, '2020-02-08 17:33:27', '2020-02-08 17:33:27', 5, 3, NULL, '2050.00', 'A210', '0.00', '', 3),
(16, '2020-02-08 17:33:27', '2020-02-08 17:33:27', 5, 5, NULL, '2150.00', 'A280', '0.00', '', 3);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` decimal(8,2) DEFAULT NULL,
  `cube` varchar(20) DEFAULT NULL,
  `installment` decimal(8,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `createdAt`, `updatedAt`, `price`, `cube`, `installment`) VALUES
(1, '2019-12-17 09:50:30', '2019-12-17 09:50:30', '1950.00', 'lean', '2050.00'),
(2, '2019-12-17 09:51:30', '2019-12-17 09:51:30', '2000.00', 'A180', '2100.00'),
(3, '2019-12-17 09:51:30', '2019-12-17 09:51:30', '2050.00', 'A210', '2150.00'),
(4, '2019-12-17 09:56:24', '2019-12-17 09:56:24', '2100.00', 'A240', '2200.00'),
(5, '2019-12-17 09:56:24', '2019-12-17 09:56:24', '2150.00', 'A280', '2250.00');

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` int(11) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `orders_id` int(11) NOT NULL,
  `latitude` decimal(8,2) DEFAULT NULL,
  `latitude_copy1` decimal(8,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `users_id` int(11) NOT NULL,
  `deliveryDateTime` datetime DEFAULT NULL,
  `status` enum('confirm','cancel','pay','finish','shipping') DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `createdAt`, `updatedAt`, `users_id`, `deliveryDateTime`, `status`, `tel`, `description`, `name`) VALUES
(1, '2020-01-14 16:40:51', '2020-01-14 16:40:51', 1, '0000-00-00 00:00:00', 'confirm', '0855555555', 'send today', 'to'),
(2, '2020-01-15 14:46:43', '2020-01-15 14:46:43', 1, '0000-00-00 00:00:00', 'confirm', '0651654654', 'dsfsdf', 'benz'),
(3, '2020-02-24 03:11:18', '2020-02-24 03:11:18', 3, '0000-00-00 00:00:00', 'confirm', '654651651', 'sdfasdfasdfasdf', 'rgreg'),
(4, '2020-02-24 03:11:20', '2020-02-24 03:11:20', 3, '0000-00-00 00:00:00', 'confirm', '885455', 'ดfdgdrfgsdfgre', 'vbxvcb'),
(5, '2020-02-08 17:33:27', '2020-02-08 17:33:27', 1, '0000-00-00 00:00:00', 'confirm', '863832852', '', 'jhgjhg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `role` enum('admin','user') DEFAULT 'user',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `name`, `status`, `role`, `createdAt`, `updatedAt`) VALUES
(1, 'benz', '$2b$10$18dzDvK6aQy5DNrypc/tOeoq7HOLcKr/vRQPM1ztYJzZPqntsPLRq', 'benz', 1, 'admin', '2020-02-24 03:39:05', '2020-02-24 03:39:05'),
(3, 'benzzz', '$2b$10$MThKHCwqqeLJg5MXB.9KJOqca/V39Cmhv2hddz5ouYD.UxaaUQ0Qu', 'benzzzzz', 1, 'user', '2020-02-24 03:21:38', '2020-02-24 03:21:38');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deliverycharges`
--
ALTER TABLE `deliverycharges`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_item_orders_idx` (`orders_id`),
  ADD KEY `fk_item_items1_idx` (`items_id`),
  ADD KEY `fk_item_deliverycharges1_idx` (`deliverycharges_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_location_orders1_idx` (`orders_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_orders_users1_idx` (`users_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `deliverycharges`
--
ALTER TABLE `deliverycharges`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `fk_item_deliverycharges1` FOREIGN KEY (`deliverycharges_id`) REFERENCES `deliverycharges` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_item_items1` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_item_orders` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `fk_location_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
