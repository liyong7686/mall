/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.23 : Database - db_book_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_book_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_book_manage`;

/*Table structure for table `t_book` */

CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bianhao` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `banhao` varchar(100) DEFAULT NULL,
  `bianzhu` varchar(100) DEFAULT NULL,
  `chubanshe` varchar(100) DEFAULT NULL,
  `chubanDate` date DEFAULT NULL,
  `danjia` varchar(50) DEFAULT NULL,
  `kucun` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_book` */

insert  into `t_book`(`id`,`bianhao`,`name`,`banhao`,`bianzhu`,`chubanshe`,`chubanDate`,`danjia`,`kucun`) values (3,'1002','php攻略','5520','小红','美山出版社','2018-06-02','50',8),(4,'1001','java攻略','5510','小明','美工出版社','2018-06-01','50',9),(6,'1003','三国演义','5530','小明','中国！@@@@','2018-03-09','50',19),(7,'2001','BookJAVA','publicNo001','测试001','哈哈测试出版社','2018-08-13','300',298);

/*Table structure for table `t_card_bill` */

CREATE TABLE `t_card_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clientId` int(11) DEFAULT NULL,
  `clientName` varchar(50) DEFAULT NULL,
  `carno` varchar(100) DEFAULT NULL,
  `actual` decimal(10,2) DEFAULT NULL,
  `discounts` decimal(10,0) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `type` int(4) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_card_bill` */

insert  into `t_card_bill`(`id`,`clientId`,`clientName`,`carno`,`actual`,`discounts`,`money`,`balance`,`type`,`remark`,`createDateTime`,`createUserId`) values (1,2,'小明','1001','1000.00','100','1100.00','1100.00',1,'','2018-05-06 14:19:04',3),(2,2,'小明','1001',NULL,NULL,'10.00','1090.00',2,'','2018-05-06 14:20:14',3),(3,2,'小明','1001',NULL,NULL,'52.00','1038.00',2,'做了 美容','2018-05-06 14:20:55',3),(4,2,'小明','1001',NULL,NULL,'12.00','1026.00',2,'做了美甲','2018-05-06 14:21:06',3);

/*Table structure for table `t_client` */

CREATE TABLE `t_client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carno` varchar(100) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `clientTypeId` int(11) DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `carno_only_one` (`carno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_client` */

insert  into `t_client`(`id`,`carno`,`name`,`sex`,`phone`,`address`,`balance`,`createDateTime`,`remark`,`clientTypeId`,`createUserId`) values (2,'1001','小明',2,'18337537525','1212','1026.00','2018-05-06 14:18:38','12',3,3),(3,'10012','1',2,'','1','50.00','2018-05-06 14:28:22','',3,3),(4,'No12312','xingm',2,'','','0.00','2018-08-12 13:41:26',NULL,3,3),(5,'No11','cesu',1,'13888888888','地址信息地址信息','0.00','2018-08-12 13:42:10','闭住细腻被只细腻',3,3);

/*Table structure for table `t_client_type` */

CREATE TABLE `t_client_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_client_type` */

insert  into `t_client_type`(`id`,`order_`,`name`) values (3,1,'普通客户'),(4,2,'5折客户'),(5,-1,'客户11');

/*Table structure for table `t_company` */

CREATE TABLE `t_company` (
  `id` varchar(15) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `pid` varchar(15) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createBy` varchar(20) DEFAULT NULL,
  `activity` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_company` */

insert  into `t_company`(`id`,`name`,`pid`,`createTime`,`createBy`,`activity`) values ('10','中国总公司','0','2018-12-19 17:07:04','system',1),('101','中国一分部','10','2018-12-21 09:51:44','system',1),('102','中国二分部','10','2018-12-21 09:52:08','system',1),('1011','第一事业部','101','2018-12-21 09:52:27','system',1),('1012','第二事业部','101','2018-12-21 09:52:55','system',1),('10111','第一事业单位','1011','2018-12-21 09:53:50','system',1),('1021','第一事业部2','102','2018-12-21 09:54:27','system',1);

/*Table structure for table `t_config` */

CREATE TABLE `t_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `domain_name` varchar(100) DEFAULT NULL,
  `web_site` varchar(200) DEFAULT NULL,
  `headStr` text,
  `layuiStr` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_config` */

insert  into `t_config`(`id`,`domain_name`,`web_site`,`headStr`,`layuiStr`) values (1,'个人管理平台系统','http://www.###.com','<!-- 强制  高速模式 渲染网页    -->\n<meta NAME=”renderer” content=”webkit”>\n<!-- 强制  高速模式 渲染网页    -->\n\n<link href=\"/static/favicon.ico\" rel=\"shortcut icon\" />\n\n<!--添加  jq  支持加载-->\n<script	src=\"/static/easy-ui/jquery.min.js\"></script>\n<!--添加  jq  支持加载-->','<!--添加 layui  支持加载-->\n<link rel=\"stylesheet\"	href=\"/static/layui-v2.2.5/layui/css/layui.css\">\n<script	src=\"/static/layui-v2.2.5/layui/layui.js\"></script>\n<!--添加 layui  支持加载-->');

/*Table structure for table `t_cui_hai` */

CREATE TABLE `t_cui_hai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userSubId` int(11) DEFAULT NULL,
  `jieyuerenId` int(11) DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_cui_hai` */

insert  into `t_cui_hai`(`id`,`userSubId`,`jieyuerenId`,`bookId`,`createDateTime`) values (1,18,12,6,'2018-07-04 21:45:17');

/*Table structure for table `t_goods` */

CREATE TABLE `t_goods` (
  `id` varchar(35) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `describes` varchar(50) DEFAULT NULL,
  `imgPath` varchar(50) DEFAULT NULL,
  `number` int(9) DEFAULT NULL,
  `commodity` varchar(2) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `activity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_goods` */

insert  into `t_goods`(`id`,`name`,`describes`,`imgPath`,`number`,`commodity`,`status`,`startTime`,`endTime`,`activity`) values ('G001','商品1号','这个是热卖商品',NULL,145,'1','1','2018-12-01 11:22:44','2019-01-05 11:22:49',1),('1545725107208',NULL,'12312',NULL,2,'0','0',NULL,NULL,1),('1545725234631',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,1);

/*Table structure for table `t_message` */

CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_message` */

/*Table structure for table `t_question` */

CREATE TABLE `t_question` (
  `id` varchar(40) DEFAULT NULL,
  `userId` varchar(35) DEFAULT NULL,
  `title` varchar(35) DEFAULT NULL,
  `commentCount` int(10) DEFAULT NULL,
  `viewCount` int(10) DEFAULT NULL,
  `likeCount` int(10) DEFAULT NULL,
  `status` varchar(5) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_question` */

insert  into `t_question`(`id`,`userId`,`title`,`commentCount`,`viewCount`,`likeCount`,`status`,`content`) values ('12','122121','第一次发表测试问题要求',1,2,2,'1','这个是文章内容文章内容'),('13','1212','我的发扬',2,3,4,'1','阿卡哎，这个是文章的主要内容，你呀熟悉熟悉熟悉熟悉');

/*Table structure for table `t_role` */

CREATE TABLE `t_role` (
  `id` varchar(15) NOT NULL,
  `roleName` varchar(30) DEFAULT NULL,
  `roleRemark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`roleName`,`roleRemark`) values ('1545107213221','商家管理人员','商家管理人员'),('1545107318946','平台管理账号','平台管理账号专用'),('1545107414933','访客角色','注册账户专用');

/*Table structure for table `t_role_menu` */

CREATE TABLE `t_role_menu` (
  `id` varchar(15) DEFAULT NULL,
  `roleId` varchar(15) DEFAULT NULL,
  `menuId` varchar(15) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `activity` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_menu` */

insert  into `t_role_menu`(`id`,`roleId`,`menuId`,`createTime`,`activity`) values ('1545126782208','1545126782179','171000','2018-12-18 17:53:01',0),('1545126782243','1545126782179','171005','2018-12-18 17:53:01',0),('1545126782281','1545126782179','171009','2018-12-18 17:53:01',0),('1545126782312','1545126782179','171011','2018-12-18 17:53:01',0),('1545126782342','1545126782179','19','2018-12-18 17:53:01',0),('1545126782377','1545126782179','1545114625919','2018-12-18 17:53:01',0),('1545126782407','1545126782179','61010','2018-12-18 17:53:01',0),('1545126782437','1545126782179','61010','2018-12-18 17:53:01',0),('1545126782471','1545126782179','61011','2018-12-18 17:53:01',0),('1545126782503','1545126782179','61012','2018-12-18 17:53:01',0),('1545126782544','1545126782179','171010','2018-12-18 17:53:01',0),('1545126782576','1545126782179','61015','2018-12-18 17:53:01',0),('1545126782633','1545126782179','61017','2018-12-18 17:53:01',0),('1545126880705','1545125978539','171000','2018-12-18 17:54:39',0),('1545126880740','1545125978539','171005','2018-12-18 17:54:39',0),('1545126880775','1545125978539','171009','2018-12-18 17:54:39',0),('1545126880808','1545125978539','171011','2018-12-18 17:54:39',0),('1545126880838','1545125978539','1545114625919','2018-12-18 17:54:39',0),('1545126880872','1545125978539','61010','2018-12-18 17:54:39',0),('1545126880906','1545125978539','61010','2018-12-18 17:54:39',0),('1545126880941','1545125978539','61011','2018-12-18 17:54:39',0),('1545126880978','1545125978539','61012','2018-12-18 17:54:39',0),('1545126881009','1545125978539','171010','2018-12-18 17:54:40',0),('1545126881044','1545125978539','61015','2018-12-18 17:54:40',0),('1545126881093','1545125978539','61017','2018-12-18 17:54:40',0),('1545126887085','1545125978539','1545114625919','2018-12-18 17:54:46',0),('1545126887129','1545125978539','61010','2018-12-18 17:54:46',0),('1545126887188','1545125978539','61010','2018-12-18 17:54:46',0),('1545126887224','1545125978539','61011','2018-12-18 17:54:46',0),('1545126887253','1545125978539','61012','2018-12-18 17:54:46',0),('1545127911781','1545126782179','171000','2018-12-18 18:11:50',1),('1545127911826','1545126782179','171005','2018-12-18 18:11:50',1),('1545127911862','1545126782179','171009','2018-12-18 18:11:50',1),('1545127911902','1545126782179','171011','2018-12-18 18:11:50',1),('1545128533980','1545125978539','171000','2018-12-18 18:22:12',1),('1545128534023','1545125978539','171005','2018-12-18 18:22:13',1),('1545128534055','1545125978539','171009','2018-12-18 18:22:13',1),('1545128534092','1545125978539','171011','2018-12-18 18:22:13',1),('1545128639288','1545125978539','171000','2018-12-18 18:23:58',1),('1545128639328','1545125978539','171005','2018-12-18 18:23:58',1),('1545128639364','1545125978539','171009','2018-12-18 18:23:58',1),('1545128639406','1545125978539','171011','2018-12-18 18:23:58',1),('1545128639442','1545125978539','171000','2018-12-18 18:23:58',1),('1545128639479','1545125978539','171005','2018-12-18 18:23:58',1),('1545128639514','1545125978539','171009','2018-12-18 18:23:58',1),('1545128639553','1545125978539','171010','2018-12-18 18:23:58',1),('1545128639589','1545125978539','61015','2018-12-18 18:23:58',1),('1545128639626','1545125978539','61017','2018-12-18 18:23:58',1),('1545128645292','1545125978539','171000','2018-12-18 18:24:04',1),('1545128645340','1545125978539','171005','2018-12-18 18:24:04',1),('1545128645382','1545125978539','171009','2018-12-18 18:24:04',1),('1545128645419','1545125978539','171011','2018-12-18 18:24:04',1),('1545128645460','1545125978539','171000','2018-12-18 18:24:04',1),('1545128645497','1545125978539','171005','2018-12-18 18:24:04',1),('1545128645543','1545125978539','171009','2018-12-18 18:24:04',1),('1545128645583','1545125978539','1545114625919','2018-12-18 18:24:04',1),('1545128645630','1545125978539','61010','2018-12-18 18:24:04',1),('1545128645668','1545125978539','61010','2018-12-18 18:24:04',1),('1545128645708','1545125978539','61011','2018-12-18 18:24:04',1),('1545128645745','1545125978539','61012','2018-12-18 18:24:04',1),('1545128645794','1545125978539','171010','2018-12-18 18:24:04',1),('1545128645840','1545125978539','61015','2018-12-18 18:24:04',1),('1545128645884','1545125978539','61017','2018-12-18 18:24:04',1),('1545128662648','1545107414933','171000','2018-12-18 18:24:21',1),('1545128662686','1545107414933','171005','2018-12-18 18:24:21',1),('1545128662727','1545107414933','171009','2018-12-18 18:24:21',1),('1545128662766','1545107414933','171011','2018-12-18 18:24:21',1),('1545128662821','1545107414933','171000','2018-12-18 18:24:21',1),('1545128662860','1545107414933','171005','2018-12-18 18:24:21',1),('1545128662904','1545107414933','171009','2018-12-18 18:24:21',1),('1545128662963','1545107414933','61013','2018-12-18 18:24:21',1),('1545128663002','1545107414933','61014','2018-12-18 18:24:22',1),('1545128663043','1545107414933','19','2018-12-18 18:24:22',1),('1545128663087','1545107414933','1545114625919','2018-12-18 18:24:22',1),('1545128663128','1545107414933','61010','2018-12-18 18:24:22',1),('1545128663167','1545107414933','61010','2018-12-18 18:24:22',1),('1545128663210','1545107414933','61011','2018-12-18 18:24:22',1),('1545128663257','1545107414933','61012','2018-12-18 18:24:22',1),('1545128663317','1545107414933','171010','2018-12-18 18:24:22',1),('1545128663364','1545107414933','61015','2018-12-18 18:24:22',1),('1545128663402','1545107414933','61017','2018-12-18 18:24:22',1),('1545128682965','1545107318946','171000','2018-12-18 18:24:41',1),('1545128683001','1545107318946','171005','2018-12-18 18:24:42',1),('1545128683037','1545107318946','171009','2018-12-18 18:24:42',1),('1545128683076','1545107318946','171011','2018-12-18 18:24:42',1),('1545128683113','1545107318946','171000','2018-12-18 18:24:42',1),('1545128683148','1545107318946','171005','2018-12-18 18:24:42',1),('1545128683190','1545107318946','171009','2018-12-18 18:24:42',1),('1545128683226','1545107318946','61013','2018-12-18 18:24:42',1),('1545128683266','1545107318946','61014','2018-12-18 18:24:42',1),('1545128683304','1545107318946','19','2018-12-18 18:24:42',1),('1545128683351','1545107318946','1545114625919','2018-12-18 18:24:42',1),('1545128683390','1545107318946','61010','2018-12-18 18:24:42',1),('1545128683426','1545107318946','61010','2018-12-18 18:24:42',1),('1545128683463','1545107318946','61011','2018-12-18 18:24:42',1),('1545128683502','1545107318946','61012','2018-12-18 18:24:42',1),('1545128683543','1545107318946','171010','2018-12-18 18:24:42',1),('1545128683587','1545107318946','61015','2018-12-18 18:24:42',1),('1545128683629','1545107318946','61017','2018-12-18 18:24:42',1),('1545128692108','1545107213221','171000','2018-12-18 18:24:51',1),('1545128692148','1545107213221','171005','2018-12-18 18:24:51',1),('1545128692189','1545107213221','171009','2018-12-18 18:24:51',1),('1545128692229','1545107213221','171011','2018-12-18 18:24:51',1),('1545128692268','1545107213221','171000','2018-12-18 18:24:51',1),('1545128692305','1545107213221','171005','2018-12-18 18:24:51',1),('1545128692353','1545107213221','171009','2018-12-18 18:24:51',1),('1545128692392','1545107213221','61013','2018-12-18 18:24:51',1),('1545128692432','1545107213221','61014','2018-12-18 18:24:51',1);

/*Table structure for table `t_tree` */

CREATE TABLE `t_tree` (
  `id` varchar(15) NOT NULL,
  `text` varchar(46) DEFAULT NULL,
  `father` varchar(15) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `state` varchar(46) DEFAULT NULL,
  `iconCls` varchar(46) DEFAULT NULL,
  `permissions` varchar(100) DEFAULT NULL,
  `dd_id` varchar(46) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_tree` */

insert  into `t_tree`(`id`,`text`,`father`,`url`,`state`,`iconCls`,`permissions`,`dd_id`) values ('19','基础配置','-1',NULL,'close',NULL,NULL,NULL),('61010','图书管理','99','/houtai/book/manage','opend','&#xe63c;',NULL,'book'),('18','借阅','-1',NULL,'close',NULL,NULL,NULL),('171005','我的借阅记录','18','/houtai/user/sub/my','opend','&#xe612;',NULL,'submy'),('171000','我要借阅','18','/houtai/book/sub/manage','opend','&#xe612;',NULL,'clienttype'),('171009','我的催还记录','18','/houtai/cuihai/my','opend','&#xe612;',NULL,'mysub'),('61013','借阅记录','18','/houtai/user/sub/manage','opend','&#xe612;',NULL,'sub'),('61015','读者信息管理和维护','99','/houtai/user/manage','opend','&#xe612;',NULL,'user'),('61014','催还记录','18','/houtai/cuihai/manage','opend','&#xe612;',NULL,'cui'),('171010','留言板','99','/houtai/message/my','opend','&#xe612;',NULL,'myliuyan'),('61017','留言板管理','99','/houtai/message/manage','opend','&#xe612;',NULL,'liuyan'),('99','服务商管理','-1',NULL,'close',NULL,NULL,NULL),('6','系统管理','-1',NULL,'close',NULL,NULL,NULL),('61010','图书管理','99','/houtai/book/manage','opend','&#xe63c;',NULL,'clientType'),('17','用户管理','-1',NULL,'close',NULL,NULL,NULL),('171005','客户管理','17','/houtai/client/manage','opend','&#xe612;',NULL,'client'),('171000','客户类型','17','/houtai/clientType/manage','opend','&#xe612;',NULL,'clienttype'),('171009','客户储值记录','17','/houtai/deposit/trade/manage','opend','&#xe612;',NULL,'DepositTrade'),('171011','客户消费记录','17','/houtai/consumption/manage','opend','&#xe612;',NULL,'consumption'),('61011','菜单管理','6','/menu/manage','opend','&#xe63c;',NULL,'menuManage'),('61012','角色管理','6','/role/manage','opend','&#xe63c;',NULL,'roleManege'),('61013','用户管理','6','/user/manage','close','&#xe63c;',NULL,'userManage'),('15452','公司管理','6','/company/manage','close','&#xe612;',NULL,'companyManage'),('61014','商品管理','19','/goods/manage','close','&#xe612;',NULL,'goodsManage'),('1545710101123',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1545710176907',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1545724362534',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t_user` */

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num_` varchar(60) DEFAULT NULL,
  `trueName` varchar(60) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `sex_` varchar(10) DEFAULT NULL,
  `menuIds` varchar(500) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `type` int(5) DEFAULT NULL,
  `token` varchar(20) DEFAULT NULL,
  `openId` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `weiyi` (`num_`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`num_`,`trueName`,`phone`,`sex_`,`menuIds`,`createDateTime`,`password`,`remark`,`type`,`token`,`openId`) values (9,'admin','管理员','18337537555','男','6,17,18,19,99,61010,61015,61013,61014,61017,171000,171005,171009,171011,171010,61011,61012,15452,61013','2018-06-23 15:50:59','0680d00353f3555fe7c01f11ed89bf8b','2',1,NULL,NULL),(12,'100','测试账号','12345678900','女','17,171000,171005,171009,171011,171010','2018-07-01 23:58:07','0680d00353f3555fe7c01f11ed89bf8b','233232',2,NULL,NULL),(13,'200','小红','12345678900','男','17,171000,171005,171009,171011,171010','2018-07-01 23:59:49','0680d00353f3555fe7c01f11ed89bf8b','在这里备注',1,NULL,NULL);

/*Table structure for table `t_user_sub` */

CREATE TABLE `t_user_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `bookId` int(11) DEFAULT NULL,
  `subDateTime` datetime DEFAULT NULL,
  `returnDateTime` datetime DEFAULT NULL,
  `type` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `t_user_sub` */

insert  into `t_user_sub`(`id`,`userId`,`bookId`,`subDateTime`,`returnDateTime`,`type`) values (14,12,6,'2018-07-01 23:58:39','2018-07-02 00:02:34',2),(15,12,3,'2018-07-01 23:59:30','2018-07-02 00:02:32',2),(16,13,4,'2018-07-02 00:00:41','2018-07-02 00:03:09',2),(17,13,3,'2018-07-02 00:00:43','2018-07-02 00:03:04',2),(18,12,6,'2018-07-04 21:44:56',NULL,1),(19,12,3,'2018-07-04 21:44:57',NULL,1),(20,9,7,'2018-08-15 00:10:23',NULL,1),(21,9,7,'2018-08-15 00:10:26',NULL,1),(22,9,4,'2018-08-15 00:10:34',NULL,1),(23,9,3,'2018-08-15 00:10:36',NULL,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
