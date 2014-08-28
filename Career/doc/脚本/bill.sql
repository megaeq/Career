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
-- Table structure for `game`
-- ----------------------------
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
-- Table structure for `game_bill`
-- ----------------------------
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
-- 2014/08/26 周二 15:14:56.22
alter table game add code varchar(20);
--2014/08/26 周二 21:40:54.67
-- ----------------------------
-- Table structure for `country`
-- ----------------------------
CREATE TABLE `country` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `country` varchar(20) DEFAULT NULL,
  `league_matches` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table team_info add orderno int (2)
--  2014/08/28 周四  17:55:28.67

ALTER TABLE game ADD home_half_score INT (1);

ALTER TABLE game ADD guest_half_score INT (1);

ALTER TABLE game ADD weather VARCHAR (10);

ALTER TABLE game ADD home_team VARCHAR (40);

ALTER TABLE game ADD guest_team VARCHAR (40);
-- 2014/08/28 周四  21:19:07.68
CREATE TABLE `game_complete` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE game ADD game_type VARCHAR (40);




