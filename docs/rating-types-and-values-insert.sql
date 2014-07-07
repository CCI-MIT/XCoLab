-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: xcolab
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `xcolab_ProposalRatingType`
--

--
-- Dumping data for table `xcolab_ProposalRatingType`
--

LOCK TABLES `xcolab_ProposalRatingType` WRITE;
/*!40000 ALTER TABLE `xcolab_ProposalRatingType` DISABLE KEYS */;
INSERT INTO `xcolab_ProposalRatingType` VALUES (1,'Novelty',1),(2,'Relevance/Impact',1),(3,'Feasibility',1),(4,'Thoroughness',1),(5,'Presentation',1),(6,'Novelty',2),(7,'Relevance/Impact',2),(8,'Feasibility',2),(9,'Thoroughness',2),(10,'Presentation',2);
/*!40000 ALTER TABLE `xcolab_ProposalRatingType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xcolab_ProposalRatingValue`
--


--
-- Dumping data for table `xcolab_ProposalRatingValue`
--

LOCK TABLES `xcolab_ProposalRatingValue` WRITE;
/*!40000 ALTER TABLE `xcolab_ProposalRatingValue` DISABLE KEYS */;
INSERT INTO `xcolab_ProposalRatingValue` VALUES (1,1,1,'poor',''),(2,1,2,'fair',''),(3,1,3,'good',''),(4,1,4,'very good',''),(5,1,5,'outstanding',''),(6,2,1,'poor',''),(7,2,2,'fair',''),(8,2,3,'good',''),(9,2,4,'very good',''),(10,2,5,'outstanding',''),(11,3,1,'poor',''),(12,3,2,'fair',''),(13,3,3,'good',''),(14,3,4,'very good',''),(15,3,5,'outstanding',''),(16,4,1,'poor',''),(17,4,2,'fair',''),(18,4,3,'good',''),(19,4,4,'very good',''),(20,4,5,'outstanding',''),(21,5,1,'poor',''),(22,5,2,'fair',''),(23,5,3,'good',''),(24,5,4,'very good',''),(25,5,5,'outstanding',''),(26,6,1,'poor',''),(27,6,2,'fair',''),(28,6,3,'good',''),(29,6,4,'very good',''),(30,6,5,'outstanding',''),(31,7,1,'poor',''),(32,7,2,'fair',''),(33,7,3,'good',''),(34,7,4,'very good',''),(35,7,5,'outstanding',''),(36,8,1,'poor',''),(37,8,2,'fair',''),(38,8,3,'good',''),(39,8,4,'very good',''),(40,8,5,'outstanding',''),(41,9,1,'poor',''),(42,9,2,'fair',''),(43,9,3,'good',''),(44,9,4,'very good',''),(45,9,5,'outstanding',''),(46,10,1,'poor',''),(47,10,2,'fair',''),(48,10,3,'good',''),(49,10,4,'very good',''),(50,10,5,'outstanding','');
/*!40000 ALTER TABLE `xcolab_ProposalRatingValue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-24 19:12:14
