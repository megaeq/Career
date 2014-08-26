/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50612
Source Host           : localhost:3306
Source Database       : career

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2014-08-25 23:15:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bill`
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bet_amount` int(4) DEFAULT NULL,
  `sp` float(8,4) DEFAULT NULL,
  `income` float(12,4) DEFAULT NULL,
  `flag` int(1) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `cluster` int(2) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', '100', null, null, null, 'virtual', '2', '2014-08-25 23:10:33');

-- ----------------------------
-- Table structure for `game`
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `home_team_id` int(5) DEFAULT NULL,
  `guest_team_id` int(5) DEFAULT NULL,
  `win_rate` float(4,2) DEFAULT NULL,
  `draw_rate` float(4,2) DEFAULT NULL,
  `lose_rate` float(4,2) DEFAULT NULL,
  `let_the_ball` int(1) DEFAULT NULL,
  `home_score` int(1) DEFAULT NULL,
  `guest_score` int(11) DEFAULT NULL,
  `suggest` varchar(2000) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `team_home_fk` (`home_team_id`),
  KEY `team_guest_fk` (`guest_team_id`),
  CONSTRAINT `team_guest_fk` FOREIGN KEY (`guest_team_id`) REFERENCES `team_info` (`id`),
  CONSTRAINT `team_home_fk` FOREIGN KEY (`home_team_id`) REFERENCES `team_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('1', '1', '2', '2.04', '3.20', '3.12', '0', null, null, '庄家强阻上，所以博三', '2014-08-25 23:13:29');
INSERT INTO `game` VALUES ('2', '3', '4', '3.85', '3.80', '1.67', '0', null, null, '庄家既得利益多', '2014-08-25 23:11:57');

-- ----------------------------
-- Table structure for `game_bill`
-- ----------------------------
DROP TABLE IF EXISTS `game_bill`;
CREATE TABLE `game_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_id` int(11) DEFAULT NULL,
  `bet` int(1) DEFAULT NULL,
  `bill_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bill_fk` (`bill_id`),
  KEY `game_fk` (`game_id`),
  CONSTRAINT `game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  CONSTRAINT `bill_fk` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_bill
-- ----------------------------
INSERT INTO `game_bill` VALUES ('1', '1', '1', '1');
INSERT INTO `game_bill` VALUES ('2', '2', '3', '1');

-- ----------------------------
-- Table structure for `team_info`
-- ----------------------------
DROP TABLE IF EXISTS `team_info`;
CREATE TABLE `team_info` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `eng` varchar(100) DEFAULT NULL,
  `chn` varchar(100) DEFAULT NULL,
  `belong` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_info
-- ----------------------------
INSERT INTO `team_info` VALUES ('1', null, '奥特维达堡', '瑞典超级联赛');
INSERT INTO `team_info` VALUES ('2', null, '奥雷布洛', '瑞典超级联赛');
INSERT INTO `team_info` VALUES ('3', null, '阿基里斯', '荷兰乙级联赛');
INSERT INTO `team_info` VALUES ('4', null, '阿贾克斯青年队', '荷兰乙级联赛');
