-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: mirifici
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

USE sys;

DROP DATABASE IF EXISTS mirifici;

CREATE DATABASE mirifici;

USE mirifici;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `idAddress` int(9) NOT NULL AUTO_INCREMENT,
  `rua` varchar(50) NOT NULL,
  `numero` int(6) NOT NULL,
  `complemento` varchar(50) NOT NULL,
  `bairro` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `CEP` int(8) NOT NULL,
  `UF` varchar(2) NOT NULL,
  `idCustomer` int(8) NOT NULL,
  PRIMARY KEY (`idAddress`),
  KEY `fk_address_001` (`idCustomer`),
  CONSTRAINT `fk_address_001` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auctionDetails`
--

DROP TABLE IF EXISTS `auctionDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auctionDetails` (
  `idAuctionDetail` int(9) NOT NULL AUTO_INCREMENT,
  `throw` double(10,2) NOT NULL,
  `throwDate` date NOT NULL,
  `idAuction` int(9) NOT NULL,
  `idCustomer` int(9) NOT NULL,
  PRIMARY KEY (`idAuctionDetail`),
  KEY `fk_auctionDetails_001` (`idAuction`),
  KEY `fk_auctionDetails_002` (`idCustomer`),
  CONSTRAINT `fk_auctionDetails_001` FOREIGN KEY (`idAuction`) REFERENCES `auctions` (`idAuction`),
  CONSTRAINT `fk_auctionDetails_002` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctionDetails`
--

LOCK TABLES `auctionDetails` WRITE;
/*!40000 ALTER TABLE `auctionDetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `auctionDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auctions`
--

DROP TABLE IF EXISTS `auctions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auctions` (
  `idAuction` int(9) NOT NULL AUTO_INCREMENT,
  `minIncrementValue` double(10,2) NOT NULL,
  `initAuctionDate` date NOT NULL,
  `finalAuctionDate` date NOT NULL,
  `idProduct` int(9) NOT NULL,
  PRIMARY KEY (`idAuction`),
  KEY `fk_auctions_001` (`idProduct`),
  CONSTRAINT `fk_auctions_001` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auctions`
--

LOCK TABLES `auctions` WRITE;
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auctions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autentication`
--

DROP TABLE IF EXISTS `autentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autentication` (
  `idAutentication` int(9) NOT NULL AUTO_INCREMENT,
  `email` varchar(80) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(70) NOT NULL,
  `salt` varchar(70) NOT NULL,
  `idCustomer` int(9) NOT NULL,
  PRIMARY KEY (`idAutentication`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `login` (`login`),
  KEY `fk_autentication_001` (`idCustomer`),
  CONSTRAINT `fk_autentication_001` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autentication`
--

LOCK TABLES `autentication` WRITE;
/*!40000 ALTER TABLE `autentication` DISABLE KEYS */;
/*!40000 ALTER TABLE `autentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `idContact` int(9) NOT NULL AUTO_INCREMENT,
  `ddd` int(2) NOT NULL,
  `number` int(9) NOT NULL,
  `mobile` tinyint(1) NOT NULL,
  `idCustomer` int(8) NOT NULL,
  PRIMARY KEY (`idContact`),
  KEY `fk_contacts_001` (`idCustomer`),
  CONSTRAINT `fk_contacts_001` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `idCustomer` int(9) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `bornDate` date NOT NULL,
  `nick` varchar(30) NOT NULL,
  PRIMARY KEY (`idCustomer`),
  UNIQUE KEY `cpf` (`cpf`),
  UNIQUE KEY `nick` (`nick`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `idProduct` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `dateRegister` date NOT NULL,
  `value` double(10,2) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productsImages`
--

DROP TABLE IF EXISTS `productsImages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productsImages` (
  `idProductsImages` int(9) NOT NULL AUTO_INCREMENT,
  `linkImage` varchar(80) NOT NULL,
  `idProduct` int(9) NOT NULL,
  PRIMARY KEY (`idProductsImages`),
  UNIQUE KEY `linkImage` (`linkImage`),
  KEY `fk_productsImages_001` (`idProduct`),
  CONSTRAINT `fk_productsImages_001` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productsImages`
--

LOCK TABLES `productsImages` WRITE;
/*!40000 ALTER TABLE `productsImages` DISABLE KEYS */;
/*!40000 ALTER TABLE `productsImages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `idSales` int(9) NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  `value` double(10,2) NOT NULL,
  `payoutDate` date DEFAULT NULL,
  `idProduct` int(9) NOT NULL,
  `idCustomer` int(9) NOT NULL,
  PRIMARY KEY (`idSales`),
  KEY `fk_sales_001` (`idProduct`),
  KEY `fk_sales_002` (`idCustomer`),
  CONSTRAINT `fk_sales_001` FOREIGN KEY (`idProduct`) REFERENCES `products` (`idProduct`),
  CONSTRAINT `fk_sales_002` FOREIGN KEY (`idCustomer`) REFERENCES `customers` (`idCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-07  3:12:21
