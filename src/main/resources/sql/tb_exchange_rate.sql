/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : soubaibei

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 11/10/2018 22:41:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_exchange_rate
-- ----------------------------
DROP TABLE IF EXISTS `tb_exchange_rate`;
CREATE TABLE `tb_exchange_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(63) DEFAULT NULL COMMENT '编码',
  `name` varchar(63) DEFAULT NULL COMMENT '名称',
  `rate` float DEFAULT NULL COMMENT '汇率',
  `underlying` varchar(63) DEFAULT NULL COMMENT '标的物',
  `type` varchar(15) DEFAULT NULL COMMENT '类型',
  `status` varchar(15) DEFAULT NULL COMMENT '状态',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modified_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_exchange_rate
-- ----------------------------
BEGIN;
INSERT INTO `tb_exchange_rate` VALUES (1, 'USD', '美元', 1, 'USD', '1', '1', '2018-10-11 22:25:32', '2018-10-11 22:35:54');
INSERT INTO `tb_exchange_rate` VALUES (2, 'CNY', '人民币', 0.1449, 'USD', '1', '1', '2018-10-11 22:26:36', '2018-10-11 22:35:59');
INSERT INTO `tb_exchange_rate` VALUES (3, 'EUR', '欧元', 1.1559, 'USD', '1', '1', '2018-10-11 22:30:06', '2018-10-11 22:36:02');
INSERT INTO `tb_exchange_rate` VALUES (4, 'JPY', '日元', 0.008894, 'USD', '1', '1', '2018-10-11 22:35:26', '2018-10-11 22:36:06');
INSERT INTO `tb_exchange_rate` VALUES (5, 'KRW', '韩币', 0.0008808, 'USD', '1', '1', '2018-10-11 22:35:31', '2018-10-11 22:36:10');
INSERT INTO `tb_exchange_rate` VALUES (6, 'HKD', '港币', 0.1276, 'USD', '1', '1', '2018-10-11 22:35:35', '2018-10-11 22:36:14');
INSERT INTO `tb_exchange_rate` VALUES (7, 'BTC', '比特币', 6324, 'USD', '2', '1', '2018-10-11 22:35:39', '2018-10-11 22:36:18');
INSERT INTO `tb_exchange_rate` VALUES (8, 'ETH', '以太坊', 202, 'USD', '2', '1', '2018-10-11 22:35:42', '2018-10-11 22:36:22');
INSERT INTO `tb_exchange_rate` VALUES (9, 'BCH', '比特现金', 455, 'USD', '2', '1', '2018-10-11 22:35:46', '2018-10-11 22:36:25');
INSERT INTO `tb_exchange_rate` VALUES (10, 'EOS', '柚子', 5.36, 'USD', '2', '1', '2018-10-11 22:35:50', '2018-10-11 22:36:29');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
