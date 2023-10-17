-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: securityjwt
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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `level` int DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '员工状态：0在职，1休假，2离职',
  `address` varchar(64) DEFAULT NULL,
  `account_status` int DEFAULT '0' COMMENT '账户状态：0正常；1封禁；2注销',
  `hire_date` date DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1714188902462857219 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1714188843180552193,'norm123','普通员工',NULL,NULL,NULL,'normaluser','$2a$10$OgACkqnWMmDzhRcqEhfRLeXc24iN8BaS7425IBAzcrdQ6vkEoTYaW',NULL,NULL,NULL,NULL,0,NULL,NULL,'2023-10-17 15:57:23',NULL),(1714188902462857218,'sf8848','黄准备',NULL,NULL,NULL,'bobby','$2a$10$wd/Zuux6rzOtKzpe.i8L/.qPXQTBvXuX6VKNR6s8JbmQ42Pk8KT2e',NULL,NULL,NULL,NULL,0,NULL,NULL,'2023-10-17 15:57:37',NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id_idx` (`employee_id`),
  KEY `role_id_idx` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1714204641185935363 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (1714204641131409409,1714188902462857218,309735426,'2023-10-17 17:00:09',NULL),(1714204641185935362,1714188843180552193,1387671554,'2023-10-17 17:00:10',NULL);
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `domain` varchar(45) DEFAULT NULL,
  `permission` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1790382083 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (-1342808063,309735426,'user','read','2023-10-17 15:49:32',NULL),(-1342808062,309735426,'user','edit','2023-10-17 15:49:32',NULL),(-1342808061,309735426,'user','add','2023-10-17 15:49:32',NULL),(-1284087807,309735426,'user','delete','2023-10-17 15:49:32',NULL),(-1271504894,1387671554,'user','read','2023-10-17 15:49:32',NULL);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1387671555 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (309735426,'ROLE_SUPER_ADMIN','2023-10-17 14:47:21',NULL),(1341534210,'ROLE_STATION_ADMIN','2023-10-17 14:47:21',NULL),(1341534211,'ROLE_DELIVERY_MAN','2023-10-17 14:47:21',NULL),(1387671554,'ROLE_NORMAL_EMPLOYEE','2023-10-17 14:47:21',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nickname` varchar(45) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `avatar` varchar(45) DEFAULT NULL,
  `account_status` int DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1711636840390742019 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1711636586933137410,NULL,NULL,NULL,'user#0','user#0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636587805552642,NULL,NULL,NULL,'user#1','user#1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636587872661505,NULL,NULL,NULL,'user#2','user#2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636587872661506,NULL,NULL,NULL,'user#3','user#3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636587872661507,NULL,NULL,NULL,'user#4','user#4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636587935576066,NULL,NULL,NULL,'user#5','user#5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636587935576067,NULL,NULL,NULL,'user#6','user#6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636587935576068,NULL,NULL,NULL,'user#7','user#7',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(1711636840390742018,NULL,NULL,NULL,'vividbobo','$2a$10$iG3LleSNDLFL88BkBUmqgObELrlMJD3K5UnIZulkJYFwUn90zsU3O',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-17 17:31:01
