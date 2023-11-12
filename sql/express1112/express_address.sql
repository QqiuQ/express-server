CREATE DATABASE  IF NOT EXISTS `express` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `express`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: express
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID\n',
  `user_id` bigint NOT NULL COMMENT '用户ID\n',
  `recipient_name` varchar(40) NOT NULL COMMENT '收件人姓名\n',
  `phone` varchar(11) NOT NULL COMMENT '手机号\n',
  `province` varchar(20) NOT NULL COMMENT '省份\n',
  `city` varchar(20) NOT NULL COMMENT '城市\n',
  `country` varchar(20) NOT NULL COMMENT '县\n',
  `street` varchar(20) NOT NULL COMMENT '街道',
  `detail` varchar(100) DEFAULT NULL COMMENT '详细地址\n',
  `postal_code` varchar(6) DEFAULT NULL COMMENT '邮编\n',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间\\\\n',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1723339957293453314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1723190956741025794,1722969958217330689,'黄','888888','山西省','太原市','小店区','上阿达',NULL,'848484','2023-11-11 12:08:34',NULL),(1723323518830415873,1722971623486042113,'huagn','17880340980','福建省','福州市','闽侯县','上街镇',NULL,'888888','2023-11-11 20:55:19',NULL),(1723339957293453313,1722971623486042113,'bobo','17880340980','湖北省','武汉市','洪山区','黄家湖',NULL,'430020','2023-11-11 22:00:39',NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-12 16:53:36
