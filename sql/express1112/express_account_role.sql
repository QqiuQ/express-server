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
INSERT INTO `account_role` VALUES (1722969958217330689,-650670078,'EMPLOYEE'),(1722969959542730754,-650670079,'EMPLOYEE'),(1722969959869886465,-717778941,'EMPLOYEE'),(1722969960125739010,-650670078,'EMPLOYEE'),(1722969960452894721,-717778942,'EMPLOYEE'),(1722970233762115585,-650670079,'EMPLOYEE'),(1722970235108487169,-650670079,'EMPLOYEE'),(1722970235435642881,-650670079,'EMPLOYEE'),(1722970235695689730,-650670079,'EMPLOYEE'),(1722970236027039745,-650670079,'EMPLOYEE'),(1722970236308058113,-650670079,'EMPLOYEE'),(1722970236610048001,-650670079,'EMPLOYEE'),(1722970236878483458,-650670079,'EMPLOYEE'),(1722970237205639170,-650670079,'EMPLOYEE'),(1722970237469880322,-650670079,'EMPLOYEE'),(1722970237729927170,-650670079,'EMPLOYEE'),(1722970238057082881,-717778942,'EMPLOYEE'),(1722970238321324034,-717778942,'EMPLOYEE'),(1722970238652674050,-717778942,'EMPLOYEE'),(1722970238912720898,-650670079,'EMPLOYEE'),(1722970239172767745,-717778942,'EMPLOYEE'),(1722970239499923457,-717778942,'EMPLOYEE'),(1722970239755776002,-717778942,'EMPLOYEE'),(1722970240087126018,-650670079,'EMPLOYEE'),(1722970240347172865,-717778942,'EMPLOYEE'),(1722971622093533186,-1967681535,'USER'),(1722971623486042113,-1967681535,'USER'),(1722972920209674241,-1967681535,'USER'),(1722972921627348994,-1967681535,'USER'),(1722972921954504705,-1967681535,'USER'),(1722972922277466113,-1967681535,'USER'),(1722972922537512961,-1967681535,'USER'),(1722972922864668674,-1967681535,'USER'),(1722972923196018690,-1967681535,'USER'),(1722972923460259842,-1967681535,'USER'),(1722972923787415554,-1967681535,'USER'),(1722972924051656706,-1967681535,'USER'),(1722972924378812417,-1967681535,'USER'),(1722972924638859266,-1967681535,'USER'),(1722972924966014977,-1967681535,'USER'),(1722972925226061825,-1967681535,'USER'),(1722972925549023234,-1967681535,'USER'),(1722972925813264385,-1967681535,'USER'),(1722972926144614401,-1967681535,'USER'),(1722972926408855553,-1967681535,'USER'),(1722972926727622657,-1967681535,'USER'),(1722972926991863809,-1967681535,'USER'),(1723618633625063425,-717778941,'EMPLOYEE');
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-12 16:53:37