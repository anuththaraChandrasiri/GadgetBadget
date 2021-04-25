-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 24, 2021 at 05:38 AM
-- Server version: 8.0.21
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gadget_badget`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `adminId` int NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminId`) VALUES
(1000);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cartId` int NOT NULL AUTO_INCREMENT,
  `clientId` int NOT NULL,
  PRIMARY KEY (`cartId`),
  KEY `FK_clientId` (`clientId`)
) ENGINE=MyISAM AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cartId`, `clientId`) VALUES
(1000, 1004);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `clientId` int NOT NULL,
  PRIMARY KEY (`clientId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientId`) VALUES
(1003),
(1004);

-- --------------------------------------------------------

--
-- Table structure for table `finished`
--

DROP TABLE IF EXISTS `finished`;
CREATE TABLE IF NOT EXISTS `finished` (
  `pid` int NOT NULL,
  `researcherId` int NOT NULL,
  `cartId` int DEFAULT NULL,
  `clientId` int DEFAULT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`pid`,`researcherId`),
  KEY `FK_cartID_clientId` (`cartId`,`clientId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `finished`
--

INSERT INTO `finished` (`pid`, `researcherId`, `cartId`, `clientId`, `price`) VALUES
(10000, 1001, NULL, 1003, 30000),
(10001, 1002, 1004, 1004, 25000);

-- --------------------------------------------------------

--
-- Table structure for table `fund`
--

DROP TABLE IF EXISTS `fund`;
CREATE TABLE IF NOT EXISTS `fund` (
  `fundId` int NOT NULL AUTO_INCREMENT,
  `pId` int NOT NULL,
  `researcherId` int NOT NULL,
  `amount` float NOT NULL,
  PRIMARY KEY (`fundId`,`pId`,`researcherId`),
  KEY `FK_pId_researcherId` (`pId`,`researcherId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `fund_payment`
--

DROP TABLE IF EXISTS `fund_payment`;
CREATE TABLE IF NOT EXISTS `fund_payment` (
  `fPaymentId` int NOT NULL AUTO_INCREMENT,
  `pId` int NOT NULL,
  `researcherId` int NOT NULL,
  `fundId` int NOT NULL,
  `amount` float NOT NULL,
  `paymentStatus` varchar(10) NOT NULL,
  PRIMARY KEY (`fPaymentId`),
  KEY `FK_pId_researchId_fundId` (`pId`,`researcherId`,`fundId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `pid` int NOT NULL,
  `researcherId` int NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `totAmount` float NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`orderId`,`pid`,`researcherId`),
  KEY `FK_pId_researcherId` (`pid`,`researcherId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_payment`
--

DROP TABLE IF EXISTS `order_payment`;
CREATE TABLE IF NOT EXISTS `order_payment` (
  `oPaymentId` int NOT NULL AUTO_INCREMENT,
  `pId` int NOT NULL,
  `orderId` int NOT NULL,
  `researcherId` int NOT NULL,
  `amount` float NOT NULL,
  `paymentStatus` varchar(10) NOT NULL,
  PRIMARY KEY (`oPaymentId`),
  KEY `FK_pId_researchId_orderId` (`pId`,`researcherId`,`orderId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `progress`
--

DROP TABLE IF EXISTS `progress`;
CREATE TABLE IF NOT EXISTS `progress` (
  `progressId` int NOT NULL AUTO_INCREMENT,
  `fundId` int NOT NULL,
  `pId` int NOT NULL,
  `researcherId` int NOT NULL,
  `document` blob NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`progressId`,`fundId`,`pId`,`researcherId`),
  KEY `FK_pId_researchId_fundId` (`pId`,`researcherId`,`fundId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `researcherId` int NOT NULL,
  `topic` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`pid`,`researcherId`),
  KEY `FK_researcherID` (`researcherId`)
) ENGINE=MyISAM AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`pid`, `researcherId`, `topic`, `status`) VALUES
(10000, 1001, 'Artificial Intelligence', 'Not paid'),
(10001, 1002, 'Machine Learning', 'Not paid');

-- --------------------------------------------------------

--
-- Table structure for table `researcher`
--

DROP TABLE IF EXISTS `researcher`;
CREATE TABLE IF NOT EXISTS `researcher` (
  `researcherId` int NOT NULL,
  PRIMARY KEY (`researcherId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `researcher`
--

INSERT INTO `researcher` (`researcherId`) VALUES
(1001),
(1002);

-- --------------------------------------------------------

--
-- Table structure for table `researcher_payment`
--

DROP TABLE IF EXISTS `researcher_payment`;
CREATE TABLE IF NOT EXISTS `researcher_payment` (
  `rPaymentId` int NOT NULL AUTO_INCREMENT,
  `researcherId` int NOT NULL,
  `amount` float NOT NULL,
  `paymentStatus` varchar(10) NOT NULL,
  PRIMARY KEY (`rPaymentId`),
  KEY `FK_researcherId` (`researcherId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `unfinished`
--

DROP TABLE IF EXISTS `unfinished`;
CREATE TABLE IF NOT EXISTS `unfinished` (
  `pid` int NOT NULL,
  `researcherId` int NOT NULL,
  `clientId` int DEFAULT NULL,
  `requiredAmount` float NOT NULL,
  PRIMARY KEY (`pid`,`researcherId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(10) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `cardNumber` bigint DEFAULT NULL,
  `CVV` int DEFAULT NULL,
  `expDate` date DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userName`, `email`, `password`, `firstName`, `lastName`, `cardNumber`, `CVV`, `expDate`) VALUES
(1001, 'kamal_d', 'kamald@gmail.com', 'kd@123', 'Kamal', 'Dissanayake', 4567893748596782, 4562, '2023-04-12'),
(1000, 'amal_p', 'amalp@gmail.com', 'ap@123', 'Amal', 'Perera', 1678293674893784, 6567, '2022-09-07'),
(1002, 'sunil_h', 'sunilh@gmail.com', 'sh@123', 'Sunil', 'Hewage', 2678935678925673, 8793, '2023-01-11'),
(1003, 'gayasha_f', 'gayashaf@gmail.com', 'gf@123', 'Gayasha', 'Fernando', 3678294678905672, 5646, '2022-11-17'),
(1004, 'tharini_j', 'tharinij@gmail.com', 'tj@123', 'Tharini', 'Jayakody', 5698737892678356, 3456, '2023-03-08');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
