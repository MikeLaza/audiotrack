-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.2    Database: audio_tracks_order
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'идентификатор альбома',
  `singer_id` int(11) NOT NULL COMMENT 'внешний ключ на исполнителя',
  `name_album` varchar(45) NOT NULL,
  `img` int(11) NOT NULL,
  PRIMARY KEY (`album_id`,`singer_id`),
  KEY `fk_album_singer1_idx` (`singer_id`),
  CONSTRAINT `fk_album_singer1` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`singer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='Альбом.\nУ  исполнителя может быть несколько альбомов, а один альбом относится к одному исполнителю';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,2,'niiiiii',1),(2,2,'Music Offering',2),(3,3,'Ordinary miracle',3),(4,3,'Dog in the manger',4),(5,4,'First',5),(6,4,'Second',6),(7,4,'MyAlbum',7),(8,5,'Amazing story',8),(9,6,'Cool Day',9),(10,6,'For your baby',10),(11,7,'Bayby',12);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `track` int(11) NOT NULL COMMENT 'внешний ключ на трек',
  `text` varchar(45) NOT NULL COMMENT 'текст комментария ',
  PRIMARY KEY (`comment_id`,`user_id`,`track`),
  KEY `fk_comment_track1_idx` (`track`),
  KEY `fk_comment_user1_idx` (`user_id`),
  CONSTRAINT `fk_comment_track1` FOREIGN KEY (`track`) REFERENCES `track` (`track_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='таблица комментария\nклиент может сделать несколько комментариев на трек, а трек может иммет несколько комментариев от разных клиентов.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (4,4,6,'werwe'),(27,56,6,'my comment'),(28,56,6,'dfsfsffs'),(29,71,6,'dfsfdsf'),(30,71,6,'qqqqqqqqqqqqqq'),(31,72,6,'q'),(32,56,6,'a'),(33,56,7,'a'),(35,56,6,'ll'),(37,3,6,'makeeeee'),(38,74,6,'1q1q1q1q1'),(39,3,6,'makdddddd'),(40,56,7,'ddd'),(41,3,6,'makdddddd'),(42,3,6,'makdddddd'),(43,3,6,'makdddddd'),(44,3,6,'makdddddd'),(68,80,6,'dfd'),(69,56,8,'334'),(70,56,10,'ggdg'),(71,56,6,'hello');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный иденификатор заказа\n',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`user_id`),
  KEY `fk_order_user1_idx` (`user_id`),
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COMMENT='Таблица заказов\nодин клиент может сделать несколько заказов, а один заказ относится к одному клиенту';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (120,56),(121,56),(122,56),(123,56),(124,56),(125,56),(126,56),(127,56),(34,63),(48,72),(59,74),(65,75),(81,79);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_m2m_track`
--

DROP TABLE IF EXISTS `order_m2m_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_m2m_track` (
  `order` int(11) NOT NULL COMMENT 'Внешний ключ на заказ',
  `track` int(11) NOT NULL COMMENT 'Внешний ключ на трек',
  PRIMARY KEY (`order`,`track`),
  KEY `fk_order_m2m_track_track_idx` (`track`),
  KEY `fk_order_m2m_track_order1_idx` (`order`),
  CONSTRAINT `fk_order_m2m_track_order1` FOREIGN KEY (`order`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_m2m_track_track` FOREIGN KEY (`track`) REFERENCES `track` (`track_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Один заказ может иметь несколько трекво, а один трек может относиться к нескольким заказам\n ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_m2m_track`
--

LOCK TABLES `order_m2m_track` WRITE;
/*!40000 ALTER TABLE `order_m2m_track` DISABLE KEYS */;
INSERT INTO `order_m2m_track` VALUES (48,6),(59,6),(65,6),(81,6),(124,6),(120,7),(123,8),(127,9),(121,10),(126,10),(122,11),(34,13),(125,91);
/*!40000 ALTER TABLE `order_m2m_track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `playlist_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'идентификатор плейлиста',
  `name_playlist` varchar(45) NOT NULL COMMENT 'название плейлиста',
  PRIMARY KEY (`playlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Плейлист, содержищий несколько треков';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'Soviet music'),(2,'Music for the soul'),(3,'Dance'),(4,'Summer hits'),(6,'ffff'),(7,'mike'),(8,'mike'),(9,'mike');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_m2m_track`
--

DROP TABLE IF EXISTS `playlist_m2m_track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_m2m_track` (
  `track_id` int(11) NOT NULL COMMENT 'внешний ключ на трек',
  `playlist_id` int(11) NOT NULL COMMENT 'внешний ключ на плейлист',
  PRIMARY KEY (`track_id`,`playlist_id`),
  KEY `fk_track_has_playlist_playlist1_idx` (`playlist_id`),
  KEY `fk_track_has_playlist_track1_idx` (`track_id`),
  CONSTRAINT `fk_track_has_playlist_playlist1` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_track_has_playlist_track1` FOREIGN KEY (`track_id`) REFERENCES `track` (`track_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Один плей лист содержит несколько треков, а один трек может относится к нескольким плейлистам';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_m2m_track`
--

LOCK TABLES `playlist_m2m_track` WRITE;
/*!40000 ALTER TABLE `playlist_m2m_track` DISABLE KEYS */;
INSERT INTO `playlist_m2m_track` VALUES (6,1),(7,1),(12,1),(8,2),(13,2),(9,3),(15,3),(10,4);
/*!40000 ALTER TABLE `playlist_m2m_track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `singer`
--

DROP TABLE IF EXISTS `singer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `singer` (
  `singer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'идентификатор исполнителя',
  `first_name` varchar(45) NOT NULL COMMENT 'имя исполнителя',
  `last_name` varchar(45) NOT NULL COMMENT 'фамилия исполнителя',
  PRIMARY KEY (`singer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='Исполнитель, который имеет идентификатор и имя.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `singer`
--

LOCK TABLES `singer` WRITE;
/*!40000 ALTER TABLE `singer` DISABLE KEYS */;
INSERT INTO `singer` VALUES (1,'Michael','Jackson'),(2,'Eduard','Artemyev'),(3,'Mikhail ',' Boyarsky'),(4,'Mike','Lazarenko'),(5,'sdfds','sfdsf'),(6,'Bruno','Mars'),(7,'Justin','Biber');
/*!40000 ALTER TABLE `singer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track` (
  `track_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'идентификатор трека',
  `album_id` int(11) NOT NULL COMMENT 'внешний ключ на альбом исполнителя',
  `name_track` varchar(45) NOT NULL COMMENT 'название трека',
  `duration` int(11) NOT NULL COMMENT 'время воспроизведения в секундах',
  `price` decimal(5,1) NOT NULL COMMENT 'цена в долорах',
  PRIMARY KEY (`track_id`,`album_id`),
  KEY `fk_track_album1_idx` (`album_id`),
  CONSTRAINT `fk_track_album1` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='Трек имеет название, id, время воспроизведения и внешний ключ на  альбом исполнителя\nОдин трек относиться к определенному альбому, а альбом может содержать несколько треков';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (6,11,'nmike',12,14.0),(7,1,'My dream',234,6.0),(8,2,'Your love',245,7.0),(9,3,'Why',267,5.0),(10,4,'My car',234,2.0),(11,6,'Summer 86',123,6.0),(12,8,'I love your',23,4.0),(13,2,'Go away',56,5.0),(14,3,'ase of base ',67,4.0),(15,7,'Just for fun',256,8.0),(16,11,'Idont know',244,2.0),(19,4,'unforgiven',214,3.0),(21,5,'dfsfs',235,5.0),(22,8,'sdfsf',35,7.0),(23,4,'mikecool',86,86.0),(27,7,'www',3,3.0),(28,6,'mike',86,86.0),(29,2,'tison',10,10.0),(42,1,'fdssdfs',1,1.0),(44,5,'mikelazacool',1,1.0),(61,6,'fdfs',98,67.0),(67,11,'q',1,1.0),(68,5,'DSFSSFSF',12,12.0),(69,1,'sdsf',1,1.0),(70,7,'eeee',12,12.0),(72,5,'eee',1,1.0),(73,1,'fdfjsfsd90',12,12.0),(89,1,'mikelaza',4,3.4),(90,1,'mikelaza',4,3.4),(91,11,'mikelaza',4,3.4),(92,11,'mikelaza',4,3.4),(93,1,'one',12,12.0);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `locale` varchar(45) NOT NULL,
  `is_admin` tinyint(3) NOT NULL,
  `balance` decimal(4,1) NOT NULL,
  `discount` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'arturAbii','ere','sfsdf35@gmail.com','en_US ',0,13.0,10),(4,'antonPerigud','rete','fsfsfsd@gmail.com','en_US',0,0.0,1),(5,'mikel12','12Qwert','gdfafa@gmail.com','en_US',1,0.0,3),(56,'asd123','12Qwert','mike.lazarenko@97gmail.com','ru_RU',0,0.5,11),(57,'mikelaza','12Qwert','mikelaza@gmail.com','ru_RU',0,4.0,10),(58,'gggg34','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,6.0,14),(59,'ggg6611','12Qwert','mike.lazarenko.97@mail.ru','ru_RU',0,14.0,0),(60,'rett45','12Qwert','mike.lazarenko.97@mail.ru','ru_RU',0,18.0,0),(61,'dfsf342mmmmmm','112Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(62,'dfdsf','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(63,'3424234fsdfs','12Qwert','mike.lazarenko.97@mail.ru','ru_RU',0,15.0,12),(64,'mikelsdfsf','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(65,'wwwewew121','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(66,'dfsdfsdfsd34234','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(67,'oooooooo1','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(68,'w1eew33','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(69,'dfsf45','12Qwert','mike.lazarenko.97@mail.ru','en_US',0,20.0,0),(70,'dfsdfds2342','12Qwert','mike.lazarenko.97@gmail.com','en_US',0,20.0,0),(71,'qwer12mikaaaaa12','12Qwert','mike.lazarenko.97@gmail.com','ru_RU',0,20.0,0),(72,'qwqwqwq111111111','12Qwert','mike.lazarenko.97@gmail.com','en_US',0,19.0,0),(73,'fsdfsfd12','12Qwert','mike.lazarenko.97@gmail.com','ru_RU',0,20.0,12),(74,'erwerwrwe124','12Qwert','mike.lazarenko.97@gmail.com','en_US',0,19.0,0),(75,'fdfsdf23','12Qwert','mike.lazarenko.97@gmail.com','ru_RU',0,19.0,0),(76,'fdsfds34','12Qwert','dfsdfsdfdsf@gfdfs.com','ru_RU',0,20.0,0),(77,'fsfsdds34','12Qwert','mike.lazarenko.97@gmail.com','ru_RU',0,20.0,0),(78,'dfdsf232','12Qwert','mike.lazarenko.97@gmail.com','ru_RU',0,20.0,0),(79,'nikenike123','12Qwert','mike.lazarenko.97@gmail.com','en_US',0,9.2,12),(80,'mikel1212','12Qwert','mike.lazarenko.97@gmail.com','ru_RU',0,20.0,0);
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

-- Dump completed on 2018-02-07 21:24:43
