/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : quartz

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-01-23 14:39:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `file_json` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flag` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
