
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

DROP TABLE IF EXISTS `xcolab_ProposalRatingType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xcolab_ProposalRatingType` (
  `id_` bigint(20) NOT NULL,
  `label` varchar(75) DEFAULT NULL,
  `description` longtext,
  `judgeType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xcolab_ProposalRatingType`
--

LOCK TABLES `xcolab_ProposalRatingType` WRITE;
/*!40000 ALTER TABLE `xcolab_ProposalRatingType` DISABLE KEYS */;
INSERT INTO `xcolab_ProposalRatingType` VALUES (1,'Novelty','The degree to which the proposal is original (not only rare but also ingenious, imaginative, or surprising), and modifies a paradigm.',1),(2,'Workability','The degree to which the proposal is appealing (socially, legally and politically) and implementable (technically and economically).',1),(3,'Effectiveness','The degree to which the proposal, if successfully implemented, will be effective at solving the challenge in the contest prompt.',1),(4,'Thoroughness','The degree to which the proposal is worked out in detail and there is a clear relationship between the recommended action and the expected outcome.',1),(5,'Presentation','The degree to which the proposal is presented in a clear, persuasive and appealing manner. ',1),(6,'Novelty','The degree to which the proposal is original (not only rare but also ingenious, imaginative, or surprising), and modifies a paradigm.',2),(7,'Workability','The degree to which the proposal is appealing (socially, legally and politically) and implementable (technically and economically).',2),(8,'Effectiveness','The degree to which the proposal, if successfully implemented, will be effective at solving the challenge in the contest prompt.',2),(9,'Thoroughness','The degree to which the proposal is worked out in detail and there is a clear relationship between the recommended action and the expected outcome.',2),(10,'Presentation','The degree to which the proposal is presented in a clear, persuasive and appealing manner. ',2);
/*!40000 ALTER TABLE `xcolab_ProposalRatingType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xcolab_ProposalRatingValue`
--

DROP TABLE IF EXISTS `xcolab_ProposalRatingValue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xcolab_ProposalRatingValue` (
  `id_` bigint(20) NOT NULL,
  `ratingTypeId` bigint(20) DEFAULT NULL,
  `value` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xcolab_ProposalRatingValue`
--

LOCK TABLES `xcolab_ProposalRatingValue` WRITE;
/*!40000 ALTER TABLE `xcolab_ProposalRatingValue` DISABLE KEYS */;
INSERT INTO `xcolab_ProposalRatingValue` VALUES (1,1,1,'Common, mundane, boring',NULL),(2,1,2,'Interesting, but not unheard of',NULL),(3,1,3,'Unusual, interesting; imaginative',NULL),(4,1,4,'Rare; surprising; challenges paradigms',NULL),(6,2,1,'Infeasible socially, politically, legally or technically ',NULL),(7,2,2,'Challenging; feasibility is questionnable',NULL),(8,2,3,'Acceptable; Objections & barriers partially addressed',NULL),(9,2,4,'Appealing; Potential objections & barriers well addressed ',NULL),(11,3,1,'Benefits/impact not clear',NULL),(12,3,2,'Limited benefits/small impact',NULL),(13,3,3,'Partial solution/moderate impact',NULL),(14,3,4,'Large, direct, & positive impact',NULL),(16,4,1,'Poor. Missing details, unclear',NULL),(17,4,2,'Incomplete. Missing details, cause-effect unclear',NULL),(18,4,3,'Comprehensive. Clarifications needed',NULL),(19,4,4,'Comprehensive. Details worked out',NULL),(21,5,1,'Neither clear, persuasive or appealing',NULL),(22,5,2,'1 of 3: Clear, Persuasive, Appealing',NULL),(23,5,3,'2 of 3: Clear, Persuasive, Appealing',NULL),(24,5,4,'3 of 3: Clear, Persuasive, Appealing',NULL),(26,6,1,'Common, mundane, boring',NULL),(27,6,2,'Interesting, but not unheard of',NULL),(28,6,3,'Unusual, interesting; imaginative',NULL),(29,6,4,'Rare; surprising; challenges paradigms',NULL),(31,7,1,'Infeasible socially, politically, legally or technically ',NULL),(32,7,2,'Challenging; feasibility is questionnable',NULL),(33,7,3,'Acceptable; Objections & barriers partially addressed',NULL),(34,7,4,'Appealing; Potential objections & barriers well addressed ',NULL),(36,8,1,'Benefits/impact not clear',NULL),(37,8,2,'Limited benefits/small impact',NULL),(38,8,3,'Partial solution/moderate impact',NULL),(39,8,4,'Large, direct, & positive impact',NULL),(41,9,1,'Poor. Missing details, unclear',NULL),(42,9,2,'Incomplete. Missing details, cause-effect unclear',NULL),(43,9,3,'Comprehensive. Clarifications needed',NULL),(44,9,4,'Comprehensive. Details worked out',NULL),(46,10,1,'Neither clear, persuasive or appealing',NULL),(47,10,2,'1 of 3: Clear, Persuasive, Appealing',NULL),(48,10,3,'2 of 3: Clear, Persuasive, Appealing',NULL),(49,10,4,'3 of 3: Clear, Persuasive, Appealing',NULL);
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

-- Dump completed on 2014-07-21 12:17:20
