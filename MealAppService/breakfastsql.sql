/*
Navicat MySQL Data Transfer

Source Server         : breakfast
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : app

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2014-05-05 23:13:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(30) DEFAULT NULL,
  `passwords` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', '111111');

-- ----------------------------
-- Table structure for chief
-- ----------------------------
DROP TABLE IF EXISTS `chief`;
CREATE TABLE `chief` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(30) DEFAULT NULL,
  `passwords` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chief
-- ----------------------------
INSERT INTO `chief` VALUES ('2', 'wang', '12');

-- ----------------------------
-- Table structure for deliveryman
-- ----------------------------
DROP TABLE IF EXISTS `deliveryman`;
CREATE TABLE `deliveryman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(30) DEFAULT NULL,
  `passwords` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deliveryman
-- ----------------------------
INSERT INTO `deliveryman` VALUES ('1', 'deliveryman', 'deliveryman');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `breakfastid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL COMMENT '������ID',
  `username` text COMMENT '����������',
  `body` text COMMENT '��������',
  `createtime` varchar(20) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('15', '3', '1', '��·��', '�ó���', '2014-05-04');
INSERT INTO `comments` VALUES ('16', '5', '1', '��·��', '�ܺó�ѽ��', '2014-05-04');
INSERT INTO `comments` VALUES ('17', '5', '1', '��·��', '����˵��', '2014-05-04');
INSERT INTO `comments` VALUES ('18', '5', '1', '��·��', '������������������', '2014-05-04');
INSERT INTO `comments` VALUES ('19', '1', '1', '��·��', '���������ó���', '2014-05-04');
INSERT INTO `comments` VALUES ('20', '1', '1', '��·��', '�����������', '2014-05-04');
INSERT INTO `comments` VALUES ('21', '5', '1', '��·��', '����ܺõ�����', '2014-05-04');

-- ----------------------------
-- Table structure for breakfast
-- ----------------------------
DROP TABLE IF EXISTS `breakfast`;
CREATE TABLE `breakfast` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` text COMMENT '����',
  `typeid` int(11) DEFAULT NULL COMMENT '����ID',
  `typename` text COMMENT '��������',
  `intro` text COMMENT '���',
  `img_url` varchar(100) DEFAULT NULL COMMENT 'ͼƬ����',
  `price` double(11,1) DEFAULT NULL COMMENT '����',
  `amount` int(11) DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of breakfast
-- ----------------------------
INSERT INTO `breakfast` VALUES ('1', '��ƤС����', '1', '��Ʒ', 'Ũ�������㣬������ף��Ӽ������ѡ', 'xiaolongbao.jpg', '8.0', '50');
INSERT INTO `breakfast` VALUES ('2', '���涹��', '3', '��ɫ', '�����кܺõĲ�Ѫ������Ч��������Ů�Զ�Ⱥ��涹�������������ա�', 'doujiang.jpg', '3.0', '100');
INSERT INTO `breakfast` VALUES ('3', '�����������', '1', '��Ʒ', '֮ǰ��������ر�ϲ����ϸ���ۣ������ڶ������ʱ�������ĳ�Щ֭����Щ�߲�֭�ᱻ�������գ�������⽲Ż������������壬�ʶ����񡣵����˺����۲�ͬ��һ������ˮ���͵ͣ��������������Ҫ�Ⱦ������̣�����Ϊ�����ڲ�������������һ�㻨��ˮ��������ˮ�����������棬�ȿ���ȥ�����ʣ������������ڳ��������ӵ����۶�֭����Ҳ���ǵ������ڵ�һ��С���ɰɡ�', 'hundun.jpg', '5.0', '40');
INSERT INTO `breakfast` VALUES ('4', '��������ʽȫ�󵰱�', '1', '��Ʒ', '�����Ѷ�����ĺô��Ͳ��ö�˵�˰ɣ���ζ�ĵ������ӽ�����', 'jianbing.jpg', '5.0', '20');
INSERT INTO `breakfast` VALUES ('5', '���֥ʿ��', '1', '��Ʒ', '��ǰ���ü����������ӣ�����������΢����һ���׼��һ�£����ܳԵ����ش���Ӫ������������~~ ���ϣ������ţ��߽����265g���̷�10g��ˮ130g����Һ25g��ϸɰ��25g����2g����ĸ3g������25g;����װ�Σ���Һ��������֥������;���ڣ�����2����֥ʿƬ2Ƭ�����2Ƭ������Ҷ2Ƭ��������2Ƭ��ɳ��������', 'hanbao.jpg', '12.0', '30');
INSERT INTO `breakfast` VALUES ('6', '�ƹϼ���������', '1', '��Ʒ', 'һ�ݼ򵥵������Σ���ˬ���۵Ļƹ�����Ӫ���ļ�����ζ��������Ӫ��Ŷ��', 'sanmingzhi.jpg', '8.0', '50');
INSERT INTO `breakfast` VALUES ('7', '����+����+ƻ��', '2', '�ײ�', '�糿�����������⽣��ټ�һ��������һ��ƻ�������ʺϴ��緹���칫�ҵ��ϰ���͸�����Ҫ�����ϰ�ĺ���', 'taocan.jpg', '10.0', '30');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` text COMMENT '�µ�������',
  `status` int(11) DEFAULT NULL COMMENT '״̬ 0 ������ 1 ����� -1 ��ȡ��',
  `price` double(10,1) DEFAULT NULL COMMENT '�ܼ�',
  `createtime` varchar(15) DEFAULT NULL,
  `deliveryway` varchar(30),
  `deliverytime` varchar(30),
  `payway` varchar(30),
  `addressid` int(11),
  `orderid` int(11),
  `node` varchar(100),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;



-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders_breakfast`;
CREATE TABLE `orders_breakfast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `breakfastid` int(11),
  `orderid` int(11),
  `title` varchar(30),
  `number` int(11),
  `price` double(11,1),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT INTO `orders_breakfast` VALUES('1','1','5','��ƤС����','2','8.0');


-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` text CHARACTER SET utf8,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('1', '��Ʒ');
INSERT INTO `types` VALUES ('2', '�ײ�');
INSERT INTO `types` VALUES ('3', '��ɫ');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(30) DEFAULT NULL,
  `name` text,
  `passwords` varchar(30) DEFAULT NULL,
  `img_url` varchar(100),
  `phone` varchar(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'test00', '��·��', '111111','touxiang1.jpg','18310137365');
INSERT INTO `users` VALUES ('2', 'zhangsan', '����', '111111','touxiang2.jpeg','13521469616');

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `realname` text,
  `sex` varchar(30),
  `phone` varchar(30),
  `address` varchar(100),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1','1','��','����','13521469616','�������ϴ�ѧ����');


