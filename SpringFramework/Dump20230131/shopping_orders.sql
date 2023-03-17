CREATE DATABASE  IF NOT EXISTS `shopping` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `shopping`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shopping
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_num` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `day_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_num`),
  KEY `user_email_idx` (`email`),
  CONSTRAINT `user_email` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'admin@email.com','2023-01-11 16:30:53'),(7,'admin@email.com','2023-01-17 18:38:00'),(8,'admin@email.com','2023-01-17 18:41:09'),(9,'admin@email.com','2023-01-17 18:42:01'),(10,'admin@email.com','2023-01-17 18:58:17'),(11,'admin@email.com','2023-01-19 11:58:56'),(12,'admin@email.com','2023-01-19 14:34:42'),(13,'admin@email.com','2023-01-19 14:46:53'),(14,'admin@email.com','2023-01-19 14:47:36'),(15,'admin@email.com','2023-01-19 14:52:18'),(16,'sss@ssss.com','2023-01-19 15:16:41'),(17,'sss@ssss.com','2023-01-19 15:17:39'),(18,'sss@ssss.com','2023-01-19 15:19:40'),(19,'sss@ssss.com','2023-01-19 16:08:39'),(20,'sss@ssss.com','2023-01-19 16:09:23'),(21,'admin@email.com','2023-01-19 17:27:42'),(22,'admin@email.com','2023-01-19 18:35:12'),(23,'admin@email.com','2023-01-20 13:58:06'),(24,'admin@email.com','2023-01-20 13:59:08'),(25,'admin@email.com','2023-01-20 14:27:23'),(26,'admin@email.com','2023-01-20 14:27:33'),(27,'admin@email.com','2023-01-20 14:43:39'),(28,'admin@email.com','2023-01-20 15:05:18'),(29,'admin@email.com','2023-01-20 15:17:42'),(30,'admin@email.com','2023-01-20 15:26:53'),(31,'admin@email.com','2023-01-20 15:32:39'),(32,'admin@email.com','2023-01-20 15:53:05'),(33,'admin@email.com','2023-01-20 15:53:40'),(34,'admin@email.com','2023-01-20 15:53:45'),(35,'admin@email.com','2023-01-20 15:55:06'),(36,'admin@email.com','2023-01-20 15:55:37'),(37,'admin@email.com','2023-01-20 15:55:59'),(38,'admin@email.com','2023-01-20 16:35:51'),(39,'admin@email.com','2023-01-20 16:41:23'),(40,'admin@email.com','2023-01-20 16:48:28'),(41,'admin@email.com','2023-01-20 16:51:06'),(42,'admin@email.com','2023-01-20 16:53:00'),(43,'admin@email.com','2023-01-20 16:54:09'),(44,'admin@email.com','2023-01-20 16:57:04'),(45,'admin@email.com','2023-01-20 16:59:08'),(46,'admin@email.com','2023-01-20 17:00:39'),(47,'admin@email.com','2023-01-20 17:03:14'),(48,'admin@email.com','2023-01-22 20:30:52'),(49,'admin@email.com','2023-01-22 20:31:36'),(50,'admin@email.com','2023-01-24 16:19:30'),(51,'admin@email.com','2023-01-24 16:20:28'),(52,'admin@email.com','2023-01-25 10:37:55'),(53,'admin@email.com','2023-01-25 13:39:35'),(54,'admin@email.com','2023-01-25 13:41:14'),(55,'admin@email.com','2023-01-25 13:41:19'),(56,'admin@email.com','2023-01-25 13:41:26'),(57,'admin@email.com','2023-01-25 14:39:19'),(58,'admin@email.com','2023-01-25 14:55:32'),(59,'admin@email.com','2023-01-25 14:57:29'),(60,'admin@email.com','2023-01-25 15:09:47'),(61,'admin@email.com','2023-01-25 15:18:55'),(62,'admin@email.com','2023-01-25 17:38:39'),(63,'admin@email.com','2023-01-26 18:49:00'),(64,'admin@email.com','2023-01-26 18:49:22');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-31 20:52:55
