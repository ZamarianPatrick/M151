/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;

CREATE TABLE `address` (
                           `id` bigint(20) NOT NULL,
                           `street` varchar(255) NOT NULL,
                           `city_fk` bigint(20) NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FKig0ifau20k6qjgbjpihx3l3yd` (`city_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Waldstrasse 17',1),(2,'Zollgasse 36',2),(3,'Heidenweg 78',3),(4,'Pfaffnernweg 6',4);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `article` (
                           `id` bigint(20) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1),(2);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_available_items`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `article_available_items` (
                                           `article_id` bigint(20) NOT NULL,
                                           `available_items_id` bigint(20) NOT NULL,
                                           KEY `FKms6uby5ol8m01qv967vd7dwy2` (`available_items_id`),
                                           KEY `FK4sksqm3ijoxaw22e7bmflv8x6` (`article_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_available_items`
--

LOCK TABLES `article_available_items` WRITE;
/*!40000 ALTER TABLE `article_available_items` DISABLE KEYS */;
INSERT INTO `article_available_items` VALUES (1,3),(1,4),(1,6),(1,8),(1,9),(1,10),(1,11),(1,14),(2,3),(2,4),(2,5),(2,7),(2,8),(2,9),(2,10),(2,12),(2,13);
/*!40000 ALTER TABLE `article_available_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `city` (
                        `id` bigint(20) NOT NULL,
                        `name` varchar(255) NOT NULL,
                        `zip` varchar(255) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Kölliken','3452'),(2,'Olten','2398'),(3,'Pfaffnau','8242'),(4,'Rothrist','4852');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
                            `id` bigint(20) NOT NULL,
                            `customer_type` varchar(255) DEFAULT NULL,
                            `email` varchar(255) NOT NULL,
                            `firstname` varchar(255) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            `registered_since` datetime NOT NULL,
                            `surname` varchar(255) NOT NULL,
                            `telephon_number` varchar(255) NOT NULL,
                            `username` varchar(255) NOT NULL,
                            `address_fk` bigint(20) NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FK28dg7c4ivpo97omo2dp3hfckj` (`address_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'ADMIN','klaus.meier@happywriter.ch','Klaus','vFqZUz8hI8xJqitq+XWVQb6RJAVeAo7363VSJmEb33fTcQiXwivV1JSYAog3eUuyi9IaUMhvj5GmhsRVXUEAkA==','2019-06-04 07:50:32','Meier','062 794 64 89','admin',1),(2,'USER','lena.hollinger@hotmail.de','Lena','H5nMH6dl35MPwbRZ2KrwV1XFYnCVZna0IdbqVwITBZYeZhRcTU7zDcAVdvYEi8PO467aqV4ajYjcRID8Pj0Llg==','2019-06-04 07:52:49','Hollinger','062 794 52 89','benutzer',2),(3,'EMPLOYEE','fritz.mueller@happywriter.ch','Fritz','RAxvvYERShnwGm1R2v5tiNqpM1G0aGS65seW7JWG1DfaCvz8nQ72HLG8j1hyVEW6R2vDCMYvssMYeEaz6Y4sSw==','2019-06-04 07:54:48','Müller','062 793 89 23','mitarbeiter',3),(4,'ADMIN','patrick@zamgames.ch','Patrick','Dyuo3ASnJbjk8+CPAsiLLOosOHaxf+lHrMFVJxn43hLKgC1HbJxC1wUux7pEhvu9RbNxezoQ5Ufm4PJKgywGsQ==','2019-06-04 07:57:42','Zamarian','062 794 33 06','patrick',4);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `flyway_schema_history` (
                                         `installed_rank` int(11) NOT NULL,
                                         `version` varchar(50) DEFAULT NULL,
                                         `description` varchar(200) NOT NULL,
                                         `type` varchar(20) NOT NULL,
                                         `script` varchar(1000) NOT NULL,
                                         `checksum` int(11) DEFAULT NULL,
                                         `installed_by` varchar(100) NOT NULL,
                                         `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         `execution_time` int(11) NOT NULL,
                                         `success` tinyint(1) NOT NULL,
                                         PRIMARY KEY (`installed_rank`),
                                         KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_position`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `order_position` (
                                  `id` bigint(20) NOT NULL,
                                  `article_fk` bigint(20) NOT NULL,
                                  `order_fk` bigint(20) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKe114e9x7hu1xypg2nrxyk3db2` (`article_fk`),
                                  KEY `FKfr5ky2lm3u97yhk3l1kkeogbw` (`order_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_position`
--

LOCK TABLES `order_position` WRITE;
/*!40000 ALTER TABLE `order_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_position_items`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `order_position_items` (
                                        `order_position_id` bigint(20) NOT NULL,
                                        `items_id` bigint(20) NOT NULL,
                                        KEY `FK7jjweqlakg47nw4o3bapgu5pp` (`items_id`),
                                        KEY `FK7mwhmpm2igitlh0dswphqieg7` (`order_position_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_position_items`
--

LOCK TABLES `order_position_items` WRITE;
/*!40000 ALTER TABLE `order_position_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_position_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_address`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `seq_address` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_address`
--

LOCK TABLES `seq_address` WRITE;
/*!40000 ALTER TABLE `seq_address` DISABLE KEYS */;
INSERT INTO `seq_address` VALUES (21);
/*!40000 ALTER TABLE `seq_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_city`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `seq_city` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_city`
--

LOCK TABLES `seq_city` WRITE;
/*!40000 ALTER TABLE `seq_city` DISABLE KEYS */;
INSERT INTO `seq_city` VALUES (21);
/*!40000 ALTER TABLE `seq_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_customer`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `seq_customer` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_customer`
--

LOCK TABLES `seq_customer` WRITE;
/*!40000 ALTER TABLE `seq_customer` DISABLE KEYS */;
INSERT INTO `seq_customer` VALUES (21);
/*!40000 ALTER TABLE `seq_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_order`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `seq_order` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_order`
--

LOCK TABLES `seq_order` WRITE;
/*!40000 ALTER TABLE `seq_order` DISABLE KEYS */;
INSERT INTO `seq_order` VALUES (1);
/*!40000 ALTER TABLE `seq_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_order_position`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `seq_order_position` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_order_position`
--

LOCK TABLES `seq_order_position` WRITE;
/*!40000 ALTER TABLE `seq_order_position` DISABLE KEYS */;
INSERT INTO `seq_order_position` VALUES (1);
/*!40000 ALTER TABLE `seq_order_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seq_shop_object`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `seq_shop_object` (
    `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seq_shop_object`
--

LOCK TABLES `seq_shop_object` WRITE;
/*!40000 ALTER TABLE `seq_shop_object` DISABLE KEYS */;
INSERT INTO `seq_shop_object` VALUES (31);
/*!40000 ALTER TABLE `seq_shop_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_object`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `shop_object` (
                               `id` bigint(20) NOT NULL,
                               `icon` varchar(255) NOT NULL,
                               `name` varchar(255) NOT NULL,
                               `price` double NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_object`
--

LOCK TABLES `shop_object` WRITE;
/*!40000 ALTER TABLE `shop_object` DISABLE KEYS */;
INSERT INTO `shop_object` VALUES (1,'etui.jpg','Etui',45.95),(2,'holzschachtel.jpg','Holzschachtel',29.95),(3,'bleistift.jpg','Bleistift',4.95),(4,'buntstifte.jpg','Buntstifte',12.95),(5,'kleber.jpg','Kleber',8.95),(6,'leimstift.jpg','Leimstift',8.95),(7,'pinsel.jpg','Pinselset',16.95),(8,'postit.jpg','Post-It',4.95),(9,'radiergummi.jpg','Radiergummi',2.95),(10,'schere.jpg','Schere',14.95),(11,'spitzer1.jpg','Spitzer',4.95),(12,'spitzer2.jpg','Dosenspitzer',8.95),(13,'zirkel1.jpg','Zirkel Purple',18.95),(14,'zirkel2.jpg','Zirkel',18.95);
/*!40000 ALTER TABLE `shop_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_order`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `shop_order` (
                              `id` bigint(20) NOT NULL,
                              `comments` varchar(255) DEFAULT NULL,
                              `order_date` date NOT NULL,
                              `customer_fk` bigint(20) NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKdhi8sudpdxmeoqu8wrxrmu3v3` (`customer_fk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_order`
--

LOCK TABLES `shop_order` WRITE;
/*!40000 ALTER TABLE `shop_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04 10:07:42
