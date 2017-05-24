-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: rosquete_db
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `commissioned_payment_classification`
--

DROP TABLE IF EXISTS `commissioned_payment_classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commissioned_payment_classification` (
  `ci_employee` int(11) NOT NULL,
  `commissionRate` float DEFAULT NULL,
  `monthlySalary` float DEFAULT NULL,
  PRIMARY KEY (`ci_employee`),
  CONSTRAINT `fk_cpc_ci_employee` FOREIGN KEY (`ci_employee`) REFERENCES `employee` (`ci_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commissioned_payment_classification`
--

LOCK TABLES `commissioned_payment_classification` WRITE;
/*!40000 ALTER TABLE `commissioned_payment_classification` DISABLE KEYS */;
INSERT INTO `commissioned_payment_classification` VALUES (222,100,500),(223,100.5,250.5);
/*!40000 ALTER TABLE `commissioned_payment_classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `ci_employee` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `payment_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ci_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (111,'Cris Redfield','Redfield','Standford St.','hourly'),(222,'Leon S. Kennedy','Kennedy','Raccoon City','commission'),(223,'Nemesis','Undefined','Umbrella','commission'),(333,'Sheva Alomar','Alomar','Shambala St.','salary'),(334,'Carlos Oliveira','Undefined','Raccoon City','salary'),(444,'Ada Wong','Wong','Tokio St.','hourly'),(555,'Camila Cabello','Cabello','Miami','hourly'),(666,'Lauren Jauregui','Undefined','Miami Florida','hourly'),(777,'Albert Wesker','Undefined','Umbrella','hourly'),(789,'Camren is real :v','Undefined','En mis sue;os','hourly'),(888,'Salazar','Undefined','Salazar Castle','hourly'),(998,'Sadler','Undefined','Spain St.','salary'),(999,'Claire Redfield','Undefined','Standford St.','hourly'),(1235,'Daniel Illanes','Undefined','Av. America y Libertador','hourly');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hourly_payment_classification`
--

DROP TABLE IF EXISTS `hourly_payment_classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hourly_payment_classification` (
  `ci_employee` int(11) NOT NULL,
  `hourlyRate` float DEFAULT NULL,
  PRIMARY KEY (`ci_employee`),
  CONSTRAINT `fk_hpc_ci_employee` FOREIGN KEY (`ci_employee`) REFERENCES `employee` (`ci_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hourly_payment_classification`
--

LOCK TABLES `hourly_payment_classification` WRITE;
/*!40000 ALTER TABLE `hourly_payment_classification` DISABLE KEYS */;
INSERT INTO `hourly_payment_classification` VALUES (111,60),(444,60),(555,45.5),(666,50),(777,70),(789,1000),(888,80.5),(999,60.5),(1235,70);
/*!40000 ALTER TABLE `hourly_payment_classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salaried_classification`
--

DROP TABLE IF EXISTS `salaried_classification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salaried_classification` (
  `ci_employee` int(11) NOT NULL,
  `salary` float DEFAULT NULL,
  PRIMARY KEY (`ci_employee`),
  CONSTRAINT `fk_sc_ci_employee` FOREIGN KEY (`ci_employee`) REFERENCES `employee` (`ci_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salaried_classification`
--

LOCK TABLES `salaried_classification` WRITE;
/*!40000 ALTER TABLE `salaried_classification` DISABLE KEYS */;
INSERT INTO `salaried_classification` VALUES (333,2550.55),(334,5000),(998,5500.5);
/*!40000 ALTER TABLE `salaried_classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_receipt`
--

DROP TABLE IF EXISTS `sales_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_receipt` (
  `id_sales_receipt` int(11) NOT NULL,
  `ci_employee` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id_sales_receipt`),
  KEY `fk_sr_ci_employee` (`ci_employee`),
  CONSTRAINT `fk_sr_ci_employee` FOREIGN KEY (`ci_employee`) REFERENCES `commissioned_payment_classification` (`ci_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_receipt`
--

LOCK TABLES `sales_receipt` WRITE;
/*!40000 ALTER TABLE `sales_receipt` DISABLE KEYS */;
INSERT INTO `sales_receipt` VALUES (1,222,200,'2017-05-19');
/*!40000 ALTER TABLE `sales_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_card`
--

DROP TABLE IF EXISTS `time_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_card` (
  `id_timecard` varchar(45) NOT NULL,
  `ci_employee` int(11) DEFAULT NULL,
  `hours` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id_timecard`),
  KEY `fk_tc_ci_employee` (`ci_employee`),
  CONSTRAINT `fk_tc_ci_employee` FOREIGN KEY (`ci_employee`) REFERENCES `hourly_payment_classification` (`ci_employee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_card`
--

LOCK TABLES `time_card` WRITE;
/*!40000 ALTER TABLE `time_card` DISABLE KEYS */;
INSERT INTO `time_card` VALUES ('1',111,2,'2017-05-22'),('2',111,2,'2017-05-23');
/*!40000 ALTER TABLE `time_card` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-24 20:48:50
