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
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID\n',
  `phone` varchar(11) NOT NULL COMMENT '手机号\n',
  `province` varchar(20) NOT NULL COMMENT '省份\n',
  `city` varchar(20) NOT NULL COMMENT '城市\n',
  `country` varchar(20) NOT NULL COMMENT '县\n',
  `street` varchar(20) NOT NULL COMMENT '街道\n',
  `detail` varchar(100) DEFAULT NULL COMMENT '详细地址\n',
  `station_name` varchar(40) NOT NULL COMMENT '网点名称\n',
  `station_manager` varchar(20) DEFAULT NULL COMMENT '网点经理\n',
  `manager_phone` varchar(11) DEFAULT NULL COMMENT '经理手机号\n',
  `landline_number` varchar(12) DEFAULT NULL COMMENT '网点座机\n',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间\n',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1722923144885714946 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1722462136996147202,'sadf','河北省','邢台市','内丘县','sdf',NULL,'sdfsad','fsdf','sdf',NULL,NULL,NULL),(1722874497603178498,'sadfasd','天津市','市辖区','河西区','sadf',NULL,'asdf','asdf','adsfds',NULL,NULL,NULL),(1722923144885714945,'23406888','福建省','福州市','闽侯县','上街镇',NULL,'福州大学快递服务中心','李乾坤','17880340888',NULL,NULL,NULL);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-10 22:01:01
