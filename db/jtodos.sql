/*
SQLyog Ultimate v8.32 
MySQL - 5.7.19-log : Database - jtodos
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jtodos` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jtodos`;

/*Table structure for table `todos` */

DROP TABLE IF EXISTS `todos`;

CREATE TABLE `todos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `todos` */

insert  into `todos`(`id`,`item`,`createtime`,`userid`) values (4,'下午三点看书','2017-11-14 16:15:11',0),(5,'下午三点看书','2017-11-20 15:45:27',1),(6,'这是李四的备忘录','2017-11-20 16:02:26',4),(7,'这个也是李四的备忘录','2017-11-20 16:04:11',4),(8,'这个还是李四的备忘录','2017-11-20 16:07:22',4),(9,'测试第二个todo','2017-12-06 14:38:20',1),(10,'åå ç¬¬ä¸ä¸ªæµè¯','2017-12-06 14:56:43',1),(11,'第四个中文测试','2017-12-06 15:10:47',1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`name`) values (1,'zhangsan'),(2,'index.jsp'),(4,'lisi');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
