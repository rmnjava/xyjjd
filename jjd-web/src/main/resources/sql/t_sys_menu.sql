# Host: 192.168.8.47  (Version: 5.1.66)
# Date: 2013-12-30 17:25:42
# Generator: MySQL-Front 5.3  (Build 3.22)

/*!40101 SET NAMES utf8 */;

#
# Source for table "t_sys_menu"
#

DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias_name` varchar(50) DEFAULT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `FK2B50BAFCA86B909B` (`parent_id`),
  CONSTRAINT `FK2B50BAFCA86B909B` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3101 DEFAULT CHARSET=utf8;

#
# Data for table "t_sys_menu"
#

INSERT INTO `t_sys_menu` VALUES (10,'topoMgr','menu.topo','/buz/topo.do?method=showTopo',0,NULL),(1000,'systemMgr','menu.system',NULL,1000,NULL),(1100,'customer','menu.system.customer','/buz/customer.do?method=listPage',1100,1000),(1200,'user','menu.system.user','/system/user.do?method=listPage',1200,1000),(1300,'role','menu.system.role','/system/role.do?method=listPage',1300,1000),(2000,'customerMgr','menu.customer',NULL,2000,NULL),(2100,'region','menu.customer.region','/buz/region.do?method=listPage',2100,2000),(2200,'location','menu.customer.location','/buz/location.do?method=listPage',2200,2000),(2300,'department','menu.customer.department','/buz/department.do?method=listPage',2300,2000),(2400,'customer','menu.currUser','/system/index.do?method=index',2400,2000),(3000,'rootneMgr','menu.rootne',NULL,3000,NULL),(3100,'rootne','menu.rootne.list','/buz/rootNe.do?method=listPage',3100,3000);
