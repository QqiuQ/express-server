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
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `express_number` varchar(20) NOT NULL COMMENT '物流单号\\n',
  `status` int DEFAULT '2' COMMENT '运单状态(0:已取消; 1:已完成; 2:进行中)\\n',
  `sender_name` varchar(20) NOT NULL COMMENT '寄件人\\n',
  `sender_phone` varchar(11) NOT NULL COMMENT '寄件人电话\\n',
  `sender_address` varchar(200) NOT NULL COMMENT '寄件地址\\n',
  `recipient_name` varchar(20) NOT NULL COMMENT '收件人\\n',
  `recipient_phone` varchar(11) NOT NULL COMMENT '收件电话\\n',
  `recipient_address` varchar(200) NOT NULL COMMENT '收件地址\\n',
  `express_status` int DEFAULT '1' COMMENT '物流状态(0:异常;1:揽收中; 2:运输中; 3: 派送中; 4: 到达网点; 5:已签收)',
  `package_weight` int DEFAULT NULL COMMENT '包裹重量(g)\\n',
  `package_length` int DEFAULT NULL COMMENT '包裹长度\\n(cm)',
  `package_width` int DEFAULT NULL COMMENT '包裹宽度(cm)\\n',
  `express_cost` int DEFAULT NULL COMMENT '快递费用(分)\\n',
  `estimated_delivery_time` datetime DEFAULT NULL COMMENT '预计送达时间\\n',
  `actual_delivery_time` datetime DEFAULT NULL COMMENT '实际送达时间\\n',
  `courier_name` varchar(20) DEFAULT NULL COMMENT '快递员姓名\\n',
  `courier_phone` varchar(11) DEFAULT NULL COMMENT '快递员手机\\n',
  `courier_code` varchar(20) DEFAULT NULL COMMENT '快递员编号\\n',
  `description` varchar(200) DEFAULT NULL COMMENT '快递描述\\n',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注\\n',
  `recipient_signature` varchar(20) DEFAULT NULL COMMENT '签收人\\n',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间\\n',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间\\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `express_number_UNIQUE` (`express_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1723600402998763523 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物流订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1722441425401344002,'35270328590667776',0,'黄22','88888811','福州大学','五','1788','上海',1,NULL,NULL,NULL,NULL,NULL,NULL,'li','1313333333','888844',NULL,NULL,NULL,NULL,NULL),(1722494320553398273,'35323224011182080',2,'黄','1788888888','福州大学','吴','88888884','上海',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-09 14:00:23',NULL),(1723208657505009666,'36037560975364096',2,'黄还好','17988','福建省福州市闽侯县上街','丽丽i','54654','河北省石家庄市长安区',1,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','2023-11-11 13:18:54',NULL),(1723208680301051905,'36037583742046208',2,'黄还好','17988','福建省福州市闽侯县上街上街','丽丽i','54654','河北省石家庄市长安区',1,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','2023-11-11 13:19:00',NULL),(1723315978516205570,'36144881932046336',0,'user','17880340980','福建省福州市闽侯县','hhhh','17880340980','河北省石家庄市长安区',1,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','2023-11-11 20:25:22',NULL),(1723347715384315906,'36176618833711104',2,'huagn','17880340980','福建省/福州市/闽侯县/上街镇','bobo','17880340980','湖北省/武汉市/洪山区/黄家湖',2,NULL,NULL,NULL,NULL,NULL,'2023-11-12 15:18:57','','18888','000003','','','','2023-11-11 22:31:28','2023-11-12 15:18:57'),(1723392379000918017,'36221282408337408',1,'bobo','17880340980','湖北省/武汉市/洪山区/黄家湖','hhhhhh','17880340980','湖北省/武汉市/洪山区/黄家湖',3,NULL,NULL,NULL,NULL,NULL,'2023-11-12 15:11:47','','18888','000003','','','','2023-11-12 01:28:57','2023-11-12 15:11:47'),(1723600402998763522,'36429306477547520',2,'huagn','17880340980','福建省/福州市/闽侯县/上街镇','bobo','17880340980','湖北省/武汉市/洪山区/黄家湖',1,NULL,NULL,NULL,NULL,NULL,NULL,'','','','','','','2023-11-12 15:15:34',NULL);
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
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
