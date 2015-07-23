/*
SQLyog Community v8.55 
MySQL - 5.0.27-community-nt : Database - ideaclickmvp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ideaclickmvp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ideaclickmvp`;

/*Table structure for table `x_organization` */

DROP TABLE IF EXISTS `x_organization`;

CREATE TABLE `x_organization` (
  `ORGANIZATION_ID` int(10) NOT NULL auto_increment,
  `ORGANIZATION_NAME` varchar(50) NOT NULL,
  `ORGANIZATION_TYPE` varchar(25) NOT NULL,
  `ORGANIZATION_EMAIL` varchar(50) NOT NULL,
  `ORGANIZATION_CONTACT` int(15) NOT NULL,
  `STATUS` varchar(10) NOT NULL,
  PRIMARY KEY  (`ORGANIZATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `x_organization` */

LOCK TABLES `x_organization` WRITE;

insert  into `x_organization`(`ORGANIZATION_ID`,`ORGANIZATION_NAME`,`ORGANIZATION_TYPE`,`ORGANIZATION_EMAIL`,`ORGANIZATION_CONTACT`,`STATUS`) values (1,'Cognizant','Corporate','cogni@cognizant.net',1234567891,''),(2,'MIT','Institude','mit@gmail.com',1234567891,'DEACTIVATE');

UNLOCK TABLES;

/*Table structure for table `x_user` */

DROP TABLE IF EXISTS `x_user`;

CREATE TABLE `x_user` (
  `USER_ID` int(10) NOT NULL auto_increment,
  `USER_NAME` varchar(50) NOT NULL,
  `ORGANIZATION_NAME` varchar(50) default '',
  `USER_EMAIL` varchar(50) NOT NULL,
  `USER_CONTACT` int(15) NOT NULL,
  `STATUS` varchar(10) NOT NULL,
  PRIMARY KEY  (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `x_user` */

LOCK TABLES `x_user` WRITE;

insert  into `x_user`(`USER_ID`,`USER_NAME`,`ORGANIZATION_NAME`,`USER_EMAIL`,`USER_CONTACT`,`STATUS`) values (1,'Amol','','amol@gmail.com',1234567891,'DEACTIVATE');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
