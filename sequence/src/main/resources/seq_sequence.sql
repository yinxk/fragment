/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : sequence

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 28/12/2019 20:44:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for seq_sequence
-- ----------------------------
DROP TABLE IF EXISTS `seq_sequence`;
CREATE TABLE `seq_sequence`  (
  `sequence_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `min_value` decimal(36, 0) NOT NULL DEFAULT 1,
  `max_value` decimal(36, 0) NOT NULL,
  `cycle_flag` bit(1) NOT NULL DEFAULT b'0',
  `last_number` decimal(36, 0) NOT NULL DEFAULT 1,
  `segment_size` decimal(10, 0) NOT NULL DEFAULT 100,
  `dynamic_size` decimal(1, 0) NOT NULL DEFAULT 1,
  PRIMARY KEY (`sequence_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seq_sequence
-- ----------------------------
INSERT INTO `seq_sequence` VALUES ('testSequence-1', 1, 99999, b'1', 0, -1, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence0', 1, 99999999999999999999999999999999, b'0', 0, 0, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence1', 1, 99999999999999999999999999999999, b'0', 0, 1, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence10', 1, 99999999999999999999999999999999, b'0', 0, 10, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence100', 1, 99999999999999999999999999999999, b'0', 0, 100, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence1000', 1, 99999999999999999999999999999999, b'0', 0, 1000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence10000', 1, 99999999999999999999999999999999, b'0', 0, 10000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence100000', 1, 99999999999999999999999999999999, b'0', 0, 100000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence2', 1, 99999999999999999999999999999999, b'0', 0, 2, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence20', 1, 99999999999999999999999999999999, b'0', 0, 20, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence200', 1, 99999999999999999999999999999999, b'0', 0, 200, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence2000', 1, 99999999999999999999999999999999, b'0', 0, 2000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence20000', 1, 99999999999999999999999999999999, b'0', 0, 20000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence200000', 1, 99999999999999999999999999999999, b'0', 0, 200000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence3', 1, 99999999999999999999999999999999, b'0', 0, 3, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence30', 1, 99999999999999999999999999999999, b'0', 0, 30, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence300', 1, 99999999999999999999999999999999, b'0', 0, 300, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence3000', 1, 99999999999999999999999999999999, b'0', 0, 3000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence30000', 1, 99999999999999999999999999999999, b'0', 0, 30000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence300000', 1, 99999999999999999999999999999999, b'0', 0, 300000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence4', 1, 99999999999999999999999999999999, b'0', 0, 4, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence40', 1, 99999999999999999999999999999999, b'0', 0, 40, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence400', 1, 99999999999999999999999999999999, b'0', 0, 400, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence4000', 1, 99999999999999999999999999999999, b'0', 0, 4000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence40000', 1, 99999999999999999999999999999999, b'0', 0, 40000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence400000', 1, 99999999999999999999999999999999, b'0', 0, 400000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence5', 1, 99999999999999999999999999999999, b'0', 0, 5, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence50', 1, 99999999999999999999999999999999, b'0', 0, 50, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence500', 1, 99999999999999999999999999999999, b'0', 0, 500, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence5000', 1, 99999999999999999999999999999999, b'0', 0, 5000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence50000', 1, 99999999999999999999999999999999, b'0', 0, 50000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence500000', 1, 99999999999999999999999999999999, b'0', 0, 500000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence6', 1, 99999999999999999999999999999999, b'0', 0, 6, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence60', 1, 99999999999999999999999999999999, b'0', 0, 60, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence600', 1, 99999999999999999999999999999999, b'0', 0, 600, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence6000', 1, 99999999999999999999999999999999, b'0', 0, 6000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence60000', 1, 99999999999999999999999999999999, b'0', 0, 60000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence7', 1, 99999999999999999999999999999999, b'0', 0, 7, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence70', 1, 99999999999999999999999999999999, b'0', 0, 70, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence700', 1, 99999999999999999999999999999999, b'0', 0, 700, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence7000', 1, 99999999999999999999999999999999, b'0', 0, 7000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence70000', 1, 99999999999999999999999999999999, b'0', 0, 70000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence8', 1, 99999999999999999999999999999999, b'0', 0, 8, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence80', 1, 99999999999999999999999999999999, b'0', 0, 80, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence800', 1, 99999999999999999999999999999999, b'0', 0, 800, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence8000', 1, 99999999999999999999999999999999, b'0', 0, 8000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence80000', 1, 99999999999999999999999999999999, b'0', 0, 80000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence9', 1, 99999999999999999999999999999999, b'0', 0, 9, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence90', 1, 99999999999999999999999999999999, b'0', 0, 90, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence900', 1, 99999999999999999999999999999999, b'0', 0, 900, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence9000', 1, 99999999999999999999999999999999, b'0', 0, 9000, 1);
INSERT INTO `seq_sequence` VALUES ('testSequence90000', 1, 99999999999999999999999999999999, b'0', 0, 90000, 1);

SET FOREIGN_KEY_CHECKS = 1;
