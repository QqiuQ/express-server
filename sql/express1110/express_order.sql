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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `express_number` varchar(20) NOT NULL COMMENT '物流单号\n',
  `status` int DEFAULT '2' COMMENT '运单状态(0:已取消; 1:已完成; 2:进行中)\n',
  `sender_name` varchar(20) NOT NULL COMMENT '寄件人\n',
  `sender_phone` varchar(11) NOT NULL COMMENT '寄件人电话\n',
  `sender_address` varchar(200) NOT NULL COMMENT '寄件地址\n',
  `recipient_name` varchar(20) NOT NULL COMMENT '收件人\n',
  `recipient_phone` varchar(11) NOT NULL COMMENT '收件电话\n',
  `recipient_address` varchar(200) NOT NULL COMMENT '收件地址\n',
  `express_status` int DEFAULT '1' COMMENT '物流状态(0:异常;1:揽收中; 2:运输中; 3: 派送中; 4: 到达网点; 5:已签收)',
  `package_weight` int DEFAULT NULL COMMENT '包裹重量(g)\n',
  `package_length` int DEFAULT NULL COMMENT '包裹长度\n(cm)',
  `package_width` int DEFAULT NULL COMMENT '包裹宽度(cm)\n',
  `express_cost` int DEFAULT NULL COMMENT '快递费用(分)\n',
  `estimated_delivery_time` datetime DEFAULT NULL COMMENT '预计送达时间\n',
  `actual_delivery_time` datetime DEFAULT NULL COMMENT '实际送达时间\n',
  `courier_name` varchar(20) DEFAULT NULL COMMENT '快递员姓名\n',
  `courier_phone` varchar(11) DEFAULT NULL COMMENT '快递员手机\n',
  `courier_code` varchar(20) DEFAULT NULL COMMENT '快递员编号\n',
  `description` varchar(200) DEFAULT NULL COMMENT '快递描述\n',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注\n',
  `recipient_signature` varchar(20) DEFAULT NULL COMMENT '签收人\n',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间\n',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `express_number_UNIQUE` (`express_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-10 22:01:00
