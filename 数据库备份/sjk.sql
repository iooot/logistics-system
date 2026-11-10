/*
SQLyog Ultimate v8.32 
MySQL - 8.0.41 : Database - logisticssys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`logisticssys` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `logisticssys`;

/*Table structure for table `cdinfo` */

DROP TABLE IF EXISTS `cdinfo`;

CREATE TABLE `cdinfo` (
  `id` varchar(50) NOT NULL,
  `cname` varchar(300) DEFAULT NULL COMMENT '菜单名称',
  `cpath` varchar(2000) DEFAULT NULL COMMENT '菜单路径',
  `fnid` varchar(50) DEFAULT NULL COMMENT '父级节点名称',
  `indx` int DEFAULT NULL COMMENT '排序',
  `level` varchar(50) DEFAULT NULL COMMENT '菜单层级',
  `nrole` varchar(50) DEFAULT NULL COMMENT '0-所有人；1-超级管理；2-普通管理；3-公用菜单',
  `bem` varchar(50) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `cdinfo` */

insert  into `cdinfo`(`id`,`cname`,`cpath`,`fnid`,`indx`,`level`,`nrole`,`bem`) values ('01e60717-e3a2-11ee-b23b-00fff1b46f31','用户列表','/function/userGN/user-list.html','c467f553-179e-11f0-891b-00ff2728bbe1',1,'1','2','&#xe697;'),('01e60717-e3a2-11ee-b23b-00fff1b46f38','系统信息','/disphome/xtInfo','01e60717-e3a2-11ee-b23b-00fff1b46f39',0,'1','2','&#xe697;'),('01e60717-e3a2-11ee-b23b-00fff1b46f39','权限管理','0','0',9999,'1','2','&#xe723;'),('01e60717-e3a2-11ee-b23b-00fff1b46f40','禁用账号','/function/userGN/user-del.html','c467f553-179e-11f0-891b-00ff2728bbe1',2,'1','2','&#xe697;'),('01e60717-e3a2-11ee-b23b-00fff1b46f51','角色管理','/function/role/roleList.html','01e60717-e3a2-11ee-b23b-00fff1b46f39',1,'1','2','&#xe697;'),('01e60717-e3a2-11ee-b23b-00fff1b46f52','菜单管理','/function/cdInfo/cd-list.html','01e60717-e3a2-11ee-b23b-00fff1b46f39',2,'1','2','&#xe697;'),('086fd03f-183b-11f0-891b-00ff2728bbe1','个人信息维护','/disphome/userInfo?id=select&target=personchange','2b2f7962-183a-11f0-891b-00ff2728bbe1',1,'1','3','&#xe723;'),('2b2f7962-183a-11f0-891b-00ff2728bbe1','个人信息','0','0',4,'1','3','&#xe723;'),('50925c07-b176-11f0-ace3-00ff2728bbe1','物流论坛','0','0',3,'1','3','&#xe723;'),('76b37ac2-b344-11f0-ace3-00ff2728bbe1','物流订单录入','/dispOrder/addNewOrder','fdb5e60c-b178-11f0-ace3-00ff2728bbe1',2,'1','1','&#xe723;'),('81e04ee6-b178-11f0-ace3-00ff2728bbe1','公告管理','/function/forum/bbs-list.html','50925c07-b176-11f0-ace3-00ff2728bbe1',1,'1','2','&#xe723;'),('9d5355ed-b1ae-11f0-ace3-00ff2728bbe1','物流订单查询','/function/order/order-list.html','fdb5e60c-b178-11f0-ace3-00ff2728bbe1',0,'1','2','&#xe723;'),('b8c50000-b176-11f0-ace3-00ff2728bbe1','公告列表','/function/forum/outer/bbs-list.html','50925c07-b176-11f0-ace3-00ff2728bbe1',0,'1','3','&#xe723;'),('c467f553-179e-11f0-891b-00ff2728bbe1','用户管理','0','0',5,'1','2','&#xe723;'),('e132480a-b28e-11f0-ace3-00ff2728bbe1','物流信息录入','/dispOrder/addNewOrderWU','fdb5e60c-b178-11f0-ace3-00ff2728bbe1',1,'1','2','&#xe723;'),('fbaafeda-183a-11f0-891b-00ff2728bbe1','个人中心','/function/userGN/personcenter/index.html','2b2f7962-183a-11f0-891b-00ff2728bbe1',0,'1','3','&#xe723;'),('fd730282-b346-11f0-ace3-00ff2728bbe1','客户订单查询','/function/order/personorder-list.html','fdb5e60c-b178-11f0-ace3-00ff2728bbe1',3,'1','1','&#xe723;'),('fdb5e60c-b178-11f0-ace3-00ff2728bbe1','物流管理','0','0',2,'1','3','&#xe723;');

/*Table structure for table `sys_fuc_addr` */

DROP TABLE IF EXISTS `sys_fuc_addr`;

CREATE TABLE `sys_fuc_addr` (
  `id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `orderid` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `cartaddress` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `orderstute` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_fuc_addr` */

insert  into `sys_fuc_addr`(`id`,`orderid`,`cartaddress`,`createtime`,`orderstute`,`ctime`) values ('1','0360048d-b1c2-11f0-ace3-00ff2728bbe1','123','2025-10-28 00:00:00',NULL,NULL),('2','0360048d-b1c2-11f0-ace3-00ff2728bbe1','222','2025-10-27 00:00:00',NULL,NULL),('422f7e28-b33f-11f0-ace3-00ff2728bbe1','0360048d-b1c2-11f0-ace3-00ff2728bbe1','广东xxxx市','2025-10-27 22:14:00',NULL,'2025-10-27 22:14:32');

/*Table structure for table `sys_fuc_bbs` */

DROP TABLE IF EXISTS `sys_fuc_bbs`;

CREATE TABLE `sys_fuc_bbs` (
  `id` varchar(50) NOT NULL,
  `bbsname` varchar(500) DEFAULT NULL COMMENT '帖子名称',
  `bbstype` varchar(50) DEFAULT NULL COMMENT '帖子类型',
  `bbscontent` varchar(500) DEFAULT NULL COMMENT '帖子内容',
  `bbscreator` varchar(200) DEFAULT NULL COMMENT '帖子创建人',
  `bbscreatetime` datetime DEFAULT NULL COMMENT '帖子创建时间',
  `state` varchar(20) DEFAULT NULL COMMENT '0：置顶贴；1：正常贴，2-删除贴',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_fuc_bbs` */

insert  into `sys_fuc_bbs`(`id`,`bbsname`,`bbstype`,`bbscontent`,`bbscreator`,`bbscreatetime`,`state`) values ('1','帖子名称','帖子类型','帖子内容','创建人','2024-04-03 11:36:51','1'),('2','帖子名称2','帖子类型2','帖子内容2','创建人','2024-04-03 11:46:51','1'),('4c5c2658-185c-11f0-891b-00ff2728bbe1','新帖子','知识科普类','小动物要定期洗澡清理，才能更加干净','管理员','2025-04-13 19:41:54','0');

/*Table structure for table `sys_fuc_order` */

DROP TABLE IF EXISTS `sys_fuc_order`;

CREATE TABLE `sys_fuc_order` (
  `id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `ordername` varchar(400) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单名称',
  `ordertime` datetime DEFAULT NULL COMMENT '下单时间',
  `orderuser` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '下单用户',
  `ordernum` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `revicename` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '收件人',
  `tel` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '联系方式',
  `orderstute` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单状态',
  `bem` varchar(500) DEFAULT NULL COMMENT '备注',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `ordermoney` varchar(200) DEFAULT NULL COMMENT '订单金额',
  `sendWU` varchar(200) DEFAULT NULL COMMENT '发货地址',
  `reviceWU` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `orderuserid` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '下单用户id',
  `protype` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '物品类型',
  `proheight` varchar(200) DEFAULT NULL COMMENT '物品重量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_fuc_order` */

insert  into `sys_fuc_order`(`id`,`ordername`,`ordertime`,`orderuser`,`ordernum`,`revicename`,`tel`,`orderstute`,`bem`,`createtime`,`ordermoney`,`sendWU`,`reviceWU`,`orderuserid`,`protype`,`proheight`) values ('0360048d-b1c2-11f0-ace3-00ff2728bbe1',NULL,'2025-10-26 00:00:00','管理员','ORD_20251026001','收件人1','联系方式1',NULL,'备注1','2025-10-26 00:45:28','10001','发货地址1','收货地址1','3f462bb8-cfe3-11ed-8c25-00e04cf193f7','衣物','物品重量1'),('c28fb192-b345-11f0-ace3-00ff2728bbe1',NULL,'2025-10-27 00:00:00','测试用户1','ORD_20251027001','11','123',NULL,'123','2025-10-27 23:01:04','123','11','123','e1e4e1ee-b342-11f0-ace3-00ff2728bbe1','文件','123');

/*Table structure for table `sys_fuc_pl` */

DROP TABLE IF EXISTS `sys_fuc_pl`;

CREATE TABLE `sys_fuc_pl` (
  `id` varchar(50) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `creator` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `bbsid` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_fuc_pl` */

insert  into `sys_fuc_pl`(`id`,`content`,`creator`,`createtime`,`bbsid`) values ('1','测试意见1','管理员','2024-03-25 02:22:07','1'),('2','测试意见2','用户1','2024-03-25 02:24:07','1'),('3','测试意见3','用户2','2024-03-25 02:25:07','1'),('4','测试意见4','用户3','2024-03-25 02:26:07','1'),('5','测试意见5','用户4','2024-03-25 02:23:07','1'),('7b0b0b94-ea0e-11ee-b23b-00fff1b46f39','33311','管理员','2024-03-25 02:42:36','1'),('92cdf5bd-ea0e-11ee-b23b-00fff1b46f39','1323','管理员','2024-03-25 02:43:16','1'),('985ac8fb-ea0e-11ee-b23b-00fff1b46f39','33333','管理员','2024-03-25 02:43:26','1'),('16c392bf-f197-11ee-89f7-00fff1b46f39','123','管理员','2024-04-03 16:48:51','1'),('1db27157-f197-11ee-89f7-00fff1b46f39','33333333333','管理员','2024-04-03 16:49:03','1'),('4237305a-f19e-11ee-89f7-00fff1b46f39','12313231','管理员','2024-04-03 17:40:11','1'),('48a68269-f19e-11ee-89f7-00fff1b46f39','21412414124','管理员','2024-04-03 17:40:21','2'),('76e09bc9-f19e-11ee-89f7-00fff1b46f39','1113','管理员','2024-04-03 17:41:39','2'),('a3f0b196-17ba-11f0-891b-00ff2728bbe1','测试帖子',NULL,'2025-04-13 00:24:43','2'),('9b404716-184e-11f0-891b-00ff2728bbe1','133',NULL,'2025-04-13 18:03:53','2'),('3b7ce7fb-185c-11f0-891b-00ff2728bbe1','新帖评论',NULL,'2025-04-13 19:41:26','2'),('50e7d240-185c-11f0-891b-00ff2728bbe1','我觉得也是',NULL,'2025-04-13 19:42:02','4c5c2658-185c-11f0-891b-00ff2728bbe1'),('2103a2f7-b178-11f0-ace3-00ff2728bbe1','123',NULL,'2025-10-25 15:56:35','4c5c2658-185c-11f0-891b-00ff2728bbe1');

/*Table structure for table `sys_fuc_users` */

DROP TABLE IF EXISTS `sys_fuc_users`;

CREATE TABLE `sys_fuc_users` (
  `id` varchar(50) NOT NULL,
  `logname` varchar(200) DEFAULT NULL COMMENT '登录账号',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名称',
  `pwd` varchar(200) DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(200) DEFAULT NULL COMMENT '联系方式',
  `email` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `role` varchar(200) DEFAULT NULL COMMENT '0-所有人；1-超级管理；2-普通管理；3-公用菜单',
  `bem` mediumtext,
  `rowstate` varchar(20) DEFAULT NULL COMMENT '账号状态',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `homeAddress` varchar(500) DEFAULT NULL COMMENT '家庭地址',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_fuc_users` */

insert  into `sys_fuc_users`(`id`,`logname`,`username`,`pwd`,`phone`,`email`,`role`,`bem`,`rowstate`,`sex`,`homeAddress`,`createtime`) values ('3f462bb8-cfe3-11ed-8c25-00e04cf193f7','admin','管理员','2f7b52aacfbf6f44e13d27656ecb1f59','13631229337','test1@qq.com','2','    我叫XXX，毕业于XX大学XX专业，主修XX方向。在校期间，我系统学习了专业课程，并取得了优异的成绩，对XX领域有了深入的了解和扎实的理论基础。\n除了学术方面的成就，我还积极参与了多项实践活动。我曾在XX公司实习，负责XX项目，通过实践，我提升了自己的专业技能和团队协作能力。同时，我还担任了学生会XX部门的部长，组织并参与了多项校园活动，这些经历锻炼了我的组织能力和领导能力。\n\n我具备扎实的专业知识、良好的沟通能力和团队协作精神。我善于分析问题、解决问题，并具备创新思维和较强的学习能力。我相信自己能够迅速适应新环境，为公司的发展贡献自己的力量。\n此外，我对贵公司的发展历程和企业文化非常感兴趣，认为贵公司是一个充满活力和创新精神的团队。我渴望能够加入贵公司，与优秀的同事们一起工作，共同实现公司的目标。\n非常感谢您给我这次面试的机会，我期待能够成为贵公司的一员，共同开创美好的未来。谢谢！','1','女','家庭地址','2025-10-04 00:00:00'),('485d8fcc-e471-11ee-b23b-00fff1b46f39','zuzhang','店主','2f7b52aacfbf6f44e13d27656ecb1f59','136xxxxxx','电子邮箱','4',' 大家好，我是店长。非常荣幸能在这里向大家介绍自己以及我们充满爱与欢乐的宠物销售店。\n我从事宠物行业已有[X]年时间，从最初对宠物的简单喜爱，到深入了解各类宠物的习性、养护知识，再到如今经营一家宠物销售店，每一步都倾注了我对宠物事业的热忱与专注。\n我们店铺就像一个温馨的宠物大家庭，汇聚了众多可爱的宠物伙伴。这里有活泼好动的小狗，它们眼神灵动，充满活力，每一只都拥有独特的性格；还有温顺乖巧的小猫，毛茸茸的身体，柔软的叫声，能瞬间治愈人心。此外，店内还有小巧玲珑的仓鼠、聪明机灵的鹦鹉等各类宠物，满足不同顾客的喜好。\n为了给宠物们提供最好的生活环境，我们店内设施齐全且卫生整洁。每一处角落都经过精心布置，让宠物们能在这里舒适地生活。同时，我们严格把控宠物的来源，确保它们都来自正规、健康的繁殖场，每一只宠物在售出前都会进行全面的健康检查，让顾客买得放心。\n作为店长，我始终秉持着“以宠为本，服务至上”的理念。不仅为顾客提供优质的宠物，还会耐心地传授宠物养护知识，从饮食搭配到日常训练，事无巨细地给予指导。后续也会持续关注顾客和宠物的相处情况，提供必要的支持和帮助。\n如果您正在寻找一只可爱的宠物伙伴，欢迎来到我们[店铺名称]，我会用我的专业和热情，为您和宠物搭建起一座美好的桥梁。','1','男','家庭地址','2025-10-04 00:00:00'),('78e26b7d-178c-11f0-891b-00ff2728bbe1','cs1','首页注册的用户-修改','2f7b52aacfbf6f44e13d27656ecb1f59','联系方式1','电子邮箱1','3','  个人介绍1','2','女','家庭地址1','2025-10-04 00:00:00'),('b12b1311-178c-11f0-891b-00ff2728bbe1','cs2','用户管理注册的用户','2f7b52aacfbf6f44e13d27656ecb1f59','联系方式','电子邮箱','3',' 个人介绍','2','男','家庭地址','2025-10-04 00:00:00'),('e1e4e1ee-b342-11f0-ace3-00ff2728bbe1','user1','测试用户1','2f7b52aacfbf6f44e13d27656ecb1f59','136zxxxx','6099@qq.com','1','测试用户','1','男','11xxx','2025-10-27 22:40:28');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL,
  `val` varchar(50) DEFAULT NULL COMMENT '角色名',
  `vkey` int DEFAULT NULL COMMENT '角色代码',
  `dicName` varchar(50) DEFAULT NULL COMMENT '所属字典组名',
  `did` varchar(50) DEFAULT NULL COMMENT '所属字典组id',
  `createtime` date DEFAULT NULL,
  `bem` varchar(500) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`val`,`vkey`,`dicName`,`did`,`createtime`,`bem`) values ('1','管理员',2,'用户权限','1','2024-04-10',''),('2','普通用户',1,'用户权限','1','2024-04-10',NULL),('3','公用菜单',3,'用户权限','1','2024-04-10',NULL);

/*Table structure for table `systeminfo` */

DROP TABLE IF EXISTS `systeminfo`;

CREATE TABLE `systeminfo` (
  `id` varchar(50) NOT NULL,
  `sysname` varchar(200) DEFAULT NULL COMMENT '系统名称',
  `leibie` varchar(50) DEFAULT NULL COMMENT '类别',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `xc1` varchar(500) DEFAULT NULL,
  `xc2` varchar(500) DEFAULT NULL,
  `xc3` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `systeminfo` */

insert  into `systeminfo`(`id`,`sysname`,`leibie`,`content`,`xc1`,`xc2`,`xc3`) values ('111','物流管理系统','物流管理系统','物流管理系统','','',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
