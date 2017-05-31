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
  `userid` int(11) DEFAULT NULL COMMENT '评价人ID',
  `username` text COMMENT '评价人姓名',
  `body` text COMMENT '评价内容',
  `createtime` varchar(20) DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('15', '3', '1', '在路上', '好吃吗', '2014-05-04');
INSERT INTO `comments` VALUES ('16', '5', '1', '在路上', '很好吃呀！', '2014-05-04');
INSERT INTO `comments` VALUES ('17', '5', '1', '在路上', '怎样说呢', '2014-05-04');
INSERT INTO `comments` VALUES ('18', '5', '1', '在路上', '哈哈哈哈哈，不错不错', '2014-05-04');
INSERT INTO `comments` VALUES ('19', '1', '1', '在路上', '怎样啊，好吃吗', '2014-05-04');
INSERT INTO `comments` VALUES ('20', '1', '1', '在路上', '哈哈，不错的', '2014-05-04');
INSERT INTO `comments` VALUES ('21', '5', '1', '在路上', '好像很好的样子', '2014-05-04');

-- ----------------------------
-- Table structure for breakfast
-- ----------------------------
DROP TABLE IF EXISTS `breakfast`;
CREATE TABLE `breakfast` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` text COMMENT '标题',
  `typeid` int(11) DEFAULT NULL COMMENT '类型ID',
  `typename` text COMMENT '类型名称',
  `intro` text COMMENT '简介',
  `img_url` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `price` double(11,1) DEFAULT NULL COMMENT '单价',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of breakfast
-- ----------------------------
INSERT INTO `breakfast` VALUES ('1', '薄皮小笼包', '1', '单品', '浓郁的肉香，搭配香醋，居家早餐首选', 'xiaolongbao.jpg', '8.0', '50');
INSERT INTO `breakfast` VALUES ('2', '红枣豆浆', '3', '特色', '红枣有很好的补血理气功效，爱美的女性多喝红枣豆浆，面容美美哒。', 'doujiang.jpg', '3.0', '100');
INSERT INTO `breakfast` VALUES ('3', '荠菜鲜肉馄饨', '1', '单品', '之前包馄饨我特别喜欢用细香芹，香芹在剁碎调馅时会适量的出些汁，这些蔬菜汁会被肉馅吸收，所以馄饨才会吃起来香而不腻，鲜而不柴。但荠菜和香芹不同，一来本身含水量就低，二来包馄饨荠菜要先经过焯烫，所以为了肉馅不柴，我特意煮了一点花椒水，将花椒水揣进肉馅里面，既可以去腥提鲜，还可以让肉馅吃起来更加的鲜嫩多汁，这也算是调制肉馅的一个小技巧吧。', 'hundun.jpg', '5.0', '40');
INSERT INTO `breakfast` VALUES ('4', '亚麻籽软式全麦蛋饼', '1', '单品', '亚麻籽对身体的好处就不用多说了吧，美味的蛋饼更加健康。', 'jianbing.jpg', '5.0', '20');
INSERT INTO `breakfast` VALUES ('5', '培根芝士堡', '1', '单品', '提前做好几个汉堡坯子，早上起来稍微花上一点儿准备一下，就能吃到荤素搭配营养均衡的早餐啦~~ 材料：主面团：高筋面粉265g，奶粉10g，水130g，蛋液25g，细砂糖25g，盐2g，酵母3g，黄油25g;表面装饰：蛋液适量，白芝麻适量;夹馅：鸡蛋2个，芝士片2片，培根2片，生菜叶2片，西红柿2片，沙拉酱适量', 'hanbao.jpg', '12.0', '30');
INSERT INTO `breakfast` VALUES ('6', '黄瓜鸡蛋三明治', '1', '单品', '一份简单的三明治，清爽脆嫩的黄瓜配上营养的鸡蛋，味道好又有营养哦！', 'sanmingzhi.jpg', '8.0', '50');
INSERT INTO `breakfast` VALUES ('7', '混沌+鸡蛋+苹果', '2', '套餐', '早晨起来来点简单馄饨，再加一个鸡蛋和一个苹果，特适合带早饭到办公室的上班族和给妈妈要早起上班的孩子', 'taocan.jpg', '10.0', '30');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` text COMMENT '下单人名称',
  `status` int(11) DEFAULT NULL COMMENT '状态 0 进行中 1 已完成 -1 已取消',
  `price` double(10,1) DEFAULT NULL COMMENT '总价',
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

INSERT INTO `orders_breakfast` VALUES('1','1','5','薄皮小笼包','2','8.0');


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
INSERT INTO `types` VALUES ('1', '单品');
INSERT INTO `types` VALUES ('2', '套餐');
INSERT INTO `types` VALUES ('3', '特色');

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
INSERT INTO `users` VALUES ('1', 'test00', '在路上', '111111','touxiang1.jpg','18310137365');
INSERT INTO `users` VALUES ('2', 'zhangsan', '张三', '111111','touxiang2.jpeg','13521469616');

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
INSERT INTO `address` VALUES ('1','1','王','先生','13521469616','北京联合大学北门');


