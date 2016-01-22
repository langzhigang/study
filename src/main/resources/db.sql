
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lzgstudy` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lzgstudy`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
  `authorid` int(11) NOT NULL AUTO_INCREMENT,
  `authorname` varchar(20) NOT NULL,
  PRIMARY KEY (`authorid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `author` */

insert  into `author`(`authorid`,`authorname`) values (1,'张三'),(2,'李四');

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `blogid` int(11) NOT NULL AUTO_INCREMENT,
  `blogname` varchar(50) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `authorid` int(11) DEFAULT NULL,
  PRIMARY KEY (`blogid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `blog` */

insert  into `blog`(`blogid`,`blogname`,`content`,`url`,`authorid`) values (1,'zs1','zs1',NULL,1),(2,'zs2','zs2',NULL,1),(3,'ls1','ls1',NULL,2);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`userid`,`username`,`password`) values (1,'hah','111'),(2,'aaa','222');

/*Table structure for table `sys_user_uuid` */

DROP TABLE IF EXISTS `sys_user_uuid`;

CREATE TABLE `sys_user_uuid` (
  `userid` varchar(36) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_uuid` */

insert  into `sys_user_uuid`(`userid`,`username`) values ('59b22844-adfc-11e5-8257-00163e0035cd','哈哈'),('92cefd1e-ae0b-11e5-8257-00163e0035cd','哈哈'),('9d781656-ae0b-11e5-8257-00163e0035cd','哈哈');

/*Table structure for table `testuser` */

DROP TABLE IF EXISTS `testuser`;

CREATE TABLE `testuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


