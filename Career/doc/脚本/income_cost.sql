/*
Navicat MySQL Data Transfer

Source Server         : Career
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : myinfo

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2014-09-11 17:26:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `income_cost`
-- ----------------------------
DROP TABLE IF EXISTS `income_cost`;
CREATE TABLE `income_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income` float(11,1) DEFAULT '0.0',
  `cost` float(11,1) DEFAULT '0.0',
  `date` date DEFAULT NULL,
  `usage` varchar(100) DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income_cost
-- ----------------------------
INSERT INTO `income_cost` VALUES ('1', '0.0', '5.5', '2014-08-08', '吃饭', '早餐');
INSERT INTO `income_cost` VALUES ('2', '0.0', '104.0', '2014-08-09', '吃饭', '请大仙吃饭');
INSERT INTO `income_cost` VALUES ('3', '0.0', '13.0', '2014-08-10', '吃饭', '午饭');
INSERT INTO `income_cost` VALUES ('4', '0.0', '129.0', '2014-08-10', '购物', '超市刷卡');
INSERT INTO `income_cost` VALUES ('5', '0.0', '59.8', '2014-08-10', '购物', '给杨鹏买电影票');
INSERT INTO `income_cost` VALUES ('6', '3557.2', '0.0', '2014-08-10', '工资', '7月份工资');
INSERT INTO `income_cost` VALUES ('7', '0.0', '3.0', '2014-08-11', '吃饭', '早饭');
INSERT INTO `income_cost` VALUES ('8', '0.0', '19.0', '2014-08-12', '吃饭', '早饭和午饭');
INSERT INTO `income_cost` VALUES ('9', '69.3', '0.0', '2014-08-13', '彩票', '彩票');
INSERT INTO `income_cost` VALUES ('10', '100.0', '0.0', '2014-08-13', '话费', '杨鹏给我冲话费');
INSERT INTO `income_cost` VALUES ('11', '0.0', '34.0', '2014-08-13', '吃饭', '午饭');
INSERT INTO `income_cost` VALUES ('12', '0.0', '18.5', '2014-08-14', '吃饭', '早饭午饭');
INSERT INTO `income_cost` VALUES ('13', '0.0', '100.0', '2014-08-15', '充值', '公交卡');
INSERT INTO `income_cost` VALUES ('14', '0.0', '22.0', '2014-08-15', '吃饭', '早饭和午饭');
INSERT INTO `income_cost` VALUES ('15', '0.0', '4.0', '2014-08-16', '吃饭', '早饭');
INSERT INTO `income_cost` VALUES ('16', '0.0', '32.0', '2014-08-16', '吃饭', '晚饭');
INSERT INTO `income_cost` VALUES ('17', '0.0', '23.0', '2014-08-17', '吃饭', '晚饭');
INSERT INTO `income_cost` VALUES ('18', '0.0', '100.0', '2014-08-18', '彩票', '彩票');
INSERT INTO `income_cost` VALUES ('19', '140.0', '0.0', '2014-08-18', '彩票', '彩票');
INSERT INTO `income_cost` VALUES ('20', '0.0', '18.0', '2014-08-18', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('21', '0.0', '100.0', '2014-08-19', '彩票', '彩票');
INSERT INTO `income_cost` VALUES ('22', '0.0', '52.5', '2014-08-19', '吃饭', '早饭和午饭');
INSERT INTO `income_cost` VALUES ('23', '0.0', '30.0', '2014-08-19', '消费', '西瓜。理发');
INSERT INTO `income_cost` VALUES ('24', '0.0', '14.5', '2014-08-20', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('25', '193.9', '0.0', '2014-08-21', '彩票', '彩票');
INSERT INTO `income_cost` VALUES ('26', '0.0', '42.0', '2014-08-21', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('27', '0.0', '13.5', '2014-08-22', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('28', '0.0', '392.0', '2014-08-23', '彩票', '彩票');
INSERT INTO `income_cost` VALUES ('29', '0.0', '1094.3', '2014-08-23', '彩票', '彩票');
INSERT INTO `income_cost` VALUES ('30', '0.0', '17.0', '2014-08-23', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('31', '0.0', '4.0', '2014-08-25', '吃饭', '早饭');
INSERT INTO `income_cost` VALUES ('32', '0.0', '19.0', '2014-08-26', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('33', '0.0', '26.0', '2014-08-27', '吃饭', '午饭、晚饭');
INSERT INTO `income_cost` VALUES ('34', '0.0', '30.5', '2014-08-28', '吃饭', '早饭、午饭');
INSERT INTO `income_cost` VALUES ('35', '0.0', '31.0', '2014-08-29', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('36', '0.0', '105.0', '2014-08-30', '车票', '回家车票');
INSERT INTO `income_cost` VALUES ('37', '0.0', '13.0', '2014-08-31', '吃饭', '午饭');
INSERT INTO `income_cost` VALUES ('38', '0.0', '254.0', '2014-08-31', '购物', '超市刷卡');
INSERT INTO `income_cost` VALUES ('39', '0.0', '13.5', '2014-09-01', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('40', '0.0', '14.0', '2014-09-02', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('41', '0.0', '12.5', '2014-09-03', '吃饭', '早饭、午饭');
INSERT INTO `income_cost` VALUES ('42', '0.0', '30.0', '2014-09-04', '吃饭', '全天');
INSERT INTO `income_cost` VALUES ('43', '0.0', '85.0', '2014-09-04', '电费', '电费');
INSERT INTO `income_cost` VALUES ('44', '0.0', '34.5', '2014-09-05', '吃饭', '全天');
INSERT INTO `income_cost` VALUES ('45', '0.0', '11.5', '2014-09-05', '购物', '买水');
INSERT INTO `income_cost` VALUES ('46', '0.0', '54.0', '2014-09-06', '购物', '牛奶');
INSERT INTO `income_cost` VALUES ('47', '0.0', '121.0', '2014-09-06', '车票', '返回车票');
INSERT INTO `income_cost` VALUES ('48', '0.0', '8.0', '2014-09-08', '购物', '买水');
INSERT INTO `income_cost` VALUES ('49', '3557.0', '0.0', '2014-09-10', '工资', '8月份工资');
INSERT INTO `income_cost` VALUES ('50', '0.0', '29.0', '2014-09-09', '吃饭', '早饭和午饭');
INSERT INTO `income_cost` VALUES ('51', '0.0', '50.0', '2014-09-07', '消费', '话费充值，爸爸');
INSERT INTO `income_cost` VALUES ('52', '0.0', '100.0', '2014-09-09', '消费', '公交卡');
INSERT INTO `income_cost` VALUES ('53', '0.0', '17.5', '2014-09-10', '吃饭', '早饭，午饭');
INSERT INTO `income_cost` VALUES ('54', '0.0', '194.0', '2014-09-11', '购物', '耳机k420');
