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
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `account_id` bigint NOT NULL COMMENT '账户ID(员工ID、用户ID)\n',
  `role_id` int NOT NULL COMMENT '角色ID\n',
  `type` varchar(10) NOT NULL COMMENT '账户类型("user": 用户; "employee": 员工)\n',
  PRIMARY KEY (`account_id`,`role_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1721524122505601025,-1967681535,'USER'),(1721524949085581314,-650670078,'EMPLOYEE'),(1721524995080269826,-717778942,'EMPLOYEE');
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

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
  `create_time` datetime NOT NULL COMMENT '创建时间\n',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(45) NOT NULL COMMENT '角色名称\n',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间\n',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (-1967681535,'ROLE_USER','2023-11-06 21:49:18',NULL),(-717778942,'ROLE_EMPLOYEE','2023-11-06 21:49:18',NULL),(-717778941,'ROLE_DELIVERY_MAN','2023-11-06 21:49:18',NULL),(-650670079,'ROLE_STATION_ADMIN','2023-11-06 21:49:18',NULL),(-650670078,'ROLE_SUPER_ADMIN','2023-11-06 21:49:18',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station_employee`
--

DROP TABLE IF EXISTS `station_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID\n',
  `station_id` bigint NOT NULL COMMENT '网点ID',
  `employee_id` bigint NOT NULL COMMENT '员工ID',
  `position` varchar(40) DEFAULT '员工' COMMENT '职位（网点快递员、工作人员...）',
  `status` int DEFAULT '0' COMMENT '员工状态(0:在职; 1:离职; 2:休假)  p.s 与employee表重复',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='站点员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_employee`
--

LOCK TABLES `station_employee` WRITE;
/*!40000 ALTER TABLE `station_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `station_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station_order`
--

DROP TABLE IF EXISTS `station_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `station_id` bigint NOT NULL COMMENT '网点ID',
  `status` int DEFAULT '0' COMMENT '网点运单状态（0:待取件; 1:已出库; 2:异常）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='站点运单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_order`
--

LOCK TABLES `station_order` WRITE;
/*!40000 ALTER TABLE `station_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `station_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名\\n',
  `password` varchar(256) NOT NULL COMMENT '密码\\n',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像地址\\n',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱\\n',
  `account_status` int DEFAULT '0' COMMENT '账户状态(0: 正常; 1: 封禁; 2:注销)\\n',
  `create_time` datetime DEFAULT NULL COMMENT '账户创建时间\\n',
  `update_time` datetime DEFAULT NULL COMMENT '账户更新时间\\n',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间\\n',
  `nickname` varchar(45) DEFAULT NULL COMMENT '昵称\n',
  `sex` int DEFAULT '2' COMMENT '性别(0:女; 1:男; 2:未知)\n',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号\n',
  `birthday` date DEFAULT NULL COMMENT '生日\n',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间\n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1721524221306634244 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1721524122505601025,'vividbobo','$2a$10$uQeh.YYXpgnXjDndwMajA./SgdkMA3GjgSJJ2rOlXVagK4GGKYUrO',NULL,'abc@163.com',0,NULL,NULL,NULL,'vividbobby',1,'178888',NULL,NULL),(1721524221180805122,'user#1','$2a$10$9.TWFKb8DFIwkFMIqA1y7uvoIutjenuoxo/UxMnQczM1iaO.xih4W',NULL,NULL,0,NULL,NULL,NULL,'user111',1,NULL,NULL,NULL),(1721524221180805123,'user#2','$2a$10$ZbgdHqmkckgQ6fE/NDRmTOZGAfSOJPsIFEHwCPtMcvWerzudlX0Si',NULL,NULL,0,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL),(1721524221180805124,'user#3','$2a$10$aiFGi2y9Z1TAhc9u6in8oeY7MEuAHXDnFFyqLP4Wwzv2SB2CEwE9W',NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(1721524221180805125,'user#4','$2a$10$8po597NN8WHOA7F.bp3Jq.n8E4oBNgfwLzi0QygoAmbzNelVPoXhK',NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(1721524221243719682,'user#5','$2a$10$tcJHE4rdI5uBow2tpVy72OanAQsCvMyIHkDHJK3PSbW.tmGZuurWW',NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(1721524221243719683,'user#6','$2a$10$WB3eJZb4pQGWEuff48M.OeDPDxrqVbMtRtonqogEHPBwBwquHvd8y',NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(1721524221243719684,'user#7','$2a$10$WIvVpRQVtvKm2bG/9GoFse6DevUSo3MD95X/jtKEqh5Jb2Ejoae2.',NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(1721524221306634242,'user#8','$2a$10$5YD132DlwXPoplWwaOF3cuhf5jQJhGduQE8oYJNa0UEPpuvje1X7e',NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL),(1721524221306634243,'user#9','$2a$10$6gLD.yY71DAgAujJmEyMm.kVOkx6jMg9Pd0dbQpU1Ms3/YiiByCmi',NULL,NULL,0,NULL,NULL,NULL,NULL,2,NULL,NULL,NULL);
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

-- Dump completed on 2023-11-08 12:01:27
