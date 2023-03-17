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
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_num` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` int(11) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `product_count` int(11) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  `day_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `order_state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_detail_num`),
  KEY `order_num_idx` (`order_num`),
  KEY `product_name_idx` (`product_name`),
  CONSTRAINT `order_num` FOREIGN KEY (`order_num`) REFERENCES `orders` (`order_num`),
  CONSTRAINT `product_name` FOREIGN KEY (`product_name`) REFERENCES `product` (`product_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (6,8,'강아지 덴디케어 치약',0,24000,'2023-01-17 18:41:09','주문취소'),(7,9,'강아지 덴디케어 치약',0,24000,'2023-01-17 18:42:01','주문취소'),(8,10,'보헤리 바삭64 멍멍이 간식 닭 가슴살',0,39000,'2023-01-17 18:58:18','주문취소'),(9,11,'로얄캐닌 독 미니 인도어 퍼피 1.5kg',1,50000,'2023-01-19 11:58:56','주문취소'),(14,16,'강아지 덴디케어 치약',1,24000,'2023-01-19 15:16:41','결제완료'),(15,17,'강아지 덴디케어 치약',1,24000,'2023-01-19 15:17:39','주문취소'),(16,18,'마리앙 코트킹 브러쉬',1,38000,'2023-01-19 15:19:40','결제완료'),(17,19,'맛있는사료 연어 화태사료 참좋은',2,31000,'2023-01-19 16:08:39','결제완료'),(18,20,'맛있는사료 연어 화태사료 참좋은',2,31000,'2023-01-19 16:09:23','주문취소'),(19,21,'큐밸리 노브랜드 츄잉스틱 딸기 요거트',3,21000,'2023-01-19 17:27:42','주문취소'),(20,22,'진수성찬 어덜트 스페셜 5kg',1,33000,'2023-01-19 18:35:12','주문취소'),(21,23,'보헤리 바삭64 멍멍이 간식 한우스틱',7,21000,'2023-01-20 13:58:06','주문취소'),(30,32,'진수성찬 어덜트 스페셜 5kg',3,33000,'2023-01-20 15:53:06','주문취소'),(31,33,'진수성찬 어덜트 스페셜 5kg',3,33000,'2023-01-20 15:53:40','주문취소'),(32,34,'진수성찬 어덜트 스페셜 5kg',5,33000,'2023-01-20 15:53:45','주문취소'),(33,35,'진수성찬 어덜트 스페셜 5kg',5,33000,'2023-01-20 15:55:06','주문취소'),(34,36,'진수성찬 어덜트 스페셜 5kg',5,33000,'2023-01-20 15:55:37','주문취소'),(35,37,'진수성찬 어덜트 스페셜 5kg',7,33000,'2023-01-20 15:56:00','주문취소'),(38,40,'미소 깨끗한 피모를 위한 프리미엄 건강식 2kg',10,30000,'2023-01-20 16:48:28','주문취소'),(45,47,'진수성찬 어덜트 스페셜 5kg',6,33000,'2023-01-20 17:03:14','주문취소'),(46,48,'보헤리 바삭64 멍멍이 간식 한우스틱',3,21000,'2023-01-22 20:30:52','주문취소'),(47,49,'보헤리 바삭64 멍멍이 간식 한우스틱',3,21000,'2023-01-22 20:31:36','주문취소'),(48,50,'미소 깨끗한 피모를 위한 프리미엄 건강식 2kg',5,30000,'2023-01-24 16:19:30','주문취소'),(49,51,'미소 깨끗한 피모를 위한 프리미엄 건강식 2kg',5,30000,'2023-01-24 16:20:28','주문취소'),(50,52,'보헤리 바삭64 멍멍이 간식 한우스틱',1,21000,'2023-01-25 10:37:56','주문취소'),(51,53,'보헤리 바삭64 멍멍이 간식 한우스틱',0,21000,'2023-01-25 13:39:35','주문취소'),(52,54,'보헤리 바삭64 멍멍이 간식 한우스틱',0,21000,'2023-01-25 13:41:15','주문취소'),(53,55,'미소 깨끗한 피모를 위한 프리미엄 건강식 2kg',0,30000,'2023-01-25 13:41:19','주문취소'),(54,56,'크린세이프 기저귀',0,14700,'2023-01-25 13:41:26','주문취소'),(55,57,'프로플랜 센서티브 스킨 앤스토막 어덜트 12kg',0,125000,'2023-01-25 14:39:19','주문취소'),(56,58,'건강한사료 어덜트 개사료 1.5kg',0,79000,'2023-01-25 14:55:32','주문취소'),(57,59,'보헤리 바삭64 멍멍이 간식 한우스틱',0,21000,'2023-01-25 14:57:29','주문취소'),(58,60,'보헤리 바삭64 멍멍이 간식 한우스틱',3,21000,'2023-01-25 15:09:47','결제완료'),(59,61,'반려견사료세이버 엑시전트 2kg',2,97000,'2023-01-25 15:18:55','주문취소'),(60,63,'미소 깨끗한 피모를 위한 프리미엄 건강식 2kg',1,30000,NULL,'결제완료'),(61,64,'미소 깨끗한 피모를 위한 프리미엄 건강식 2kg',1,30000,'2023-01-26 18:49:22','결제완료');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-31 20:52:56
