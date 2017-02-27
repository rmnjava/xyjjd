# Host: 192.168.8.47  (Version: 5.1.66)
# Date: 2013-12-30 17:09:20
# Generator: MySQL-Front 5.3  (Build 3.22)

/*!40101 SET NAMES utf8 */;

#
# Source for table "t_sys_permission"
#

DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation` varchar(50) DEFAULT NULL,
  `operation_cn_name` varchar(50) DEFAULT NULL,
  `res_cn_name` varchar(50) DEFAULT NULL,
  `res_name` varchar(50) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`permission_id`),
  KEY `FK57E601AC65AA4BC6` (`menu_id`),
  CONSTRAINT `FK57E601AC65AA4BC6` FOREIGN KEY (`menu_id`) REFERENCES `t_sys_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4105 DEFAULT CHARSET=utf8;

#
# Data for table "t_sys_permission"
#

INSERT INTO `t_sys_permission` VALUES (1001,'read','global.read','menu.topo.globalTopo','globalTopo',10),(1101,'read','global.read','menu.system.customer','customer',1100),(1201,'create','global.create','menu.system.user','user',1200),(1202,'update','global.update','menu.system.user','user',1200),(1203,'delete','global.delete','menu.system.user','user',1200),(1301,'update','global.update','menu.system.role','role',1300),(1302,'create','global.create','menu.system.role','role',1300),(1303,'delete','global.delete','menu.system.role','role',1300),(2101,'read','global.read','menu.customer.region','region',2100),(2201,'read','global.read','menu.customer.location','location',2200),(2301,'read','global.read','menu.customer.department','department',2300),(2401,'read','global.read','menu.currUser','customer',2400),(3003,'detail','buz.rootNe.detail','menu.rootne.list','rootne',3100),(4102,'create','global.create','menu.customer.department','department',2300),(4103,'update','global.update','menu.customer.department','department',2300),(4104,'delete','global.delete','menu.customer.department','department',2300);
