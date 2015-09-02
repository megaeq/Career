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
--2014/08/30 周六10:05:24.50
CREATE TABLE `property` (
  `property` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  PRIMARY KEY (`property`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--2014/09/01 周一18:58:05.88
INSERT INTO `property` (`property`, `value`) VALUES ('dirpath', 'D:/tomcatfile/');
INSERT INTO `property` (`property`, `value`) VALUES ('urlpath', 'http://localhost:8080/file/');
 --2014/09/19 周五 8:43:56.28
CREATE TABLE `account` (
  `id` int(4) DEFAULT NULL,
  `balance` float(11,2) DEFAULT '0.00' COMMENT '余额',
  `belong` varchar(40) DEFAULT '' COMMENT '归属机构',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(40) DEFAULT NULL,
  `pwd` blob,
  `isreal` int(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `account_history` (
  `id` int(11) DEFAULT NULL,
  `income` float(11,2) DEFAULT NULL,
  `cost` float(11,2) DEFAULT NULL,
  `accountid` int(4) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `memo` varchar(500) DEFAULT NULL,
  `usages` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--2014/09/22 周一 21:29:59.25
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `intro` varchar(500) DEFAULT NULL COMMENT '简介',
  `userId` int(11) DEFAULT NULL,
  `isdel` tinyint(1) DEFAULT NULL COMMENT '1为是，0为否',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `article_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `userId` int(11) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `articleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `article_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  `articleid` int(11) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `article_tag` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) DEFAULT NULL,
  `tagname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `pwd` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 2014/09/23 周二9:21:31.53
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  `createtime` timestamp NULL DEFAULT NULL,
  `level` int(2) DEFAULT NULL COMMENT '级别，数字越小，级别越高',
  `complete` int(11) DEFAULT '0' COMMENT '完成度，100%',
  `isDel` tinyint(1) DEFAULT '0' COMMENT '1为已删除，0为未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 2014/09/25 周四8:42:56.08
CREATE TABLE `plan_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` timestamp NULL DEFAULT NULL,
  `planid` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT '变更类型',
  `memo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--2014/10/10 周五 17:21:20.66
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `permission` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
2014/10/29 周三  14:05:06.46
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(100) DEFAULT NULL,
  `permission` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--2014/11/03 周一
CREATE TABLE `system_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `system_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
---2014/12/02 周二
CREATE TABLE `football_bifa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `homeTeam` varchar(40) DEFAULT NULL,
  `homeBelong` varchar(40) DEFAULT NULL,
  `homeNo` int(3) DEFAULT NULL,
  `guestTeam` varchar(40) DEFAULT NULL,
  `guestBelong` varchar(40) DEFAULT NULL,
  `guestNo` int(3) DEFAULT NULL,
  `letTheBall` int(2) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `football_bifa_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bifaId` int(11) NOT NULL,
  `type` varchar(40) DEFAULT NULL,
  `buys` int(11) DEFAULT NULL,
  `buyRate` float(9,6) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `saleRate` float(9,6) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `market` int(11) DEFAULT NULL,
  `bifa` float(9,6) DEFAULT NULL,
  `bifaPercent` float(9,6) DEFAULT NULL,
  `averageRate` float(9,6) DEFAULT NULL,
  `averagePercent` float(9,6) DEFAULT NULL,
  `jincaiPercent` float(9,6) DEFAULT NULL,
  `simulate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--2015 05 13
CREATE TABLE `mathdata` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mathmodelid` int(10) DEFAULT NULL,
  `result` float(12,6) DEFAULT NULL,
  `times` int(10) DEFAULT NULL,
  `scoresum` float(16,6) DEFAULT NULL,
  `scoreaverage` float(16,6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mathmodel` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mathmodel` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `explaination` varchar(1000) DEFAULT NULL,
  `expression` varchar(500) DEFAULT NULL,
  `maxid` int(10) ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--20150522 添加hasfinish字段
update `game` set hasfinish=(case when winrate is not null and winrate >0 
and drawrate is not null and drawrate>0 
and loserate is not null and loserate>0
and homescore is not null and homescore>-1 and guestscore>-1 and guestscore is not null then 1 ELSE 0 end);

--20150522 
CREATE TABLE `math_data_ref` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mathdataid` int(10) DEFAULT NULL,
  `gameid` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--201505222329
CREATE TABLE `math_model_ref` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `gameid` int(10) DEFAULT NULL,
  `mathmodelid` int(10) DEFAULT NULL,
  `result` float(12,5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--201509011509 
CREATE TABLE `function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  `description` text,
  `command` varchar(150) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `type` int(6) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `times` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--201509021543
CREATE TABLE `role_permission_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `user_role_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

