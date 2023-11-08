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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名\n',
  `password` varchar(256) NOT NULL COMMENT '密码\n',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像地址\n',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱\n',
  `account_status` int DEFAULT '0' COMMENT '账户状态(0: 正常; 1: 封禁; 2:注销)\n',
  `create_time` datetime DEFAULT NULL COMMENT '账户创建时间\n',
  `update_time` datetime DEFAULT NULL COMMENT '账户更新时间\n',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间\n',
  `code` varchar(20) NOT NULL COMMENT '员工编码\n',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名\n',
  `sex` int DEFAULT '2' COMMENT '性别(0:女; 1:男; 2:未知)\n',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号\n',
  `status` int DEFAULT '0' COMMENT '员工状态(0:在职; 1:离职; 2:休假)\n',
  `address` varchar(100) DEFAULT NULL COMMENT '员工住址\n',
  `hire_date` datetime DEFAULT NULL COMMENT '入职日期\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1721524995080269827 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1721524949085581314,'bobby','$2a$10$lzS921on7EvnumEis8D0muj9Zk6XK2YC1dPJhQ554bAx1Tgh1j5fK',NULL,NULL,0,'2023-11-06 21:48:27',NULL,NULL,'sf8848','黄准备',2,NULL,0,NULL,NULL),(1721524995080269826,'normaluser','$2a$10$6XgfU0EP.aab/GN.VPKCIONZ8up00OM8bm4v3FY80idaJ5dP6PUZ.',NULL,NULL,0,'2023-11-06 21:48:38',NULL,NULL,'norm123','普通员工',2,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-08 12:01:14
