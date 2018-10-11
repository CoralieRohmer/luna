-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: luna
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Film`
--

DROP TABLE IF EXISTS `Film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Film` (
  `id_film` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) NOT NULL,
  `annee_sortie` year(4) DEFAULT NULL,
  `note` tinyint(3) unsigned DEFAULT NULL,
  `vu` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_film`),
  FULLTEXT KEY `ind_titre` (`titre`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Film`
--

LOCK TABLES `Film` WRITE;
/*!40000 ALTER TABLE `Film` DISABLE KEYS */;
INSERT INTO `Film` VALUES (1,'Bienvenue à Zombieland',2009,4,1),(2,'La Horde',2009,3,0),(3,'Land Of The Dead',2005,3,0),(4,'Resident Evil 1',2002,3,1),(5,'Resident Evil 2 Apocalypse',2004,2,1),(6,'Resident Evil 3 Extinction',2007,3,1),(7,'Resident Evil 4 Afterlife',2010,2,1),(8,'Resident Evil 5 Retribution',2012,2,1),(9,'Le Dernier Pub Avant La Fin Du Monde',2013,4,0);
/*!40000 ALTER TABLE `Film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Genre`
--

DROP TABLE IF EXISTS `Genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Genre` (
  `id_genre` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `genre` varchar(20) NOT NULL,
  PRIMARY KEY (`id_genre`),
  UNIQUE KEY `ind_genre` (`genre`(10))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Genre`
--

LOCK TABLES `Genre` WRITE;
/*!40000 ALTER TABLE `Genre` DISABLE KEYS */;
INSERT INTO `Genre` VALUES (1,'Comédie'),(2,'Epouvante-horreur'),(3,'Action'),(4,'Fantastique'),(5,'Science-fiction'),(6,'Thriller');
/*!40000 ALTER TABLE `Genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Nationalite`
--

DROP TABLE IF EXISTS `Nationalite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Nationalite` (
  `id_nation` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `nation` varchar(20) NOT NULL,
  PRIMARY KEY (`id_nation`),
  UNIQUE KEY `ind_nation` (`nation`(10))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Nationalite`
--

LOCK TABLES `Nationalite` WRITE;
/*!40000 ALTER TABLE `Nationalite` DISABLE KEYS */;
INSERT INTO `Nationalite` VALUES (1,'Américain'),(2,'Français'),(3,'Canadien'),(4,'Britannique'),(5,'Allemand'),(6,'Japonais');
/*!40000 ALTER TABLE `Nationalite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reference`
--

DROP TABLE IF EXISTS `Reference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reference` (
  `id_ref` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prenom` varchar(40) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `nationalite` varchar(20) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  PRIMARY KEY (`id_ref`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reference`
--

LOCK TABLES `Reference` WRITE;
/*!40000 ALTER TABLE `Reference` DISABLE KEYS */;
INSERT INTO `Reference` VALUES (1,'Woody','Harrelson','Américaine','1961-07-20'),(2,'Jesse','Eisenberg','Américaine','1983-10-05'),(3,'Abigail','Breslin','Américaine','1996-04-14'),(4,'Bill','Murray','Américaine','1950-08-21'),(5,'Claude','Perron','Française','1966-01-23'),(6,'Jean-Pierre','Martins','Française','1971-10-29'),(7,'Eriq','Ebouaney','Française','1967-10-03'),(8,'Devon','Bostick','Canadienne','1991-09-13'),(9,'Simon','Baker','Australianne','1969-07-30'),(10,'Asia','Argento','Italienne','1975-06-20'),(11,'Milla','Jovovich','Ukrainienne','1975-12-17'),(12,'Eric','Mabius','Américaine','1971-04-22'),(13,'Michelle','Rodriguez','Américaine','1978-07-12'),(14,'Sienna','Guillory','Britannique','1975-03-16'),(15,'Oded','Fehr','Israélienne','1970-11-23'),(16,'Ali','Larter','Américaine','1976-02-28'),(17,'Shawn','Roberts','Canadienne','1984-04-02'),(18,'Kevin','Durand','Américaine','1974-01-14'),(19,'Simon','Pegg','Britannique','1970-02-14'),(20,'Nick','Frost','Britannique','1972-03-28'),(21,'Martin','Freeman','Britannique','1971-09-08'),(22,'Ruben','Fleischer','Américaine','1974-10-31'),(23,'Yannick','Dahan','Française','1972-01-01'),(24,'Benjamin','Rocher','Française',NULL),(25,'George A.','Romero','Américaine','1940-02-04'),(26,'Paul W.S','Anderson','Britannique','1965-03-04'),(27,'Alexander','Witt','Chilienne',NULL),(28,'Russell','Mulcahy','Australienne','1953-06-23'),(29,'Edgar','Wright','Britannique','1974-04-18');
/*!40000 ALTER TABLE `Reference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Saga`
--

DROP TABLE IF EXISTS `Saga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Saga` (
  `id_saga` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `saga` varchar(40) NOT NULL,
  PRIMARY KEY (`id_saga`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Saga`
--

LOCK TABLES `Saga` WRITE;
/*!40000 ALTER TABLE `Saga` DISABLE KEYS */;
INSERT INTO `Saga` VALUES (1,'Resident Evil'),(2,'Cornetto Trilogy');
/*!40000 ALTER TABLE `Saga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tag` (
  `id_tag` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `tag` varchar(40) NOT NULL,
  PRIMARY KEY (`id_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tag`
--

LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO `Tag` VALUES (1,'Zombie'),(2,'Nanar');
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aPrActeur`
--

DROP TABLE IF EXISTS `aPrActeur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aPrActeur` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_film` smallint(5) unsigned NOT NULL,
  `id_acteur` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_film_acteur` (`id_film`),
  KEY `fk_id_acteur` (`id_acteur`),
  CONSTRAINT `fk_id_acteur` FOREIGN KEY (`id_acteur`) REFERENCES `Reference` (`id_ref`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_film_acteur` FOREIGN KEY (`id_film`) REFERENCES `Film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aPrActeur`
--

LOCK TABLES `aPrActeur` WRITE;
/*!40000 ALTER TABLE `aPrActeur` DISABLE KEYS */;
INSERT INTO `aPrActeur` VALUES (1,1,2),(2,1,3),(3,1,4),(4,2,5),(5,2,6),(6,2,7),(7,3,8),(8,3,9),(9,3,10),(10,4,11),(11,4,12),(12,4,13),(13,5,11),(14,5,14),(15,5,15),(16,6,11),(17,6,15),(18,6,16),(19,7,11),(20,7,16),(21,7,17),(22,8,11),(23,8,13),(24,8,18),(25,9,19),(26,9,20),(27,9,21);
/*!40000 ALTER TABLE `aPrActeur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aPrGenre`
--

DROP TABLE IF EXISTS `aPrGenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aPrGenre` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_film` smallint(5) unsigned NOT NULL,
  `id_genre` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_film_genre` (`id_film`),
  KEY `fk_id_genre` (`id_genre`),
  CONSTRAINT `fk_id_film_genre` FOREIGN KEY (`id_film`) REFERENCES `Film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_genre` FOREIGN KEY (`id_genre`) REFERENCES `Genre` (`id_genre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aPrGenre`
--

LOCK TABLES `aPrGenre` WRITE;
/*!40000 ALTER TABLE `aPrGenre` DISABLE KEYS */;
INSERT INTO `aPrGenre` VALUES (1,1,1),(2,1,2),(3,2,2),(4,2,3),(5,3,2),(6,3,3),(7,4,2),(8,4,4),(9,4,5),(10,4,6),(11,4,3),(12,5,2),(13,5,3),(14,5,4),(15,6,3),(16,6,2),(17,6,5),(18,7,3),(19,7,2),(20,8,3),(21,8,2),(22,8,5),(23,9,3),(24,9,1),(25,9,5);
/*!40000 ALTER TABLE `aPrGenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aPrNation`
--

DROP TABLE IF EXISTS `aPrNation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aPrNation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_film` smallint(5) unsigned NOT NULL,
  `id_nation` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_film_nationalite` (`id_film`),
  KEY `fk_id_nation` (`id_nation`),
  CONSTRAINT `fk_id_film_nationalite` FOREIGN KEY (`id_film`) REFERENCES `Film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_nation` FOREIGN KEY (`id_nation`) REFERENCES `Nationalite` (`id_nation`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aPrNation`
--

LOCK TABLES `aPrNation` WRITE;
/*!40000 ALTER TABLE `aPrNation` DISABLE KEYS */;
INSERT INTO `aPrNation` VALUES (1,1,1),(2,2,2),(3,3,2),(4,3,1),(5,3,3),(6,4,2),(7,4,1),(8,4,4),(9,4,5),(10,5,2),(11,5,1),(12,5,4),(13,5,5),(14,6,1),(15,7,1),(16,7,2),(17,7,5),(18,8,3),(19,8,5),(20,9,4),(21,9,1),(22,9,6);
/*!40000 ALTER TABLE `aPrNation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aPrReal`
--

DROP TABLE IF EXISTS `aPrReal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aPrReal` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_film` smallint(5) unsigned NOT NULL,
  `id_real` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_film_real` (`id_film`),
  KEY `fk_id_real` (`id_real`),
  CONSTRAINT `fk_id_film_real` FOREIGN KEY (`id_film`) REFERENCES `Film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_real` FOREIGN KEY (`id_real`) REFERENCES `Reference` (`id_ref`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aPrReal`
--

LOCK TABLES `aPrReal` WRITE;
/*!40000 ALTER TABLE `aPrReal` DISABLE KEYS */;
INSERT INTO `aPrReal` VALUES (1,1,22),(2,2,23),(3,2,24),(4,3,25),(5,4,26),(6,5,27),(7,6,28),(8,7,26),(9,8,26),(10,9,29);
/*!40000 ALTER TABLE `aPrReal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aPrSaga`
--

DROP TABLE IF EXISTS `aPrSaga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aPrSaga` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `id_film` smallint(5) unsigned NOT NULL,
  `id_saga` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_film_saga` (`id_film`),
  KEY `fk_id_saga` (`id_saga`),
  CONSTRAINT `fk_id_film_saga` FOREIGN KEY (`id_film`) REFERENCES `Film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_saga` FOREIGN KEY (`id_saga`) REFERENCES `Saga` (`id_saga`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aPrSaga`
--

LOCK TABLES `aPrSaga` WRITE;
/*!40000 ALTER TABLE `aPrSaga` DISABLE KEYS */;
INSERT INTO `aPrSaga` VALUES (1,4,1),(2,5,1),(3,6,1),(4,7,1),(5,8,1),(6,9,2);
/*!40000 ALTER TABLE `aPrSaga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aPrTag`
--

DROP TABLE IF EXISTS `aPrTag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aPrTag` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `id_film` smallint(5) unsigned NOT NULL,
  `id_tag` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_film_tag` (`id_film`),
  KEY `fk_id_tag` (`id_tag`),
  CONSTRAINT `fk_id_film_tag` FOREIGN KEY (`id_film`) REFERENCES `Film` (`id_film`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_tag` FOREIGN KEY (`id_tag`) REFERENCES `Tag` (`id_tag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aPrTag`
--

LOCK TABLES `aPrTag` WRITE;
/*!40000 ALTER TABLE `aPrTag` DISABLE KEYS */;
INSERT INTO `aPrTag` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,4,2),(10,5,2),(11,6,2),(12,7,2),(13,8,2);
/*!40000 ALTER TABLE `aPrTag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-27 17:51:42
