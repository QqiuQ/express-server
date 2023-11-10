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
  `role_id` int DEFAULT NULL COMMENT '角色ID\\n',
  `type` varchar(10) NOT NULL COMMENT '账户类型("user": 用户; "employee": 员工)\n',
  PRIMARY KEY (`account_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1722969958217330689,-650670078,'EMPLOYEE'),(1722969959542730754,-650670079,'EMPLOYEE'),(1722969959869886465,-717778941,'EMPLOYEE'),(1722969960125739010,-650670078,'EMPLOYEE'),(1722969960452894721,-717778942,'EMPLOYEE'),(1722970233762115585,-717778942,'EMPLOYEE'),(1722970235108487169,-717778942,'EMPLOYEE'),(1722970235435642881,-717778942,'EMPLOYEE'),(1722970235695689730,-717778942,'EMPLOYEE'),(1722970236027039745,-717778942,'EMPLOYEE'),(1722970236308058113,-717778942,'EMPLOYEE'),(1722970236610048001,-717778942,'EMPLOYEE'),(1722970236878483458,-717778942,'EMPLOYEE'),(1722970237205639170,-717778942,'EMPLOYEE'),(1722970237469880322,-717778942,'EMPLOYEE'),(1722970237729927170,-717778942,'EMPLOYEE'),(1722970238057082881,-717778942,'EMPLOYEE'),(1722970238321324034,-717778942,'EMPLOYEE'),(1722970238652674050,-717778942,'EMPLOYEE'),(1722970238912720898,-717778942,'EMPLOYEE'),(1722970239172767745,-717778942,'EMPLOYEE'),(1722970239499923457,-717778942,'EMPLOYEE'),(1722970239755776002,-717778942,'EMPLOYEE'),(1722970240087126018,-717778942,'EMPLOYEE'),(1722970240347172865,-717778942,'EMPLOYEE'),(1722971622093533186,-1967681535,'USER'),(1722971623486042113,-1967681535,'USER'),(1722972920209674241,-1967681535,'USER'),(1722972921627348994,-1967681535,'USER'),(1722972921954504705,-1967681535,'USER'),(1722972922277466113,-1967681535,'USER'),(1722972922537512961,-1967681535,'USER'),(1722972922864668674,-1967681535,'USER'),(1722972923196018690,-1967681535,'USER'),(1722972923460259842,-1967681535,'USER'),(1722972923787415554,-1967681535,'USER'),(1722972924051656706,-1967681535,'USER'),(1722972924378812417,-1967681535,'USER'),(1722972924638859266,-1967681535,'USER'),(1722972924966014977,-1967681535,'USER'),(1722972925226061825,-1967681535,'USER'),(1722972925549023234,-1967681535,'USER'),(1722972925813264385,-1967681535,'USER'),(1722972926144614401,-1967681535,'USER'),(1722972926408855553,-1967681535,'USER'),(1722972926727622657,-1967681535,'USER'),(1722972926991863809,-1967681535,'USER');
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
  `create_time` datetime DEFAULT NULL COMMENT '创建时间\\\\n',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间\n',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1722241494719918082 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=1722494320553398274 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物流订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1722441425401344002,'35270328590667776',0,'黄','88888811','福州大学','五','1788','上海',1,NULL,NULL,NULL,NULL,NULL,NULL,'li','1313333333','888844',NULL,NULL,NULL,NULL,NULL),(1722494320553398273,'35323224011182080',2,'黄','1788888888','福州大学','吴','88888884','上海',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-11-09 14:00:23',NULL);
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1722970240347172866 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1722969958217330689,'bobby','$2a$10$7VYV89bPklcgFvGVWm2Z4.O6n3j6jU5jHKjIJvEdoR.6c4zQuL2Me',NULL,'huangbobby@gmail.com',0,'2023-11-10 21:30:24','2023-11-10 21:49:02','2023-11-10 21:43:31','000001','黄',0,'178888',0,NULL,'2023-11-08 00:00:00'),(1722969959542730754,'station','$2a$10$FCMkxHYWckrScrdrrtrynOU8bK8jpJAzkcn6WGUtMik8jzLuuT45W',NULL,NULL,0,'2023-11-10 21:30:24',NULL,NULL,'000002',NULL,2,NULL,0,NULL,NULL),(1722969959869886465,'deliveryman','$2a$10$72SfCArewBvIX7E.CiewtuocKDOLpA1aIeugfnBMFMZJ5t4OrNItG',NULL,NULL,0,'2023-11-10 21:30:24',NULL,NULL,'000003',NULL,2,NULL,0,NULL,NULL),(1722969960125739010,'admin','$2a$10$Cp7dnYuj5D66CzL5pE4oB.wp8NQQrWV.jSDUWuSCrAyxAFzP4oEnW',NULL,NULL,0,'2023-11-10 21:30:24',NULL,NULL,'000004',NULL,2,NULL,0,NULL,NULL),(1722969960452894721,'employee','$2a$10$6RfTiswz/k3/l3V/N7MoGeVaRvfxYXjXwhi6m2Aip9/G0GqNOSyj.',NULL,NULL,0,'2023-11-10 21:30:24',NULL,NULL,'000005',NULL,2,NULL,0,NULL,NULL),(1722970233762115585,'emp#0','$2a$10$mHf7k/XIBlsENGJYTYMXB.zZKSfB2d3IHD.mVa6a.jcVEahaK.1G.',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00000',NULL,2,NULL,0,NULL,NULL),(1722970235108487169,'emp#1','$2a$10$CLb287rcZrBoEb0qPWzUzO9/hTtNbx6ENfYTeR5OjV.Ps/r/Dnjfa',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00001',NULL,2,NULL,0,NULL,NULL),(1722970235435642881,'emp#2','$2a$10$bTybLbFrLGbcSBUSIhmmxe.Q1Ii5/1cct/2.6TW9g2XHj.7s239y2',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00002',NULL,2,NULL,0,NULL,NULL),(1722970235695689730,'emp#3','$2a$10$f8A/RAva5JE5fvJMnKyZ2eOJrV/DG6X5iNN.dm8Udsox6Pq9/r95u',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00003',NULL,2,NULL,0,NULL,NULL),(1722970236027039745,'emp#4','$2a$10$4IFJVikvggao./U7ZRp99eM2RgFrJseTOwAb9lcz.y1hmqr001B1S',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00004',NULL,2,NULL,0,NULL,NULL),(1722970236308058113,'emp#5','$2a$10$z3.XB9ehWpd4C4dIoWBklOX2twpf.ARwXKKMNpUK.N3aLDj.HN/OW',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00005',NULL,2,NULL,0,NULL,NULL),(1722970236610048001,'emp#6','$2a$10$R7OERqw.cgu8GdKNZk7t0.8d6yqG7PT6tCEZACI6lR48YmchP9NKO',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00006',NULL,2,NULL,0,NULL,NULL),(1722970236878483458,'emp#7','$2a$10$NC1Rum3bTMKEeq4zz1a8Le5rSfT.UTL6D4FG8csU4.4Y30A.lamFe',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00007',NULL,2,NULL,0,NULL,NULL),(1722970237205639170,'emp#8','$2a$10$Li9TpI4xELX9Qp2VRxXJX.qTKb3QBufgjqB8WSw6QP5U9SK656bri',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00008',NULL,2,NULL,0,NULL,NULL),(1722970237469880322,'emp#9','$2a$10$Bfd/Ciyf82/XVqPyvvU0ieSmfrzdQihcvM0iuu5YSUFYmbLPu8luq',NULL,NULL,0,'2023-11-10 21:31:30',NULL,NULL,'emp00009',NULL,2,NULL,0,NULL,NULL),(1722970237729927170,'emp#10','$2a$10$joqu.wRmyDmF56wrN8WKSuO4xgI5/iAreC/t5728LhLv4eta77PM.',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00010',NULL,2,NULL,0,NULL,NULL),(1722970238057082881,'emp#11','$2a$10$HLjod/IvcMz1wGj06Cc/Ce3bDCBI8WXbzHhFuqhaq9xThZKD5H6De',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00011',NULL,2,NULL,0,NULL,NULL),(1722970238321324034,'emp#12','$2a$10$/AXfmdYb1VY3PkvFdU8sxuKzRL9PfrxtLkRawJv6GTebx/qzjGuQi',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00012',NULL,2,NULL,0,NULL,NULL),(1722970238652674050,'emp#13','$2a$10$uxR3ZgE9Tf8DC4AEpSTLD.cWsXejH7Q17mikF1OYPYPBu/F9e.9tS',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00013',NULL,2,NULL,0,NULL,NULL),(1722970238912720898,'emp#14','$2a$10$nzfo5VDAlX51CAii6vLo4.c7oFo5UWwzu5fE480qK.EQ5aRwc.U2q',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00014',NULL,2,NULL,0,NULL,NULL),(1722970239172767745,'emp#15','$2a$10$tfkrS5MD6yUW2Xu9O0SWFeRIWFt48r/TZGPX0hUlMYKo/vOKTyMVW',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00015',NULL,2,NULL,0,NULL,NULL),(1722970239499923457,'emp#16','$2a$10$4WM0r.byiIui1/c50BpqIekT.QThnMyesQS0Mci.kMmkoNmI86.cG',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00016',NULL,2,NULL,0,NULL,NULL),(1722970239755776002,'emp#17','$2a$10$zg9pGn3kbnRDKrT59Ck00eLgmGqjWIlrRYlwYqJ4ysesUpbc.6hkq',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00017',NULL,2,NULL,0,NULL,NULL),(1722970240087126018,'emp#18','$2a$10$W/C8FvOevrF6fEXWxNivhuVBT.7AXR10FxnbAWcXv3IdOgyb75ol6',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00018',NULL,2,NULL,0,NULL,NULL),(1722970240347172865,'emp#19','$2a$10$jFuBJDcbF2x8nov3Zp3SgeB455pOopgzHhlZimFE7PlGnTVGn5URS',NULL,NULL,0,'2023-11-10 21:31:31',NULL,NULL,'emp00019',NULL,2,NULL,0,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2029416450 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
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

--
-- Table structure for table `station_delivery`
--

DROP TABLE IF EXISTS `station_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_delivery` (
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
-- Dumping data for table `station_delivery`
--

LOCK TABLES `station_delivery` WRITE;
/*!40000 ALTER TABLE `station_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `station_delivery` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1722975093257560068 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='站点员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_employee`
--

LOCK TABLES `station_employee` WRITE;
/*!40000 ALTER TABLE `station_employee` DISABLE KEYS */;
INSERT INTO `station_employee` VALUES (1722975093257560065,1722923144885714945,1722969959542730754,'网点管理员',0,'2023-11-10 21:50:48',NULL),(1722975093257560066,1722923144885714945,1722969959869886465,'网点快递员',0,'2023-11-10 21:58:34','2023-11-10 21:58:34'),(1722975093257560067,1722923144885714945,1722969959869886465,'网点快递员',0,'2023-11-10 21:59:54','2023-11-10 21:59:54');
/*!40000 ALTER TABLE `station_employee` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=1722972926991863810 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1722971622093533186,'vividbobo','$2a$10$AfwK5ex/YwMZJthFgq0D8e4zEkDc11jaj9Se5Bz5PDjmoY9fKE1gO',NULL,'8888@qq.com',0,'2023-11-10 21:37:01','2023-11-10 21:48:28',NULL,'纯属娱乐',1,'8','2023-11-09',NULL),(1722971623486042113,'user','$2a$10$3K0hZIfUUujtqyIbyEe.4u8MvCA9v5namAhTKNq2WPp..8JPyeYIq',NULL,NULL,0,'2023-11-10 21:37:01',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972920209674241,'user#0','$2a$10$CPQGXLrr0O5tnGNbtgfeXuOnZXvcjAprRwGqCwP7w.AJRjRKfzCka',NULL,NULL,0,'2023-11-10 21:42:10',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972921627348994,'user#1','$2a$10$OP7MyMg0DrNqI8DHeK6Aue8T7.ytxlx81PnVdN/E.tKtB1I.zPgTq',NULL,NULL,0,'2023-11-10 21:42:10',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972921954504705,'user#2','$2a$10$GLbx8bChTzJVHoykrmHIreQK6YLlsj61nCDM.WkR9HOqD5TcYFPSi',NULL,NULL,0,'2023-11-10 21:42:10',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972922277466113,'user#3','$2a$10$zo1y6qQXXsOckfU./zZuReaK.xnl9Md7DBPAq/Fmrz4PNBcznMsli',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972922537512961,'user#4','$2a$10$EWnHiLDQ0TA3cfDvq/46QuSGpQClhg/iY0N2Sh9GdJMxrclL80MHm',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972922864668674,'user#5','$2a$10$AhE6Bsr90eID7WFKciuh2OH1JqteGWbuTSm/t77AWyfBFQVcUnCFe',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972923196018690,'user#6','$2a$10$RVi3j5V9W58oIwH328w/Lu68h4vGvmcLZ6WvDAi/hQ6bvHvanTTee',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972923460259842,'user#7','$2a$10$/QomCWnUi9QshOZOHGFpyOEFw6FqhLpEybexHisMdzHBuIj/TTIPm',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972923787415554,'user#8','$2a$10$u.LiYNyo./CMqaDBbov/LusFlXOUBLB75pIXbJY6775smgO3wJpP6',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972924051656706,'user#9','$2a$10$AMHnQU0ZENeHYZwc7m5UKOb32sg5ZEOdj9vIKIugG.N.fiuxFOkve',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972924378812417,'user#10','$2a$10$z8g6R4aj1QB9WwS2nu9vbOjPQeopozD/8Cb4M/79JnZneSAYzX0iG',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972924638859266,'user#11','$2a$10$b363QP4ONMwwlRP.TVxFSeOXJI1MUsJtWC3yQ6SRD9eeiJcz5DAFe',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972924966014977,'user#12','$2a$10$va1vN4TSo7YZZ5lzDH4Eb..CSTiHB1.I/2k6xRIQ..IswNVhgj.km',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972925226061825,'user#13','$2a$10$aUGEofMdp.HKL5sSW5HUpO6OmoioLVfx/CYZrZIKjDRUducLfaDJy',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972925549023234,'user#14','$2a$10$h3C.hgHTANkJJTp66VuM.uhQZcgVowIzuOelZKj2ejOkFLIt8cyxK',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972925813264385,'user#15','$2a$10$cDTXRyK447delCJ9ltxQZOz1sYM20QyLjIq3odL54pQD3C1f9PmIC',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972926144614401,'user#16','$2a$10$Ab1uRpWlCxkpbMhLKyCa6.0adBMpRJcp8rsUmyS.Vlr09x5cMKDbW',NULL,NULL,0,'2023-11-10 21:42:11',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972926408855553,'user#17','$2a$10$BG6Ac.9DuUxDcxZ8eRxXXulg2Sg5C8yr1oJrJDOEvPQx0kUP6IXRS',NULL,NULL,0,'2023-11-10 21:42:12',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972926727622657,'user#18','$2a$10$jQUkFkvDpzeiJhVBVmum2Ocd9TukjzmhW/UrVcHzxriHkmuiKl/rW',NULL,NULL,0,'2023-11-10 21:42:12',NULL,NULL,NULL,2,NULL,NULL,NULL),(1722972926991863809,'user#19','$2a$10$UI6.Tcv2NjNxL1Hto3bnQuyTsXFsb8/kGCAe1YCmqxYhga4PT11OG',NULL,NULL,0,'2023-11-10 21:42:12',NULL,NULL,NULL,2,NULL,NULL,NULL);
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

-- Dump completed on 2023-11-10 22:01:11
