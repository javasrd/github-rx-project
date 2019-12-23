/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 127.0.0.1:3306
 Source Schema         : doctor_rx_db

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 23/12/2019 13:53:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `old_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '东华HIS科室ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '东华HIS中科室名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (2, '03', '内科');
INSERT INTO `department` VALUES (3, '15', '内科');

-- ----------------------------
-- Table structure for diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `old_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '东华HIS诊断结果ID',
  `disease` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疾病名称',
  `doctor_id` bigint(20) NULL DEFAULT NULL COMMENT '医生ID（FK ref doctor:id）',
  `patient_id` bigint(20) NULL DEFAULT NULL COMMENT '患者ID（FK ref patient:id）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diagnosis
-- ----------------------------
INSERT INTO `diagnosis` VALUES (2, '001', '慢性胃炎', 2, 2);
INSERT INTO `diagnosis` VALUES (3, '002', '营养不良', 2, 2);
INSERT INTO `diagnosis` VALUES (4, '0', '消化不良', 3, 3);
INSERT INTO `diagnosis` VALUES (5, '0', '重感冒', 3, 3);
INSERT INTO `diagnosis` VALUES (6, '0', '消化不良', 3, 4);
INSERT INTO `diagnosis` VALUES (7, '0', '重感冒', 3, 4);
INSERT INTO `diagnosis` VALUES (8, '0', '消化不良', 3, 5);
INSERT INTO `diagnosis` VALUES (9, '0', '重感冒', 3, 5);

-- ----------------------------
-- Table structure for dict_common
-- ----------------------------
DROP TABLE IF EXISTS `dict_common`;
CREATE TABLE `dict_common`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院名称',
  `type` int(4) NULL DEFAULT NULL COMMENT '数据类型(根据业务进行定义)',
  `comment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_common
-- ----------------------------
INSERT INTO `dict_common` VALUES (1, 'hdpresc', 'http://127.0.0.1:8080/rx-web/prescapi', NULL, '向海典发送处方请求', 1);
INSERT INTO `dict_common` VALUES (2, 'hdsync', 'http://127.0.0.1/rx-back/api/demo', NULL, '向海典发送数据同步请求', 1);

-- ----------------------------
-- Table structure for dict_days
-- ----------------------------
DROP TABLE IF EXISTS `dict_days`;
CREATE TABLE `dict_days`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药疗程编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药疗程名称',
  `value` int(11) NULL DEFAULT NULL COMMENT '给药疗程值（如：1、3、7等）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_days
-- ----------------------------
INSERT INTO `dict_days` VALUES (1, 'yt', '一天', 1);
INSERT INTO `dict_days` VALUES (2, 'lt', '两天', 2);
INSERT INTO `dict_days` VALUES (3, 'st', '三天', 3);
INSERT INTO `dict_days` VALUES (4, 'sit', '四天', 4);
INSERT INTO `dict_days` VALUES (5, 'wt', '五天', 5);
INSERT INTO `dict_days` VALUES (6, 'lt', '六天', 6);
INSERT INTO `dict_days` VALUES (7, 'qt', '七天', 7);
INSERT INTO `dict_days` VALUES (8, 'yz', '一周', 7);
INSERT INTO `dict_days` VALUES (9, 'st', '十天', 10);
INSERT INTO `dict_days` VALUES (10, 'lz', '两周', 14);
INSERT INTO `dict_days` VALUES (11, 'sst', '十四天', 14);
INSERT INTO `dict_days` VALUES (12, NULL, '3天', NULL);

-- ----------------------------
-- Table structure for dict_dose_unit
-- ----------------------------
DROP TABLE IF EXISTS `dict_dose_unit`;
CREATE TABLE `dict_dose_unit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剂量单位编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剂量单位名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_dose_unit
-- ----------------------------
INSERT INTO `dict_dose_unit` VALUES (1, 'k', '克');
INSERT INTO `dict_dose_unit` VALUES (2, 'mg', '毫克');
INSERT INTO `dict_dose_unit` VALUES (3, 'μg', '微克');
INSERT INTO `dict_dose_unit` VALUES (4, 'ng', '纳克');
INSERT INTO `dict_dose_unit` VALUES (5, 'l', '升');
INSERT INTO `dict_dose_unit` VALUES (6, 'ml', '毫升');
INSERT INTO `dict_dose_unit` VALUES (7, 'p', '片');
INSERT INTO `dict_dose_unit` VALUES (8, 'w', '丸');
INSERT INTO `dict_dose_unit` VALUES (9, 'li', '粒');
INSERT INTO `dict_dose_unit` VALUES (10, 'd', '袋');
INSERT INTO `dict_dose_unit` VALUES (11, 'z', '支');
INSERT INTO `dict_dose_unit` VALUES (12, 'p', '瓶');
INSERT INTO `dict_dose_unit` VALUES (13, 'h', '盒');
INSERT INTO `dict_dose_unit` VALUES (14, 'j', '剂');
INSERT INTO `dict_dose_unit` VALUES (15, NULL, 'ml');
INSERT INTO `dict_dose_unit` VALUES (16, NULL, 'g');
INSERT INTO `dict_dose_unit` VALUES (17, NULL, 'mg');

-- ----------------------------
-- Table structure for dict_mode
-- ----------------------------
DROP TABLE IF EXISTS `dict_mode`;
CREATE TABLE `dict_mode`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药方式编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药方式名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_mode
-- ----------------------------
INSERT INTO `dict_mode` VALUES (1, 'kf', '口服');
INSERT INTO `dict_mode` VALUES (2, 'jmzs', '静脉注射');
INSERT INTO `dict_mode` VALUES (3, 'jrzs', '肌肉注射');
INSERT INTO `dict_mode` VALUES (4, 'gjmyy', '关节内用药');
INSERT INTO `dict_mode` VALUES (5, 'pxyy', '皮下用药');
INSERT INTO `dict_mode` VALUES (6, 'pwyy', '喷雾用药');
INSERT INTO `dict_mode` VALUES (7, 'po', '口服');
INSERT INTO `dict_mode` VALUES (8, 'im', '肌肉注射');
INSERT INTO `dict_mode` VALUES (9, 'iv', '静脉注射');
INSERT INTO `dict_mode` VALUES (10, 'ivgtt', '静脉滴注');
INSERT INTO `dict_mode` VALUES (11, 'ad.us.ext.', '外用');
INSERT INTO `dict_mode` VALUES (12, 'wy', '外用');
INSERT INTO `dict_mode` VALUES (13, NULL, '口服/外用');

-- ----------------------------
-- Table structure for dict_times
-- ----------------------------
DROP TABLE IF EXISTS `dict_times`;
CREATE TABLE `dict_times`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药次数编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药次数名称:1次/日，2次/日或qd: 1次/日，bid: 2次/日等',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数值:2/1（2次/天），用药次数/天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_times
-- ----------------------------
INSERT INTO `dict_times` VALUES (1, 'mryc', '每日一次', '1');
INSERT INTO `dict_times` VALUES (2, 'mrlc', '每日两次', '2');
INSERT INTO `dict_times` VALUES (3, 'mrsc', '每日三次', '3');
INSERT INTO `dict_times` VALUES (4, 'gryc', '隔日一次', '1/2');
INSERT INTO `dict_times` VALUES (5, 'mzyc', '每周一次', '1/7');
INSERT INTO `dict_times` VALUES (12, 'qd', '每日一次', '1');
INSERT INTO `dict_times` VALUES (13, 'bid', '每日两次', '2');
INSERT INTO `dict_times` VALUES (14, 'tid', '每日三次', '3');
INSERT INTO `dict_times` VALUES (15, 'qid', '每日四次', '4');
INSERT INTO `dict_times` VALUES (16, 'qh', '每小时一次', '24');
INSERT INTO `dict_times` VALUES (17, 'q2h', '每两小时一次', '12');
INSERT INTO `dict_times` VALUES (18, 'q4h', '每四小时一次', '6');
INSERT INTO `dict_times` VALUES (19, 'q6h', '每六小时一次', '4');
INSERT INTO `dict_times` VALUES (20, 'qn', '每晚一次', '1');
INSERT INTO `dict_times` VALUES (21, 'qod', '隔日一次', '1/2');
INSERT INTO `dict_times` VALUES (22, 'biw', '每周两次', '2/7');
INSERT INTO `dict_times` VALUES (23, 'hs', '临睡前', '1');
INSERT INTO `dict_times` VALUES (24, 'am', '上午', '1');
INSERT INTO `dict_times` VALUES (25, 'pm', '下午', '1');
INSERT INTO `dict_times` VALUES (26, 'st', '立即', '1');
INSERT INTO `dict_times` VALUES (27, 'dc', '停止', '1');
INSERT INTO `dict_times` VALUES (28, 'prn', '需要时（长期）', '1');
INSERT INTO `dict_times` VALUES (29, 'sos', '需要时（限用一次，12小时内有效）', '1');
INSERT INTO `dict_times` VALUES (30, 'ac', '饭前', '3');
INSERT INTO `dict_times` VALUES (31, 'pc', '饭后', '3');
INSERT INTO `dict_times` VALUES (32, '12n', '中午12点', '1');
INSERT INTO `dict_times` VALUES (33, '12mn', '午夜12点', '1');
INSERT INTO `dict_times` VALUES (34, NULL, '2次/天', NULL);

-- ----------------------------
-- Table structure for direction
-- ----------------------------
DROP TABLE IF EXISTS `direction`;
CREATE TABLE `direction`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `presc_drug_id` bigint(20) NULL DEFAULT NULL COMMENT '处方药品ID(FK ref presc_drug:id)',
  `mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药方式（口服，外敷等）',
  `times` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '给药次数',
  `dosage` decimal(12, 4) NULL DEFAULT NULL COMMENT '剂量',
  `doseunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剂量单位',
  `days` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 210 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of direction
-- ----------------------------
INSERT INTO `direction` VALUES (1, 1, '外用', '一日三次', 3.0000, 'mg', '4');
INSERT INTO `direction` VALUES (2, 2, '外用', '一日三次', 4.0000, '粒', '5');
INSERT INTO `direction` VALUES (3, 3, '外用', '一日三次', 3.0000, '粒', '5');
INSERT INTO `direction` VALUES (4, 4, '外用', '一日三次', 3.0000, '粒', '5');
INSERT INTO `direction` VALUES (5, 5, '外用', '一日一次', 3.0000, '粒', '5');
INSERT INTO `direction` VALUES (6, 6, '外用', '一日三次', 4.0000, 'mg', '7');
INSERT INTO `direction` VALUES (7, 7, '外用', '一日一次', 3.0000, '粒', '5');
INSERT INTO `direction` VALUES (8, 8, '口服', '一日三次', 4.0000, 'mg', '6');
INSERT INTO `direction` VALUES (9, 9, '口服', '一日一次', 1.0000, '粒', '1');
INSERT INTO `direction` VALUES (10, 10, '外用', '一日三次', 2.0000, 'mg', '2');
INSERT INTO `direction` VALUES (11, 11, '外用', '一日一次', 3.0000, 'mg', '3');
INSERT INTO `direction` VALUES (12, 12, '外用', '一日一次', 5.0000, '粒', '7');
INSERT INTO `direction` VALUES (13, 13, '外用', '一日一次', 4.0000, '粒', '5');
INSERT INTO `direction` VALUES (14, 14, '', '', NULL, '', '0');
INSERT INTO `direction` VALUES (15, 15, '', '', NULL, '', '0');
INSERT INTO `direction` VALUES (16, 16, '', '', 5.0000, 'mg', '6');
INSERT INTO `direction` VALUES (17, 17, '外用', '一日一次', 4.0000, '粒', '5');
INSERT INTO `direction` VALUES (18, 18, '', '', NULL, '', '0');
INSERT INTO `direction` VALUES (19, 19, '', '', 5.0000, 'mg', '6');
INSERT INTO `direction` VALUES (20, 20, '外用', '一日一次', 4.0000, '粒', '5');
INSERT INTO `direction` VALUES (21, 21, '口服', '一日三次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (22, 22, '外用', '一日一次', 3.0000, '粒', '0');
INSERT INTO `direction` VALUES (23, 23, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (24, 24, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (25, 25, '外用', '一日一次', 5.0000, '粒', '7');
INSERT INTO `direction` VALUES (26, 26, '外用', '一日一次', 3.0000, '粒', '5');
INSERT INTO `direction` VALUES (27, 27, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (28, 28, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (29, 29, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (30, 30, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (31, 31, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (32, 32, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (33, 33, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (34, 34, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (35, 35, '外用', '一日一次', 5.0000, '6', '7');
INSERT INTO `direction` VALUES (36, 36, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (37, 37, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (38, 38, '口服', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (39, 39, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (40, 40, '外用', '一日一次', 6.0000, 'mg', '7');
INSERT INTO `direction` VALUES (41, 41, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (42, 42, '外用', '一日一次', 6.0000, 'mg', '7');
INSERT INTO `direction` VALUES (43, 43, '外用', '一日一次', 6.0000, '粒', '1');
INSERT INTO `direction` VALUES (44, 44, '外用', '一日一次', 6.0000, '粒', '1');
INSERT INTO `direction` VALUES (45, 45, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (46, 46, '外用', '一日一次', 6.0000, '粒', '7');
INSERT INTO `direction` VALUES (47, 47, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (48, 48, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (49, 49, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (50, 50, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (51, 51, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (52, 52, 'yy', '一日一次', 6.0000, '粒', '5');
INSERT INTO `direction` VALUES (53, 53, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (54, 54, '外用', '一日一次', 6.0000, '粒', '6');
INSERT INTO `direction` VALUES (55, 55, '外用', '一日一次', 5.0000, '粒', '7');
INSERT INTO `direction` VALUES (56, 56, '外用', '一日一次', 6.0000, '粒', '5');
INSERT INTO `direction` VALUES (57, 57, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (58, 58, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (59, 59, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (60, 60, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (61, 61, '外用', '一日一次', 6.0000, '粒', '5');
INSERT INTO `direction` VALUES (62, 62, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (63, 63, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (64, 64, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (65, 65, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (66, 66, '外用', '一日一次', 6.0000, '粒', '5');
INSERT INTO `direction` VALUES (67, 67, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (68, 68, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (69, 69, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (70, 70, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (71, 71, '外用', '一日一次', 6.0000, '粒', '5');
INSERT INTO `direction` VALUES (72, 72, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (73, 73, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (74, 74, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (75, 75, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (76, 76, '外用', '一日一次', 2.0000, '粒', '5');
INSERT INTO `direction` VALUES (77, 77, '外用', '一日一次', 2.0000, '粒', '5');
INSERT INTO `direction` VALUES (78, 78, '外用', '一日三次', 1.0000, 'mg', '6');
INSERT INTO `direction` VALUES (79, 79, '口服', '一日一次', 4.0000, '粒', '6');
INSERT INTO `direction` VALUES (80, 80, '口服', '一日一次', 4.0000, '粒', '6');
INSERT INTO `direction` VALUES (81, 81, '口服', '一日一次', 4.0000, '粒', '6');
INSERT INTO `direction` VALUES (82, 82, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (83, 83, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (84, 84, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (85, 85, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (86, 86, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (87, 87, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (88, 88, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (89, 89, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (90, 90, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (91, 91, '外用', '一日一次', 5.0000, '粒', '8');
INSERT INTO `direction` VALUES (92, 92, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (93, 93, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (94, 94, '外用', '一日一次', 6.0000, 'mg', '7');
INSERT INTO `direction` VALUES (95, 95, '口服', '一日一次', 7.0000, '粒', '8');
INSERT INTO `direction` VALUES (96, 96, 'y', '一日一次', 8.0000, 'mg', '9');
INSERT INTO `direction` VALUES (97, 97, '外用', '一日一次', 3.0000, '粒', '3');
INSERT INTO `direction` VALUES (98, 98, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (99, 99, '外用', '一日一次', 6.0000, 'mg', '7');
INSERT INTO `direction` VALUES (100, 100, '口服', '一日一次', 7.0000, '粒', '8');
INSERT INTO `direction` VALUES (101, 101, 'y', '一日一次', 8.0000, 'mg', '9');
INSERT INTO `direction` VALUES (102, 102, '外用', '一日一次', 3.0000, '粒', '3');
INSERT INTO `direction` VALUES (103, 103, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (104, 104, '外用', '一日一次', 6.0000, 'mg', '7');
INSERT INTO `direction` VALUES (105, 105, '口服', '一日一次', 7.0000, '粒', '8');
INSERT INTO `direction` VALUES (106, 106, 'y', '一日一次', 8.0000, 'mg', '9');
INSERT INTO `direction` VALUES (107, 107, '外用', '一日一次', 3.0000, '粒', '3');
INSERT INTO `direction` VALUES (108, 108, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (109, 109, '外用', '一日一次', 5.0000, '粒', '3');
INSERT INTO `direction` VALUES (110, 110, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (111, 111, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (112, 112, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (113, 113, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (114, 114, '外用', '一日一次', NULL, '', '0');
INSERT INTO `direction` VALUES (115, 115, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (116, 116, '外用', '一日一次', 6.0000, '粒', '6');
INSERT INTO `direction` VALUES (117, 117, '外用', '一日一次', 2.5000, 'mg', '5');
INSERT INTO `direction` VALUES (118, 118, '外用', '一日一次', 2.5000, '粒', '3');
INSERT INTO `direction` VALUES (119, 119, '外用', '一日一次', 2.5000, 'mg', '5');
INSERT INTO `direction` VALUES (120, 120, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (121, 121, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (122, 122, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (123, 123, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (124, 124, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (125, 125, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (126, 126, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (127, 127, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (128, 128, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (129, 129, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (130, 130, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (131, 131, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (132, 132, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (133, 133, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (134, 134, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (135, 135, '外用', '一日一次', 6.0000, '粒', '5');
INSERT INTO `direction` VALUES (136, 136, '外用', '一日一次', 6.0000, '粒', '6');
INSERT INTO `direction` VALUES (137, 137, '外用', '一日一次', 6.0000, '粒', '6');
INSERT INTO `direction` VALUES (138, 138, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (139, 139, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (140, 140, '外用', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (141, 141, '外用', '一日一次', 6.0000, 'mg', '8');
INSERT INTO `direction` VALUES (142, 142, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (143, 143, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (144, 144, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (145, 145, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (146, 146, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (147, 147, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (148, 148, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (149, 149, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (150, 150, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (151, 151, '5', '一日一次', 5.0000, '粒', '6');
INSERT INTO `direction` VALUES (152, 152, '1', '1', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (153, 153, '1', '1', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (154, 154, '1', '1', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (155, 155, 'g', 'g', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (156, 156, '1', '1', 1.0000, '粒', '1');
INSERT INTO `direction` VALUES (157, 157, '1', '1', 11.0000, '11', '11');
INSERT INTO `direction` VALUES (158, 158, '外用', '一日一次', 11.0000, '11', '45');
INSERT INTO `direction` VALUES (159, 159, '口服', '一日一次', NULL, 'mg', '0');
INSERT INTO `direction` VALUES (160, 160, '1', '1', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (161, 161, '1', '1', 11.0000, 'mg', '5');
INSERT INTO `direction` VALUES (162, 162, '1', '1', NULL, '', '0');
INSERT INTO `direction` VALUES (163, 163, '1', '1', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (164, 164, '1', '1', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (165, 165, '口服', '一日一次', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (166, 166, '11', '1', 1.0000, '粒', '1');
INSERT INTO `direction` VALUES (167, 167, '11', '1', 1.0000, '1', '5');
INSERT INTO `direction` VALUES (168, 168, '11', '一日一次', 1.0000, '1', '1');
INSERT INTO `direction` VALUES (169, 169, '口服', '一日一次', 1.0000, '粒', '1');
INSERT INTO `direction` VALUES (170, 170, '外用', '一日一次', 5.0000, '粒', '5');
INSERT INTO `direction` VALUES (171, 171, '口服', '一日一次', 2.5000, '粒', '3');
INSERT INTO `direction` VALUES (172, 172, '口服', '一日三次', NULL, '粒', '1天');
INSERT INTO `direction` VALUES (173, 173, '口服', '一日一次', 2.5000, '粒', '1天');
INSERT INTO `direction` VALUES (174, 174, '外用', '一日一次', 3.4000, 'mg', '1天');
INSERT INTO `direction` VALUES (175, 175, '口服', '一日一次', 4.0000, '粒', '1天');
INSERT INTO `direction` VALUES (176, 176, '外用', '一日一次', 2.5000, 'mg', '1天');
INSERT INTO `direction` VALUES (177, 177, '外用', '一日一次', 1.0000, '粒', '1天');
INSERT INTO `direction` VALUES (178, 178, '外用', '一日一次', 2.0000, 'mg', '1天');
INSERT INTO `direction` VALUES (179, 179, '外用', '一日一次', 1.0000, '粒', '1天');
INSERT INTO `direction` VALUES (180, 180, '外用', '一日一次', 2.0000, '粒', '1天');
INSERT INTO `direction` VALUES (181, 181, '外用', '一日一次', 1.0000, '粒', '1天');
INSERT INTO `direction` VALUES (182, 182, '外用', '一日一次', 1.0000, '粒', '1天');
INSERT INTO `direction` VALUES (183, 183, '口服', '一日一次', NULL, '粒', '1天');
INSERT INTO `direction` VALUES (184, 184, '外用', '一日一次', 1.2000, '粒', '1天');
INSERT INTO `direction` VALUES (185, 185, '口服', '一日一次', 1.0000, '粒', '1天');
INSERT INTO `direction` VALUES (186, 186, '口服', '一日一次', 1.0000, '粒', '1天');
INSERT INTO `direction` VALUES (187, 187, '外用', '一日一次', 1.0000, '粒', '1天');
INSERT INTO `direction` VALUES (188, 188, '口服', '一日一次', 2.5000, 'mg', '3天');
INSERT INTO `direction` VALUES (189, 189, '外用', '一日一次', 2.4000, '粒', '3天');
INSERT INTO `direction` VALUES (190, 190, '外用', '一日一次', 2.5000, '粒', '1天');
INSERT INTO `direction` VALUES (191, 191, '1', '1', 1.0000, '1', '4天');
INSERT INTO `direction` VALUES (192, 192, '口服', '一日一次', 1.0000, '克', '一天');
INSERT INTO `direction` VALUES (193, 193, '静脉注射', '一日两次', 2.0000, '毫克', '两天');
INSERT INTO `direction` VALUES (194, 194, '肌肉注射', '一日三次', 3.0000, '微克', '三天');
INSERT INTO `direction` VALUES (195, 195, '静脉注射', '一日两次', 1.0000, '毫克', '两天');
INSERT INTO `direction` VALUES (196, 196, '静脉注射', '一日一次', 1.0000, '克', '一天');
INSERT INTO `direction` VALUES (197, 197, '关节内用药', '一日一次', 1.0000, '毫克', '一天');
INSERT INTO `direction` VALUES (198, 198, '口服/外用', '2次/天', 1.0000, 'ml', '3天');
INSERT INTO `direction` VALUES (199, 199, '口服/外用', '2次/天', 9.0000, 'g', '3天');
INSERT INTO `direction` VALUES (200, 200, '口服/外用', '2次/天', 10.0000, 'mg', '3天');
INSERT INTO `direction` VALUES (201, 201, '口服/外用', '2次/天', 1.0000, 'ml', '3天');
INSERT INTO `direction` VALUES (202, 202, '口服/外用', '2次/天', 1.0000, 'ml', '3天');
INSERT INTO `direction` VALUES (203, 203, '口服/外用', '2次/天', 9.0000, 'g', '3天');
INSERT INTO `direction` VALUES (204, 204, '口服/外用', '2次/天', 1.0000, 'ml', '3天');
INSERT INTO `direction` VALUES (205, 205, '口服/外用', '2次/天', 1.0000, 'ml', '3天');
INSERT INTO `direction` VALUES (206, 206, '口服/外用', '2次/天', 1.0000, 'ml', '3天');
INSERT INTO `direction` VALUES (207, 207, '口服/外用', '2次/天', 10.0000, 'g', '3天');
INSERT INTO `direction` VALUES (208, 208, '口服/外用', '2次/天', 1.0000, '克', '3天');
INSERT INTO `direction` VALUES (209, 209, '口服/外用', '每日两次', 10.0000, 'g', '3天');

-- ----------------------------
-- Table structure for dispensary
-- ----------------------------
DROP TABLE IF EXISTS `dispensary`;
CREATE TABLE `dispensary`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '药房编码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药房名称',
  `comment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dispensary
-- ----------------------------
INSERT INTO `dispensary` VALUES (1, 'rd', '药房二', '睿道医药', 1);

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `old_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自东华传送过来的医生ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生姓名',
  `department_id` bigint(20) NULL DEFAULT NULL COMMENT '医生所在科室ID（FK ref depatment:id）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES (2, '02', '李四', 2);
INSERT INTO `doctor` VALUES (3, '12', '李四', 2);

-- ----------------------------
-- Table structure for doctor_patient
-- ----------------------------
DROP TABLE IF EXISTS `doctor_patient`;
CREATE TABLE `doctor_patient`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `doctor_id` bigint(20) NULL DEFAULT NULL COMMENT '医生ID（FK ref doctor:id）',
  `patient_id` bigint(20) NULL DEFAULT NULL COMMENT '患者ID（FK ref patient:id）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doctor_patient
-- ----------------------------
INSERT INTO `doctor_patient` VALUES (2, 2, 2);
INSERT INTO `doctor_patient` VALUES (3, 3, 3);
INSERT INTO `doctor_patient` VALUES (4, 3, 4);
INSERT INTO `doctor_patient` VALUES (5, 3, 5);

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `wareid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品ID(来自海典ERP)',
  `barcode` varchar(140) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条形码',
  `abc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '助记码',
  `warename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `waresimname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通用名',
  `warespec` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `prod_addr` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产地',
  `producer` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产厂家',
  `wareunit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '在售/停售状态  1：在售； 2：停售',
  `saleprice` decimal(12, 2) NULL DEFAULT NULL COMMENT '售价',
  `inventory` int(4) NULL DEFAULT NULL COMMENT '当天药品库存',
  `jl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '剂量',
  `lc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疗程',
  `pc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频次',
  `yfyl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用法用量',
  `xuanx` decimal(12, 2) NULL DEFAULT NULL COMMENT '选项(最小可售包装的单价)',
  `saleminspec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小可出售包装规格',
  `saleminunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小可出售包装剂量单位',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IX_drug_abc`(`status`, `abc`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES (1200, '000249', '', 'CS3', '测试3', '测试3', '10片', NULL, '国际药业', '盒', 1, 11.00, 0, '', '3天', '2次/天', '口服/外用', 11.00, '10片', '盒');
INSERT INTO `drug` VALUES (1875, '000004', '', 'YDPAP', '吲达帕胺片', '吲达帕胺片', '2.5mg*20片', NULL, '石家庄市华新药业有限责任公司', '盒', 1, 7.00, 185, '2.5mg', '3天', '2次/天', '口服/外用', 7.00, '2.5mg*20片', '盒');
INSERT INTO `drug` VALUES (1876, '000006', '', 'MTSS', '蒙脱石散', '蒙脱石散', '3g*10袋', NULL, '湖南千金湘江药业股份有限公司', '盒', 1, 5.50, 0, '1片', '3天', '2次/天', '口服/外用', 5.50, '3g*10袋', '盒');
INSERT INTO `drug` VALUES (1877, '000007', '', 'XEYBKL', '小儿咽扁颗粒', '小儿咽扁颗粒', '4g*10袋', NULL, '贵州神奇药业有限公司', '盒', 1, 8.50, 0, '4g', '3天', '2次/天', '口服/外用', 8.50, '4g*10袋', '盒');
INSERT INTO `drug` VALUES (1878, '000008', '', 'BHW', '保和丸', '保和丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 5.80, 0, '9g', '3天', '2次/天', '口服/外用', 5.80, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1879, '000009', '', 'TWBXW', '天王补心丸', '天王补心丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 9.00, 0, '9g', '3天', '2次/天', '口服/外用', 9.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1880, '000010', '', 'GPW', '归脾丸', '归脾丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 6.00, 0, '9g', '3天', '2次/天', '口服/外用', 6.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1881, '000011', '', 'FZLZW', '附子理中丸', '附子理中丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 7.50, 0, '9g', '3天', '2次/天', '口服/外用', 7.50, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1882, '000012', '', 'XEYBKL', '小儿咽扁颗粒', '小儿咽扁颗粒', '8g*10袋', NULL, '贵州神奇药业有限公司', '盒', 1, 12.00, 0, '8g', '3天', '2次/天', '口服/外用', 12.00, '8g*10袋', '盒');
INSERT INTO `drug` VALUES (1883, '000013', '', 'XBDPP', '硝苯地平片', '硝苯地平片', '10mg*100片', NULL, '山西云鹏制药有限公司', '瓶', 1, 2.00, 0, '10mg', '3天', '2次/天', '口服/外用', 2.00, '10mg*100片', '瓶');
INSERT INTO `drug` VALUES (1884, '000014', '', 'LWDHW', '六味地黄丸', '六味地黄丸', '120丸', NULL, '北京同仁堂科技发展股份有限公司制药厂', '瓶', 1, 10.80, 0, '1丸', '3天', '2次/天', '口服/外用', 10.80, '120丸', '瓶');
INSERT INTO `drug` VALUES (1885, '000015', '', 'FFLBMP', '复方罗布麻片', '复方罗布麻片', '100片', NULL, '山西云鹏制药', '瓶', 1, 3.50, 0, '1片', '3天', '2次/天', '口服/外用', 3.50, '100片', '瓶');
INSERT INTO `drug` VALUES (1886, '000016', '', 'XFZYW', '血府逐瘀丸', '血府逐瘀丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 7.00, 0, '9g', '3天', '2次/天', '口服/外用', 7.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1887, '000017', '', 'LWDHW', '六味地黄丸', '六味地黄丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 5.00, 0, '9g', '3天', '2次/天', '口服/外用', 5.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1888, '000018', '', 'LBWLKL', '利巴韦林颗粒', '利巴韦林颗粒', '50mg*36袋', NULL, '湖南千金湘江药业', '盒', 1, 6.00, 0, '50mg', '3天', '2次/天', '口服/外用', 6.00, '50mg*36袋', '盒');
INSERT INTO `drug` VALUES (1889, '000019', '', 'XYW', '逍遥丸', '逍遥丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 6.80, 0, '9g', '3天', '2次/天', '口服/外用', 6.80, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1890, '000020', '', 'YQJDW', '银翘解毒丸', '银翘解毒丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 6.50, 0, '9g', '3天', '2次/天', '口服/外用', 6.50, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1891, '000021', '', 'BZYXW', '柏子养心丸', '柏子养心丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 7.00, 0, '9g', '3天', '2次/天', '口服/外用', 7.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1892, '000022', '', 'JHW', '橘红丸', '橘红丸', '6g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 6.00, 0, '6g', '3天', '2次/天', '口服/外用', 6.00, '6g*10丸', '盒');
INSERT INTO `drug` VALUES (1893, '000023', '', 'BZYMW', '八珍益母丸', '八珍益母丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 6.50, 0, '9g', '3天', '2次/天', '口服/外用', 6.50, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1894, '000024', '', 'BZYQW', '补中益气丸', '补中益气丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 5.00, 0, '9g', '3天', '2次/天', '口服/外用', 5.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1895, '000025', '', 'WJBFW', '乌鸡白凤丸', '乌鸡白凤丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 6.00, 0, '9g', '3天', '2次/天', '口服/外用', 6.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1896, '000026', '', 'JKSQW', '金匮肾气丸', '金匮肾气丸', '6g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 6.20, 0, '6g', '3天', '2次/天', '口服/外用', 6.20, '6g*10丸', '盒');
INSERT INTO `drug` VALUES (1897, '000027', '', 'HJW', '槐角丸', '槐角丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 5.00, 0, '9g', '3天', '2次/天', '口服/外用', 5.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1898, '000028', '', 'JYBDP', '甲氧苄啶片', '甲氧苄啶片', '0.1g*100片', NULL, '南京白敬宇制药', '瓶', 1, 4.00, 0, '0.1g', '3天', '2次/天', '口服/外用', 4.00, '0.1g*100片', '瓶');
INSERT INTO `drug` VALUES (1899, '000029', '', 'HTWDBM', '含糖胃蛋白酶', '含糖胃蛋白酶', '2g*12袋', NULL, '山西千汇药业有限公司', '盒', 1, 3.70, 0, '2g', '3天', '2次/天', '口服/外用', 3.70, '2g*12袋', '盒');
INSERT INTO `drug` VALUES (1900, '000030', '', 'JEYXY', '洁尔阴洗液', '洁尔阴洗液', '180ml', NULL, '四川恩威制药有限公司', '瓶', 1, 13.00, 90, '10ml', '3天', '2次/天', '口服/外用', 13.00, '180ml', '瓶');
INSERT INTO `drug` VALUES (1901, '000031', '', 'AMLZCRJN', '奥美拉唑肠溶胶囊', '奥美拉唑肠溶胶囊', '20mg', NULL, '成都天台山制药有限公司', '瓶', 1, 2.50, 402, '1ml', '3天', '2次/天', '口服/外用', 2.50, '20mg', '瓶');
INSERT INTO `drug` VALUES (1902, '000032', '', 'SHXJ', '珊瑚癣净', '珊瑚癣净', '250ml', NULL, '贵州金桥药业', '瓶', 1, 17.00, 0, '2.5ml', '3天', '2次/天', '口服/外用', 17.00, '250ml', '瓶');
INSERT INTO `drug` VALUES (1903, '000033', '', 'PPZKKL', '枇杷止咳颗粒', '枇杷止咳颗粒', '3g*9袋', NULL, '贵州神奇制药', '盒', 1, 8.00, 0, '3g', '3天', '2次/天', '口服/外用', 8.00, '3g*9袋', '盒');
INSERT INTO `drug` VALUES (1904, '000034', '', 'TBABKL', '头孢氨苄颗粒', '头孢氨苄颗粒', '0.125g*12袋', NULL, '华北制药河北华民药业', '盒', 1, 2.50, 240, '0.125g', '3天', '2次/天', '口服/外用', 2.50, '0.125g*12袋', '盒');
INSERT INTO `drug` VALUES (1905, '000035', '', 'LYQFKL', '羚羊清肺颗粒', '羚羊清肺颗粒', '6g*9袋', NULL, '江西保利制药', '盒', 1, 12.00, 60, '6g', '3天', '2次/天', '口服/外用', 12.00, '6g*9袋', '盒');
INSERT INTO `drug` VALUES (1906, '000036', '', 'LWDHJN', '六味地黄胶囊', '六味地黄胶囊', '0.3g*60粒', NULL, '吉林辉南长龙生化药业', '盒', 1, 18.00, 200, '0.3g', '3天', '2次/天', '口服/外用', 18.00, '0.3g*60粒', '盒');
INSERT INTO `drug` VALUES (1907, '000037', '', 'YHKL', '银黄颗粒', '银黄颗粒', '4g*10袋', NULL, '河北国金药业', '盒', 1, 4.00, 200, '4g', '3天', '2次/天', '口服/外用', 4.00, '4g*10袋', '盒');
INSERT INTO `drug` VALUES (1908, '000038', '', 'JHKL', '橘红颗粒', '橘红颗粒', '11g*14袋', NULL, '广西恒拓集团仁盛制药', '盒', 1, 12.00, 69, '11g', '3天', '2次/天', '口服/外用', 12.00, '11g*14袋', '盒');
INSERT INTO `drug` VALUES (1909, '000039', '', 'FFAFNMKL', '复方氨酚那敏颗粒', '复方氨酚那敏颗粒', '5g*50袋', NULL, '河北国金药业', '包', 1, 6.00, 0, '5g', '3天', '2次/天', '口服/外用', 6.00, '5g*50袋', '包');
INSERT INTO `drug` VALUES (1910, '000040', '', 'DPLTP', '多潘立酮片', '多潘立酮片', '10mg*30片', NULL, '西安杨森制药', '盒', 1, 16.00, 100, '10mg', '3天', '2次/天', '口服/外用', 16.00, '10mg*30片', '盒');
INSERT INTO `drug` VALUES (1911, '000041', '', 'ASPLCRP', '阿司匹林肠溶片', '阿司匹林肠溶片', '25mg*100片', NULL, '石家庄欧意药业', '瓶', 1, 2.50, 264, '25mg', '3天', '2次/天', '口服/外用', 2.50, '25mg*100片', '瓶');
INSERT INTO `drug` VALUES (1912, '000042', '', 'EJBXKFY', '阿胶补血口服液', '阿胶补血口服液', '10ml*10支', NULL, '苏州华葆药业', '盒', 1, 28.00, 0, '10ml', '3天', '2次/天', '口服/外用', 28.00, '10ml*10支', '盒');
INSERT INTO `drug` VALUES (1913, '000043', '', 'PPZKJN', '枇杷止咳胶囊', '枇杷止咳胶囊', '0.25g*24粒', NULL, '贵州神奇药业', '盒', 1, 12.00, 0, '0.25g', '3天', '2次/天', '口服/外用', 12.00, '0.25g*24粒', '盒');
INSERT INTO `drug` VALUES (1914, '000044', '', 'TLQTG', '通络祛痛膏', '通络祛痛膏', '7cm×10cm*5贴', NULL, '河南羚锐制药', '盒', 1, 1.50, 0, '1贴', '3天', '2次/天', '口服/外用', 1.50, '7cm×10cm*5贴', '盒');
INSERT INTO `drug` VALUES (1915, '000045', '', 'HMSCRP', '红霉素肠溶片', '红霉素肠溶片', '0.125g*100片', NULL, '石家庄华新药业', '瓶', 1, 10.00, 0, '0.125g', '3天', '2次/天', '口服/外用', 10.00, '0.125g*100片', '瓶');
INSERT INTO `drug` VALUES (1916, '000046', '', 'NHJDP', '牛黄解毒片', '牛黄解毒片', '0.27*120片', NULL, '北京同仁堂制药厂', '盒', 1, 15.00, 50, '0.27mg', '3天', '2次/天', '口服/外用', 15.00, '0.27*120片', '盒');
INSERT INTO `drug` VALUES (1917, '000047', '', 'AQMSKL', '阿奇霉素颗粒', '阿奇霉素颗粒', '0.1g*6袋', NULL, '山西千汇药业', '盒', 1, 5.00, 0, '0.1g', '3天', '2次/天', '口服/外用', 5.00, '0.1g*6袋', '盒');
INSERT INTO `drug` VALUES (1918, '000048', '', 'WUDQLMP', '维U颠茄铝镁片Ⅱ', '维U颠茄铝镁片Ⅱ', '48片', NULL, '山西云鹏制药', '瓶', 1, 2.00, 0, '1片', '3天', '2次/天', '口服/外用', 2.00, '48片', '瓶');
INSERT INTO `drug` VALUES (1919, '000049', '', 'GLQTP', '格列齐特片', '格列齐特片', '80mg*60片', NULL, '亚宝药业', '盒', 1, 7.50, 0, '80mg', '3天', '2次/天', '口服/外用', 7.50, '80mg*60片', '盒');
INSERT INTO `drug` VALUES (1920, '000050', '', 'RGLNP', '瑞格列奈片', '瑞格列奈片', '1.0mg*15片', NULL, '北京万生药业', '盒', 1, 20.00, 0, '1.0mg', '3天', '2次/天', '口服/外用', 20.00, '1.0mg*15片', '盒');
INSERT INTO `drug` VALUES (1921, '000051', '', 'YXLXMSP', '乙酰螺旋霉素片', '乙酰螺旋霉素片', '0.1g*12片*50板', NULL, '石家庄华新药业', '盒', 1, 25.00, 0, '0.1g', '3天', '2次/天', '口服/外用', 25.00, '0.1g*12片*50板', '盒');
INSERT INTO `drug` VALUES (1922, '000052', '', 'TBABJN', '头孢氨苄胶囊', '头孢氨苄胶囊', '0.125*10粒*5板', NULL, '石家庄华新药业', '盒', 1, 4.00, 0, '0.125g', '3天', '2次/天', '口服/外用', 4.00, '0.125*10粒*5板', '盒');
INSERT INTO `drug` VALUES (1923, '000053', '', 'XBDPHSP', '硝苯地平缓释片（Ⅱ）', '硝苯地平缓释片（Ⅱ）', '20mg*30片', NULL, '河北医科大学制药厂', '盒', 1, 14.00, 0, '20mg', '3天', '2次/天', '口服/外用', 14.00, '20mg*30片', '盒');
INSERT INTO `drug` VALUES (1924, '000054', '', 'ZBDHW', '知柏地黄丸', '知柏地黄丸', '9克*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 5.00, 0, '9克', '3天', '2次/天', '口服/外用', 5.00, '9克*10丸', '盒');
INSERT INTO `drug` VALUES (1925, '000055', '', 'FGGTP', '附桂骨痛片', '附桂骨痛片', '0.33g*48片', NULL, '安国市天下康制药', '盒', 1, 28.00, 0, '0.33g', '3天', '2次/天', '口服/外用', 28.00, '0.33g*48片', '盒');
INSERT INTO `drug` VALUES (1926, '000056', '', 'BLGKL', '板蓝根颗粒', '板蓝根颗粒', '10g*11代', NULL, '广西禅方药业有限公司', '盒', 1, 3.50, 0, '10g', '3天', '2次/天', '口服/外用', 3.50, '10g*11代', '盒');
INSERT INTO `drug` VALUES (1927, '000057', '', 'BLGKL', '板蓝根颗粒', '板蓝根颗粒', '5g*11代', NULL, '广西禅方药业有限公司', '盒', 1, 4.00, 0, '5g', '3天', '2次/天', '口服/外用', 4.00, '5g*11代', '盒');
INSERT INTO `drug` VALUES (1928, '000058', '', 'YMCKL', '益母草颗粒', '益母草颗粒', '15g*11袋', NULL, '广西禅方药业有限公司', '盒', 1, 6.00, 0, '15g', '3天', '2次/天', '口服/外用', 6.00, '15g*11袋', '盒');
INSERT INTO `drug` VALUES (1929, '000060', '', 'YQJDP', '银翘解毒片', '银翘解毒片', '0.5克*12片*3板', NULL, '广西禅方药业有限公司', '盒', 1, 4.00, 0, '0.5克', '3天', '2次/天', '口服/外用', 4.00, '0.5克*12片*3板', '盒');
INSERT INTO `drug` VALUES (1930, '000061', '', 'BLGKL', '板蓝根颗粒', '板蓝根颗粒', '10g*20代', NULL, '广西禅方药业有限公司', '袋', 1, 5.00, 0, '10g', '3天', '2次/天', '口服/外用', 5.00, '10g*20代', '袋');
INSERT INTO `drug` VALUES (1931, '000062', '', 'BLGKL', '板蓝根颗粒', '板蓝根颗粒', '10克*21袋', NULL, '广西禅方药业有限公司', '袋', 1, 5.00, 0, '10克', '3天', '2次/天', '口服/外用', 5.00, '10克*21袋', '袋');
INSERT INTO `drug` VALUES (1932, '000063', '', 'MRRCW', '麻仁润肠丸', '麻仁润肠丸', '6g', NULL, '北京同仁堂股份有限公司同仁堂制药厂', '盒', 1, 10.50, 100, '6g', '3天', '2次/天', '口服/外用', 10.50, '6g', '盒');
INSERT INTO `drug` VALUES (1933, '000064', '', 'XEAFHNMKL', '小儿氨酚黄那敏颗粒', '小儿氨酚黄那敏颗粒', '20袋', NULL, '石家庄神威药业', '盒', 1, 3.50, 120, '1袋', '3天', '2次/天', '口服/外用', 3.50, '20袋', '盒');
INSERT INTO `drug` VALUES (1934, '000065', '', 'MMDHW', '明目地黄丸', '明目地黄丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 7.00, 0, '9g', '3天', '2次/天', '口服/外用', 7.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (1935, '000066', '', 'JSZHP', '金嗓子喉片', '金嗓子喉片', '2g*12片', NULL, '广西金嗓子有限公司', '盒', 1, 6.00, 80, '2g', '3天', '2次/天', '口服/外用', 6.00, '2g*12片', '盒');
INSERT INTO `drug` VALUES (1936, '000067', '', 'YSBTSTP', '盐酸倍他司汀片', '盐酸倍他司汀片', '4mg*100片', NULL, '河南中杰药业有限公司', '瓶', 1, 4.00, 0, '4mg', '3天', '2次/天', '口服/外用', 4.00, '4mg*100片', '瓶');
INSERT INTO `drug` VALUES (1937, '000068', '', 'JKSQW', '金匮肾气丸', '金匮肾气丸', '6g*10丸', NULL, '颈复康药业集团赤峰丹龙药业有限公司', '盒', 1, 10.00, 0, '6g', '3天', '2次/天', '口服/外用', 10.00, '6g*10丸', '盒');
INSERT INTO `drug` VALUES (1938, '000069', '', 'NHJDW', '牛黄解毒丸', '牛黄解毒丸', '30g', NULL, '北京同仁堂科技发展股份有限公司制药厂', '盒', 1, 6.00, 0, '1g', '3天', '2次/天', '口服/外用', 6.00, '30g', '盒');
INSERT INTO `drug` VALUES (1939, '000070', '', 'TXLFW', '通宣理肺丸', '通宣理肺丸', '6克*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 7.00, 0, '6克', '3天', '2次/天', '口服/外用', 7.00, '6克*10丸', '盒');
INSERT INTO `drug` VALUES (1940, '000071', '', 'JXZP', '甲硝唑片', '甲硝唑片', '0.2g*42片', NULL, '四川省通园制药有限公司', '盒', 1, 3.00, 0, '0.2g', '3天', '2次/天', '口服/外用', 3.00, '0.2g*42片', '盒');
INSERT INTO `drug` VALUES (1941, '000072', '', 'XYLDP', '消炎利胆片', '消炎利胆片', '0.25g*100片', NULL, '广东嘉应制药股份有限公司', '瓶', 1, 6.00, 360, '0.25g', '3天', '2次/天', '口服/外用', 6.00, '0.25g*100片', '瓶');
INSERT INTO `drug` VALUES (1942, '000073', '', 'DYXAJFP', '对乙酰氨基酚片', '对乙酰氨基酚片', '0.5g*24片', NULL, '四川通园制药有限公司', '盒', 1, 2.20, 0, '0.5g', '3天', '2次/天', '口服/外用', 2.20, '0.5g*24片', '盒');
INSERT INTO `drug` VALUES (1943, '000074', '', 'AQMSKL', '阿奇霉素颗粒', '阿奇霉素颗粒', '0.1g*24袋', NULL, '湖南千金湘江药业', '盒', 1, 8.00, 0, '0.1g', '3天', '2次/天', '口服/外用', 8.00, '0.1g*24袋', '盒');
INSERT INTO `drug` VALUES (1944, '000075', '', 'LDHBDCRP', '柳氮磺吡啶肠溶片', '柳氮磺吡啶肠溶片', '0.25g*100片', NULL, '山西同达药业有限公司', '瓶', 1, 32.50, 0, '0.25g', '3天', '2次/天', '口服/外用', 32.50, '0.25g*100片', '瓶');
INSERT INTO `drug` VALUES (1945, '000076', '', 'TSLP', '碳酸锂片', '碳酸锂片', '0.25*100片', NULL, '湖南千金湘江药业', '瓶', 1, 6.50, 0, '0.25', '3天', '2次/天', '口服/外用', 6.50, '0.25*100片', '瓶');
INSERT INTO `drug` VALUES (1946, '000078', '', 'QKLRJN', '清开灵软胶囊', '清开灵软胶囊', '0.4g*12粒', NULL, '石家庄神威药业', '盒', 1, 8.50, 0, '0.4g', '3天', '2次/天', '口服/外用', 8.50, '0.4g*12粒', '盒');
INSERT INTO `drug` VALUES (1947, '000079', '', 'XYKL', '逍遥颗粒', '逍遥颗粒', '6g*10袋', NULL, '成都森科制药有限公司', '盒', 1, 20.00, 0, '6g', '3天', '2次/天', '口服/外用', 20.00, '6g*10袋', '盒');
INSERT INTO `drug` VALUES (1948, '000080', '', 'FFQYHLP', '复方氢?趸疗?', '复方氢氧化铝片', '100片', NULL, '河北天成药业股份有限公司', '瓶', 1, 3.20, 0, '1片', '3天', '2次/天', '口服/外用', 3.20, '100片', '瓶');
INSERT INTO `drug` VALUES (1949, '000081', '', 'JGAJN', '甲钴胺胶囊', '甲钴胺胶囊', '0.5mg*24粒', NULL, '福建华海药业', '盒', 1, 15.00, 0, '0.5mg', '3天', '2次/天', '口服/外用', 15.00, '0.5mg*24粒', '盒');
INSERT INTO `drug` VALUES (1950, '000082', '', 'TBKLKL', '头孢克洛颗粒', '头孢克洛颗粒', '10125g*6包', NULL, '汕头金石制药总厂', '盒', 1, 12.00, 0, '10125g', '3天', '2次/天', '口服/外用', 12.00, '10125g*6包', '盒');
INSERT INTO `drug` VALUES (1951, '000083', '', 'BTYNP', '苯妥英钠片', '苯妥英钠片', '50mg*100片', NULL, '河北东风药业有限公司', '瓶', 1, 3.00, 0, '50mg', '3天', '2次/天', '口服/外用', 3.00, '50mg*100片', '瓶');
INSERT INTO `drug` VALUES (1952, '000084', '', 'ASPLCRP', '阿司匹林肠溶片', '阿司匹林肠溶片', '100片', NULL, '河北天成药业股份有限公司', '瓶', 1, 3.50, 0, '1片', '3天', '2次/天', '口服/外用', 3.50, '100片', '瓶');
INSERT INTO `drug` VALUES (1953, '000085', '', 'AQMSKL', '阿奇霉素颗粒', '阿奇霉素颗粒', '0.1g*6袋', NULL, '河北东风药业', '盒', 1, 2.50, 0, '0.1g', '3天', '2次/天', '口服/外用', 2.50, '0.1g*6袋', '盒');
INSERT INTO `drug` VALUES (1954, '000086', '', 'FFCXLP', '复方穿心莲片', '复方穿心莲片', '0.37g*100片/瓶', NULL, '广西圣特药业', '瓶', 1, 5.00, 0, '0.37g', '3天', '2次/天', '口服/外用', 5.00, '0.37g*100片/瓶', '瓶');
INSERT INTO `drug` VALUES (1955, '000087', '', 'XGGAP', '腺苷钴胺片', '腺苷钴胺片', '0.25g*100片', NULL, '石药集团欧意药业', '瓶', 1, 4.00, 0, '0.25g', '3天', '2次/天', '口服/外用', 4.00, '0.25g*100片', '瓶');
INSERT INTO `drug` VALUES (1956, '000088', '', 'SLFSNHSP', '双氯芬酸钠缓释片', '双氯芬酸钠缓释片', '0.1g*10片', NULL, '湖南华纳大药厂', '盒', 1, 4.00, 0, '0.1g', '3天', '2次/天', '口服/外用', 4.00, '0.1g*10片', '盒');
INSERT INTO `drug` VALUES (1957, '000089', '', 'BLPYFGJJP', '保灵牌孕妇钙咀嚼片', '保灵牌孕妇钙咀嚼片', '1.7g*30片', NULL, '杭州澳医保灵药业', '盒', 1, 25.50, 0, '1.7g', '3天', '2次/天', '口服/外用', 25.50, '1.7g*30片', '盒');
INSERT INTO `drug` VALUES (1958, '000090', '', 'HXZJJN', '活血壮筋胶囊', '活血壮筋胶囊', '0.25g*14粒', NULL, '鹤壁市中药有限公司', '盒', 1, 24.00, 0, '0.25g', '3天', '2次/天', '口服/外用', 24.00, '0.25g*14粒', '盒');
INSERT INTO `drug` VALUES (1959, '000091', '', 'TSQNP', '碳酸氢钠片', '碳酸氢钠片', '0.5g*100片', NULL, '河北天成药业股份有限公司', '瓶', 1, 3.00, 0, '0.5g', '3天', '2次/天', '口服/外用', 3.00, '0.5g*100片', '瓶');
INSERT INTO `drug` VALUES (1960, '000092', '', 'KSL', '开塞露', '开塞露', '20ml', NULL, '武汉五景药业有限公司', '支', 1, 1.50, 0, '20ml', '3天', '2次/天', '口服/外用', 1.50, '20ml', '支');
INSERT INTO `drug` VALUES (1961, '000093', '', 'ASPLCRP', '阿司匹林肠溶片', '阿司匹林肠溶片', '0.3g*100片', NULL, '河北天成药业股份有限公司', '瓶', 1, 5.00, 0, '0.3g', '3天', '2次/天', '口服/外用', 5.00, '0.3g*100片', '瓶');
INSERT INTO `drug` VALUES (1962, '000094', '', 'HXZQS', '藿香正气水', '藿香正气水', '10ml*10支', NULL, '广东一力罗定制药有限公司', '盒', 1, 4.00, 0, '10ml', '3天', '2次/天', '口服/外用', 4.00, '10ml*10支', '盒');
INSERT INTO `drug` VALUES (1963, '000095', '', 'SDCBY', '蛇胆川贝液', '蛇胆川贝液', '10ml*6支', NULL, '四川通园制药有限公司', '盒', 1, 3.00, 0, '10ml', '3天', '2次/天', '口服/外用', 3.00, '10ml*6支', '盒');
INSERT INTO `drug` VALUES (1964, '000096', '', 'BLPYBKFY', '保灵牌孕宝口服液', '保灵牌孕宝口服液', '10ml/支*28', NULL, '杭州保灵有限公司', '盒', 1, 68.00, 0, '10ml', '3天', '2次/天', '口服/外用', 68.00, '10ml/支*28', '盒');
INSERT INTO `drug` VALUES (1965, '000097', '', 'BDGJN', '北豆根胶囊', '北豆根胶囊', '30mg*20粒*2板', NULL, '颈复康药业集团有限公司雾灵山药业', '盒', 1, 5.00, 0, '30mg', '3天', '2次/天', '口服/外用', 5.00, '30mg*20粒*2板', '盒');
INSERT INTO `drug` VALUES (1966, '000098', '', 'JTGJBJN', '胶体果胶铋胶囊', '胶体果胶铋胶囊', '50mg*20粒', NULL, '山西千汇', '盒', 1, 5.00, 50, '50mg', '3天', '2次/天', '口服/外用', 5.00, '50mg*20粒', '盒');
INSERT INTO `drug` VALUES (1967, '000099', '', 'DPLTP', '多潘立酮片', '多潘立酮片', '10mg*30片', NULL, '湖南千金湘江药业股份有限公司', '盒', 1, 4.50, 0, '10mg', '3天', '2次/天', '口服/外用', 4.50, '10mg*30片', '盒');
INSERT INTO `drug` VALUES (1968, '000100', '', 'BLGKL', '板蓝根颗粒', '板蓝根颗粒', '3g*20袋', NULL, '广西禅方药业有限公司', '盒', 1, 5.00, 0, '3g', '3天', '2次/天', '口服/外用', 5.00, '3g*20袋', '盒');
INSERT INTO `drug` VALUES (1969, '000101', '', 'ZHHY', '正红花油', '正红花油', '20ml', NULL, '福建太平洋制药', '瓶', 1, 3.00, 0, '1ml', '3天', '2次/天', '口服/外用', 3.00, '20ml', '瓶');
INSERT INTO `drug` VALUES (1970, '000102', '', 'MLSYNPLP', '马来酸依那普利片', '马来酸依那普利片', '10mg*16片', NULL, '湖南千金湘江药业', '盒', 1, 7.50, 0, '10mg', '3天', '2次/天', '口服/外用', 7.50, '10mg*16片', '盒');
INSERT INTO `drug` VALUES (1971, '000104', '', 'QKLKFY', '清开灵口服液', '清开灵口服液', '10ml*6支', NULL, '唐山乐福药业', '盒', 1, 0.00, 0, '10ml', '3天', '2次/天', '口服/外用', 0.00, '10ml*6支', '盒');
INSERT INTO `drug` VALUES (1972, '000105', '', 'JSSMTLEP', '酒石酸美托洛尔片', '酒石酸美托洛尔片', '50mg*30片', NULL, '烟台巨先药业有限公司', '盒', 1, 5.00, 400, '50mg', '3天', '2次/天', '口服/外用', 5.00, '50mg*30片', '盒');
INSERT INTO `drug` VALUES (1973, '000106', '', 'DMP', '多酶片', '多酶片', '100片', NULL, '四川依科制药', '瓶', 1, 4.50, 0, '1片', '3天', '2次/天', '口服/外用', 4.50, '100片', '瓶');
INSERT INTO `drug` VALUES (1974, '000107', '', 'GMLKL', '感冒灵颗粒', '感冒灵颗粒', '10g*6包', NULL, '广州白云山何济公制药厂', '盒', 1, 6.00, 0, '10g', '3天', '2次/天', '口服/外用', 6.00, '10g*6包', '盒');
INSERT INTO `drug` VALUES (1975, '000109', '', 'CSPNSP', '醋酸泼尼松片', '醋酸泼尼松片', '5mg*1000片', NULL, '山东鲁抗辰欣药业', '瓶', 1, 0.00, 0, '5mg', '3天', '2次/天', '口服/外用', 0.00, '5mg*1000片', '瓶');
INSERT INTO `drug` VALUES (1976, '000110', '', 'XGGAP', '腺苷钴安片', '腺苷钴安片', '0.25mg*100片', NULL, '石家庄欧意药业', '瓶', 1, 0.00, 0, '0.25mg', '3天', '2次/天', '口服/外用', 0.00, '0.25mg*100片', '瓶');
INSERT INTO `drug` VALUES (1977, '000111', '', 'FFCXLP', '复方穿心莲片', '复方穿心莲片', '100片*10袋', NULL, '广西圣特药业', '包', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '100片*10袋', '包');
INSERT INTO `drug` VALUES (1978, '000112', '', 'LSTBTLPBLKN', '硫酸特布他林片(博利康尼)', '硫酸特布他林片(博利康尼)', '2.5mg*20片', NULL, '阿斯利康制药', '盒', 1, 0.00, 0, '2.5mg', '3天', '2次/天', '口服/外用', 0.00, '2.5mg*20片', '盒');
INSERT INTO `drug` VALUES (1979, '000113', '', 'YKX01', '伊可新(0-1)', '伊可新(0-1)', '20粒', NULL, '山东达因海洋生物制药', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '20粒', '盒');
INSERT INTO `drug` VALUES (1980, '000114', '', 'BTMSP', '倍他米松片', '倍他米松片', '0.5mg100片', NULL, '山东鲁抗辰欣药业', '瓶', 1, 0.00, 0, '0.5mg', '3天', '2次/天', '口服/外用', 0.00, '0.5mg100片', '瓶');
INSERT INTO `drug` VALUES (1981, '000115', '', 'EJ', '阿胶', '阿胶', '31.25*16块', NULL, '北京同仁堂制药厂', '盒', 1, 67.00, 0, '', '3天', '2次/天', '口服/外用', 67.00, '31.25*16块', '盒');
INSERT INTO `drug` VALUES (1982, '000116', '', 'DZXYW', '丹栀逍遥丸', '丹栀逍遥丸', '6g*10袋', NULL, '昆明中药厂', '盒', 1, 5.00, 0, '6g', '3天', '2次/天', '口服/外用', 5.00, '6g*10袋', '盒');
INSERT INTO `drug` VALUES (1983, '000117', '', 'SXJXW', '速效救心丸', '速效救心丸', '40mg*60粒', NULL, '天津中新药业', '瓶', 1, 0.00, 0, '40mg', '3天', '2次/天', '口服/外用', 0.00, '40mg*60粒', '瓶');
INSERT INTO `drug` VALUES (1984, '000118', '', 'SMY', '生脉饮', '生脉饮', '10ml*10支', NULL, '江西诚志永丰药业', '盒', 1, 2.00, 0, '', '3天', '2次/天', '口服/外用', 2.00, '10ml*10支', '盒');
INSERT INTO `drug` VALUES (1985, '000119', '', 'LFHMP', '氯芬黄敏片', '氯芬黄敏片', '20片*40板', NULL, '北京双吉制药', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '20片*40板', '盒');
INSERT INTO `drug` VALUES (1986, '000120', '', 'FFAFNMKL', '复方氨酚那敏颗粒', '复方氨酚那敏颗粒', '7g*50袋/包', NULL, '河北国金药业', '包', 1, 7.00, 149, '7g', '3天', '2次/天', '口服/外用', 7.00, '7g*50袋/包', '包');
INSERT INTO `drug` VALUES (1987, '000121', '', 'SJHXP', '舒筋活血片', '舒筋活血片', '0.3g*36片', NULL, '河南中杰药业', '盒', 1, 1.50, 0, '0.3g', '3天', '2次/天', '口服/外用', 1.50, '0.3g*36片', '盒');
INSERT INTO `drug` VALUES (1988, '000122', '', 'JGP', '肌苷片', '肌苷片', '0.2*100片', NULL, '山东鲁抗辰欣药业', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '0.2*100片', '盒');
INSERT INTO `drug` VALUES (1989, '000123', '', 'ZSSZNP', '藻酸双酯钠片', '藻酸双酯钠片', '50mg*100片', NULL, '青岛正大海尔制药有限公司', '瓶', 1, 12.00, 900, '50mg', '3天', '2次/天', '口服/外用', 12.00, '50mg*100片', '瓶');
INSERT INTO `drug` VALUES (1990, '000124', '', 'ATFTTGP', '阿托伐他汀钙片', '阿托伐他汀钙片', '20mg*7片', NULL, '北京嘉林药业股份有限公司', '盒', 1, 0.00, 0, '20mg', '3天', '2次/天', '口服/外用', 0.00, '20mg*7片', '盒');
INSERT INTO `drug` VALUES (1991, '000129', '', 'XEAFHNMKL', '小儿氨酚黄那敏颗粒', '小儿氨酚黄那敏颗粒', '4g*15袋', NULL, '浙江亚峰药厂有限公司', '盒', 1, 0.00, 0, '4g', '3天', '2次/天', '口服/外用', 0.00, '4g*15袋', '盒');
INSERT INTO `drug` VALUES (1992, '000132', '', 'MYLSXZCG', '马应龙麝香痔疮膏', '马应龙麝香痔疮膏', '10g', NULL, '马应龙药业集团股份有限公司', '盒', 1, 8.00, 0, '', '3天', '2次/天', '口服/外用', 8.00, '10g', '盒');
INSERT INTO `drug` VALUES (1993, '000133', '', 'HXZQS', '藿香正气水', '藿香正气水', '10ml*10支', NULL, '四川依科制药有限公司', '盒', 1, 0.00, 0, '10ml', '3天', '2次/天', '口服/外用', 0.00, '10ml*10支', '盒');
INSERT INTO `drug` VALUES (1994, '000134', '', 'XZL', '鲜竹沥', '鲜竹沥', '15ml*6支', NULL, '四川通园制药有限公司', '盒', 1, 2.00, 0, '15ml', '3天', '2次/天', '口服/外用', 2.00, '15ml*6支', '盒');
INSERT INTO `drug` VALUES (1995, '000135', '', 'LDXGW', '龙胆泻肝丸', '龙胆泻肝丸', '6g*12袋', NULL, '北京同仁堂制药有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '6g*12袋', '盒');
INSERT INTO `drug` VALUES (1996, '000136', '', 'YSLNTDJN', '盐酸雷尼替丁胶囊', '盐酸雷尼替丁胶囊', '0.15g*30粒', NULL, '齐齐哈尔满山红药业', '瓶', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '0.15g*30粒', '瓶');
INSERT INTO `drug` VALUES (1997, '000137', '', 'JSSMTLEP', '酒石酸美脱洛尔片', '酒石酸美脱洛尔片', '50mg*20片', NULL, '阿斯利康制药有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '50mg*20片', '盒');
INSERT INTO `drug` VALUES (1998, '000138', '', 'SXZCS', '麝香痔疮栓', '麝香痔疮栓', '1.5g*6粒', NULL, '马应龙药业集团有限公司', '盒', 1, 11.00, 0, '', '3天', '2次/天', '口服/外用', 11.00, '1.5g*6粒', '盒');
INSERT INTO `drug` VALUES (1999, '000139', '', 'EWPLGJJP', '二维葡磷钙咀嚼片', '二维葡磷钙咀嚼片', '400片', NULL, '西安利君制药有限公司', '瓶', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '400片', '瓶');
INSERT INTO `drug` VALUES (2000, '000140', '', 'TBABJN', '头孢氨苄胶囊', '头孢氨苄胶囊', '0.125g', NULL, '石家庄市新华制药厂', '盒', 1, 4.50, 100, '', '3天', '2次/天', '口服/外用', 4.50, '0.125g', '盒');
INSERT INTO `drug` VALUES (2001, '000141', '', 'XSYSLZP', '硝酸异山梨酯片', '硝酸异山梨酯片', '5mg*100片', NULL, '天津太平洋制药有限公司', '瓶', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '5mg*100片', '瓶');
INSERT INTO `drug` VALUES (2002, '000142', '', 'YQJDP', '银翘解毒片', '银翘解毒片', '0.52g', NULL, '成都森克制药有限公司', '盒', 1, 3.00, 0, '', '3天', '2次/天', '口服/外用', 3.00, '0.52g', '盒');
INSERT INTO `drug` VALUES (2003, '000143', '', 'FFKSXJ', '复方苦参洗剂', '复方苦参洗剂', '120ml/瓶', NULL, '浙江中法制药有限公司', '瓶', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '120ml/瓶', '瓶');
INSERT INTO `drug` VALUES (2004, '000144', '', 'XSTQLSQJN', '缬沙坦氢氯噻嗪胶囊', '缬沙坦氢氯噻嗪胶囊', '12粒', NULL, '山东辰欣药业', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '12粒', '盒');
INSERT INTO `drug` VALUES (2005, '000145', '', 'RSGPW', '人参归脾丸', '人参归脾丸', '9g*10丸', NULL, '药都制药集团', '盒', 1, 6.00, 0, '', '3天', '2次/天', '口服/外用', 6.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (2006, '000146', '', 'XBDPHSP', '硝苯地平缓释片（Ⅱ）', '硝苯地平缓释片（Ⅱ）', '20mg*30片', NULL, '德州博城制药有限公司', '瓶', 1, 15.00, 600, '', '3天', '2次/天', '口服/外用', 15.00, '20mg*30片', '瓶');
INSERT INTO `drug` VALUES (2007, '000147', '', 'GYSXDNFP', '枸橼酸西地那非片', '枸橼酸西地那非片', '50mg*3片', NULL, '广州白云山制药股份有限公司', '盒', 1, 133.00, 50, '', '3天', '2次/天', '口服/外用', 133.00, '50mg*3片', '盒');
INSERT INTO `drug` VALUES (2008, '000148', '', 'MTSS', '蒙脱石散', '蒙脱石散', '3g*8袋', NULL, '湖北汇中制药有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '3g*8袋', '盒');
INSERT INTO `drug` VALUES (2009, '000149', '', 'SBP', '肾宝片', '肾宝片', '0.7g*9片*8板', NULL, '江西汇仁药业有限公司', '盒', 1, 198.00, 160, '', '3天', '2次/天', '口服/外用', 198.00, '0.7g*9片*8板', '盒');
INSERT INTO `drug` VALUES (2010, '000150', '', 'SBP', '肾宝片', '肾宝片', '0.7g*9片*14板', NULL, '江苏汇仁药业有限公司', '盒', 1, 322.00, 24, '', '3天', '2次/天', '口服/外用', 322.00, '0.7g*9片*14板', '盒');
INSERT INTO `drug` VALUES (2011, '000151', '', 'YSFGLQJN', '盐酸氟桂利嗪胶囊', '盐酸氟桂利嗪胶囊', '5mg*60粒', NULL, '天津创新药业', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '5mg*60粒', '盒');
INSERT INTO `drug` VALUES (2012, '000152', '', 'FFDSP', '复方丹参片', '复方丹参片', '180片', NULL, '河南广宇制药', '瓶', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '180片', '瓶');
INSERT INTO `drug` VALUES (2013, '000153', '', 'SJHXP', '舒筋活血片', '舒筋活血片', '36片', NULL, '河南中杰药业', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '36片', '盒');
INSERT INTO `drug` VALUES (2014, '000154', '', 'DXSYSLZP', '单硝酸异山梨酯片', '单硝酸异山梨酯片', '20mg*12片*3板', NULL, '山东鲁抗辰欣药业', '盒', 1, 30.00, 400, '', '3天', '2次/天', '口服/外用', 30.00, '20mg*12片*3板', '盒');
INSERT INTO `drug` VALUES (2015, '000155', '', 'FFAFNMKL', '复方氨酚那敏颗粒', '复方氨酚那敏颗粒', '50袋/包', NULL, '河北东风药业', '包', 1, 8.00, 97, '', '3天', '2次/天', '口服/外用', 8.00, '50袋/包', '包');
INSERT INTO `drug` VALUES (2016, '000156', '', 'XSTQLSQJN', '缬沙坦氢氯噻嗪胶囊', '缬沙坦氢氯噻嗪胶囊', '12粒*板', NULL, '山东鲁抗辰欣药业', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '12粒*板', '盒');
INSERT INTO `drug` VALUES (2017, '000157', '', 'XSYKZPJ', '硝酸益康唑喷剂', '硝酸益康唑喷剂', '50ml', NULL, '修正药业', '瓶', 1, 24.50, 1599, '', '3天', '2次/天', '口服/外用', 24.50, '50ml', '瓶');
INSERT INTO `drug` VALUES (2018, '000158', '', 'CLGCJN', '穿龙骨刺胶囊', '穿龙骨刺胶囊', '0.5g*60粒', NULL, '烟台大洋制药', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '0.5g*60粒', '盒');
INSERT INTO `drug` VALUES (2019, '000159', '', 'SSPWJ', '烧伤喷雾剂', '烧伤喷雾剂', '40ml', NULL, '华北制药', '瓶', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '40ml', '瓶');
INSERT INTO `drug` VALUES (2020, '000160', '', 'SZASKFY', '参芝安神口服液', '参芝安神口服液', '10ml*10支', NULL, '吉林龙泰制药', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '10ml*10支', '盒');
INSERT INTO `drug` VALUES (2021, '000161', '', 'FMSYTKL', '富马酸亚铁颗粒', '富马酸亚铁颗粒', '0.2g*12袋', NULL, '湖北汇中制药有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '0.2g*12袋', '盒');
INSERT INTO `drug` VALUES (2022, '000162', '', 'SFTG', '舒腹贴膏', '舒腹贴膏', '2片*2袋', NULL, '山东明人福瑞达卫生材料有限公司', '盒', 1, 12.00, 399, '', '3天', '2次/天', '口服/外用', 12.00, '2片*2袋', '盒');
INSERT INTO `drug` VALUES (2023, '000163', '', 'NXSKFY', '脑心舒口服液', '脑心舒口服液', '10ml*12支', NULL, '江苏神华药业有限公司', '盒', 1, 14.00, 600, '', '3天', '2次/天', '口服/外用', 14.00, '10ml*12支', '盒');
INSERT INTO `drug` VALUES (2024, '000164', '', 'XBDPHSP', '硝苯地平缓释片（Ⅱ）', '硝苯地平缓释片（Ⅱ）', '20mg*30片', NULL, '烟台鲁银药业有限公司', '瓶', 1, 14.00, 900, '', '3天', '2次/天', '口服/外用', 14.00, '20mg*30片', '瓶');
INSERT INTO `drug` VALUES (2025, '000165', '', 'LHZZJN', '芦荟珍珠胶囊', '芦荟珍珠胶囊', '0.5g*16粒', NULL, '河北君临药业有限公司', '盒', 1, 55.00, 100, '', '3天', '2次/天', '口服/外用', 55.00, '0.5g*16粒', '盒');
INSERT INTO `drug` VALUES (2026, '000166', '', 'EJHQKFY', '阿胶黄芪口服液', '阿胶黄芪口服液', '10ml*12支', NULL, '河北君临药业有限公司', '支', 1, 68.00, 162, '', '3天', '2次/天', '口服/外用', 68.00, '10ml*12支', '支');
INSERT INTO `drug` VALUES (2027, '000167', '', 'YSXTLQP', '盐酸西替利嗪片', '盐酸西替利嗪片', '10mg*12片', NULL, '广东彼迪药业有限公司', '盒', 1, 2.50, 0, '', '3天', '2次/天', '口服/外用', 2.50, '10mg*12片', '盒');
INSERT INTO `drug` VALUES (2028, '000168', '', 'RSYRW', '人参养荣丸', '人参养荣丸', '9g*10丸', NULL, '北京同仁堂', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (2029, '000169', '', 'DSZW', '大山楂丸', '大山楂丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (2030, '000170', '', 'JXZLJDXY', '甲硝唑氯己定洗液', '甲硝唑氯己定洗液', '50ml', NULL, '海南皇城制药厂', '瓶', 1, 5.00, 0, '', '3天', '2次/天', '口服/外用', 5.00, '50ml', '瓶');
INSERT INTO `drug` VALUES (2031, '000171', '', 'ASBNY', '安神补脑液', '安神补脑液', '10ml*20支', NULL, '吉林敖东延边药业股份有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '10ml*20支', '盒');
INSERT INTO `drug` VALUES (2032, '000172', '', 'ZYJSW', '壮腰健肾丸', '壮腰健肾丸', '52克', NULL, '广东嘉应制药股份有限公司', '盒', 1, 4.00, 0, '', '3天', '2次/天', '口服/外用', 4.00, '52克', '盒');
INSERT INTO `drug` VALUES (2033, '000173', '', 'LHQWJN', '连花清瘟胶囊', '连花清瘟胶囊', '0.35g*24粒', NULL, '石家庄以岭药业', '盒', 1, 12.00, 0, '', '3天', '2次/天', '口服/外用', 12.00, '0.35g*24粒', '盒');
INSERT INTO `drug` VALUES (2034, '000174', '', 'XSYWW', '香砂养胃丸', '香砂养胃丸', '200丸', NULL, '湖北御金丹药业有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '200丸', '盒');
INSERT INTO `drug` VALUES (2035, '000175', '', 'XCHKL', '小柴胡颗粒', '小柴胡颗粒', '10g*6袋', NULL, '四川逢春制药有限公司', '盒', 1, 2.00, 0, '', '3天', '2次/天', '口服/外用', 2.00, '10g*6袋', '盒');
INSERT INTO `drug` VALUES (2036, '000176', '', 'DLCP', '冬凌草片', '冬凌草片', '100片', NULL, '河南广宇博科生物制药', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '100片', '盒');
INSERT INTO `drug` VALUES (2037, '000177', '', 'SQDBW', '十全大补丸', '十全大补丸', '360粒', NULL, '北京同仁堂科技发展股份有限公司制药厂', '盒', 1, 9.00, 0, '', '3天', '2次/天', '口服/外用', 9.00, '360粒', '盒');
INSERT INTO `drug` VALUES (2038, '000178', '', 'ZRASKL', '枣仁安神颗粒', '枣仁安神颗粒', '5g*10袋', NULL, '台州南峰药业有限公司', '盒', 1, 15.00, 0, '', '3天', '2次/天', '口服/外用', 15.00, '5g*10袋', '盒');
INSERT INTO `drug` VALUES (2039, '000179', '', 'DLCP', '冬凌草片', '冬凌草片', '100片', NULL, '河南广宇博科生物制药', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '100片', '盒');
INSERT INTO `drug` VALUES (2040, '000180', '', 'FFXZLY', '复方鲜竹沥液', '复方鲜竹沥液', '10ml*6瓶', NULL, '江西南昌济生制药厂', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '10ml*6瓶', '盒');
INSERT INTO `drug` VALUES (2041, '000181', '', 'SWPWSKL', '四味脾胃舒颗粒', '四味脾胃舒颗粒', '10g*15袋', NULL, '广西天天乐药业股份有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '10g*15袋', '盒');
INSERT INTO `drug` VALUES (2042, '000182', '', 'SHP', '三黄片', '三黄片', '24片*2板', NULL, '湖北天圣清大制药有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '24片*2板', '盒');
INSERT INTO `drug` VALUES (2043, '000183', '', 'HQJKFY', '黄芪精口服液', '黄芪精口服液', '10ml*12支', NULL, '扬子江药业', '盒', 1, 21.00, 38, '', '3天', '2次/天', '口服/外用', 21.00, '10ml*12支', '盒');
INSERT INTO `drug` VALUES (2044, '000184', '', 'YPSTJN', '依帕司他胶囊', '依帕司他胶囊', '50mg*10粒', NULL, '扬子江药业集团南京海陵药业有限公司', '盒', 1, 31.00, 0, '', '3天', '2次/天', '口服/外用', 31.00, '50mg*10粒', '盒');
INSERT INTO `drug` VALUES (2045, '000185', '', 'BLMJN', '百乐眠胶囊', '百乐眠胶囊', '0.27g*24粒', NULL, '扬子江药业', '盒', 1, 34.00, 0, '', '3天', '2次/天', '口服/外用', 34.00, '0.27g*24粒', '盒');
INSERT INTO `drug` VALUES (2046, '000186', '', 'WSKLHT', '胃苏颗粒（含糖）', '胃苏颗粒（含糖）', '15g*3袋', NULL, '扬子江药业集团江苏制药股份有限公司', '盒', 1, 8.00, 0, '', '3天', '2次/天', '口服/外用', 8.00, '15g*3袋', '盒');
INSERT INTO `drug` VALUES (2047, '000187', '', 'WSKLHT', '胃苏颗粒（含糖）', '胃苏颗粒（含糖）', '15g*9袋', NULL, '扬子江药业集团江苏制药股份有限公司', '盒', 1, 22.00, 0, '', '3天', '2次/天', '口服/外用', 22.00, '15g*9袋', '盒');
INSERT INTO `drug` VALUES (2048, '000188', '', 'LSLZCRP', '兰索拉唑肠溶片', '兰索拉唑肠溶片', '15mg*14片', NULL, '扬子江药业', '盒', 1, 33.00, 0, '', '3天', '2次/天', '口服/外用', 33.00, '15mg*14片', '盒');
INSERT INTO `drug` VALUES (2049, '000189', '', 'KLMSFSP', '克拉霉素分散片', '克拉霉素分散片', '0.25g*6片', NULL, '江苏扬子江药业集团有限公司', '盒', 1, 10.00, 100, '', '3天', '2次/天', '口服/外用', 10.00, '0.25g*6片', '盒');
INSERT INTO `drug` VALUES (2050, '000190', '', 'YPSTP', '依帕司他片', '依帕司他片', '50mg*10片', NULL, '扬子江药业', '盒', 1, 36.00, 0, '', '3天', '2次/天', '口服/外用', 36.00, '50mg*10片', '盒');
INSERT INTO `drug` VALUES (2051, '000191', '', 'WSKLWT', '胃苏颗粒（无糖）', '胃苏颗粒（无糖）', '5g*3袋', NULL, '扬子江药业集团江苏制药股份有限公司', '盒', 1, 8.00, 447, '', '3天', '2次/天', '口服/外用', 8.00, '5g*3袋', '盒');
INSERT INTO `drug` VALUES (2052, '000192', '', 'WSKLWT', '胃苏颗粒（无糖）', '胃苏颗粒（无糖）', '5g*9袋', NULL, '扬子江药业集团江苏制药股份有限公司', '盒', 1, 22.00, 0, '', '3天', '2次/天', '口服/外用', 22.00, '5g*9袋', '盒');
INSERT INTO `drug` VALUES (2053, '000193', '', 'LHMSJN', '罗红霉素胶囊', '罗红霉素胶囊', '50mg*12粒', NULL, '扬子江药业集团有限公司', '盒', 1, 8.60, 0, '', '3天', '2次/天', '口服/外用', 8.60, '50mg*12粒', '盒');
INSERT INTO `drug` VALUES (2054, '000194', '', 'YSZYFSXJN', '盐酸左氧氟沙星胶囊', '盐酸左氧氟沙星胶囊', '0.1g*6粒', NULL, '扬子江药业', '盒', 1, 4.80, 0, '', '3天', '2次/天', '口服/外用', 4.80, '0.1g*6粒', '盒');
INSERT INTO `drug` VALUES (2055, '000195', '', 'YSZYFSXJN', '盐酸左氧氟沙星胶囊', '盐酸左氧氟沙星胶囊', '0.1g*12粒', NULL, '扬子江药业', '盒', 1, 8.80, 0, '', '3天', '2次/天', '口服/外用', 8.80, '0.1g*12粒', '盒');
INSERT INTO `drug` VALUES (2056, '000196', '', 'LJMSJN', '罗经霉素胶囊', '罗经霉素胶囊', '150mg*12粒', NULL, '江苏扬子江药业集团有限公司', '盒', 1, 20.00, 0, '', '3天', '2次/天', '口服/外用', 20.00, '150mg*12粒', '盒');
INSERT INTO `drug` VALUES (2057, '000197', '', 'LFTTJN', '洛伐他汀胶囊', '洛伐他汀胶囊', '20mg*12粒', NULL, '扬子江药业集团有限公司', '盒', 1, 10.00, 200, '', '3天', '2次/天', '口服/外用', 10.00, '20mg*12粒', '盒');
INSERT INTO `drug` VALUES (2058, '000198', '', 'LQKFY', '蓝芩口服液', '蓝芩口服液', '10ml*6支', NULL, '扬子江药业', '盒', 1, 16.50, 398, '', '3天', '2次/天', '口服/外用', 16.50, '10ml*6支', '盒');
INSERT INTO `drug` VALUES (2059, '000199', '', 'XBDPHSP', '硝苯地平缓释片（Ⅰ）', '硝苯地平缓释片（Ⅰ）', '10ml*16', NULL, '扬子江药业', '瓶', 1, 4.80, 400, '', '3天', '2次/天', '口服/外用', 4.80, '10ml*16', '瓶');
INSERT INTO `drug` VALUES (2060, '000200', '', 'MLXKP', '美洛昔康片', '美洛昔康片', '7.5mg*10片', NULL, '扬子江药业集团有限公司', '盒', 1, 10.50, 190, '', '3天', '2次/天', '口服/外用', 10.50, '7.5mg*10片', '盒');
INSERT INTO `drug` VALUES (2061, '000201', '', 'YSXTLQP', '盐酸西替利嗪片', '盐酸西替利嗪片', '10ml*12片', NULL, '扬子江药业集团有限公司', '盒', 1, 10.00, 0, '', '3天', '2次/天', '口服/外用', 10.00, '10ml*12片', '盒');
INSERT INTO `drug` VALUES (2062, '000202', '', 'GYSXDNFP', '枸橼酸西地那非片', '枸橼酸西地那非片', '50mg*1', NULL, '广州白云山制药总厂', '盒', 1, 48.00, 50, '', '3天', '2次/天', '口服/外用', 48.00, '50mg*1', '盒');
INSERT INTO `drug` VALUES (2063, '000203', '', 'EJYSKFY', '阿胶益寿口服液', '阿胶益寿口服液', '20ml*10支', NULL, '陕西立众制药', '盒', 1, 35.00, 152, '', '3天', '2次/天', '口服/外用', 35.00, '20ml*10支', '盒');
INSERT INTO `drug` VALUES (2064, '000204', '', 'LWDHW', '六味地黄丸', '六味地黄丸', '9g*10丸', NULL, '北京同仁堂科技发展有限公司制药厂', '盒', 1, 9.80, 100, '', '3天', '2次/天', '口服/外用', 9.80, '9g*10丸', '盒');
INSERT INTO `drug` VALUES (2065, '000205', '', 'CXBLP', '陈香白露片', '陈香白露片', '0.5g*60片', NULL, '昆明振华制药厂有限公司', '盒', 1, 8.00, 20, '', '3天', '2次/天', '口服/外用', 8.00, '0.5g*60片', '盒');
INSERT INTO `drug` VALUES (2066, '000206', '', 'FFAFWAJN', '复方氨酚烷胺胶囊', '复方氨酚烷胺胶囊', '10粒', NULL, '云南省曲靖药业有限公司', '盒', 1, 4.50, 396, '', '3天', '2次/天', '口服/外用', 4.50, '10粒', '盒');
INSERT INTO `drug` VALUES (2067, '000207', '', 'ZYJSW', '壮腰健肾丸', '壮腰健肾丸', '35g/瓶', NULL, '广州白云山陈李济药厂', '瓶', 1, 6.50, 200, '', '3天', '2次/天', '口服/外用', 6.50, '35g/瓶', '瓶');
INSERT INTO `drug` VALUES (2068, '000208', '', 'XEAFHNMKL', '小儿氨酚黄那敏颗粒', '小儿氨酚黄那敏颗粒', '4g*12袋', NULL, '云南植物药业有限公司', '盒', 1, 6.00, 197, '', '3天', '2次/天', '口服/外用', 6.00, '4g*12袋', '盒');
INSERT INTO `drug` VALUES (2069, '000209', '', 'TMSP', '天麻素片', '天麻素片', '25mg*36片', NULL, '云南植物药业有限公司', '盒', 1, 7.50, 20, '', '3天', '2次/天', '口服/外用', 7.50, '25mg*36片', '盒');
INSERT INTO `drug` VALUES (2070, '000210', '', 'RJLKL', '乳疾灵颗粒', '乳疾灵颗粒', '14g*12袋', NULL, '河北国金药业', '盒', 1, 21.00, 60, '', '3天', '2次/天', '口服/外用', 21.00, '14g*12袋', '盒');
INSERT INTO `drug` VALUES (2071, '000211', '', 'GMZKKL', '感冒止咳颗粒', '感冒止咳颗粒', '10g*6袋', NULL, '云南植物药业有限公司', '盒', 1, 5.50, 197, '', '3天', '2次/天', '口服/外用', 5.50, '10g*6袋', '盒');
INSERT INTO `drug` VALUES (2072, '000212', '', 'SXJZP', '舒心降脂片', '舒心降脂片', '0.3g*48片', NULL, '昆明振华制药厂有限公司', '盒', 1, 15.00, 100, '', '3天', '2次/天', '口服/外用', 15.00, '0.3g*48片', '盒');
INSERT INTO `drug` VALUES (2073, '000213', '', 'YSLNTDJN', '盐酸雷尼替丁胶囊', '盐酸雷尼替丁胶囊', '0.15g*30粒', NULL, '石家庄四药有限公司', '瓶', 1, 1.70, 400, '', '3天', '2次/天', '口服/外用', 1.70, '0.15g*30粒', '瓶');
INSERT INTO `drug` VALUES (2074, '000214', '', 'GMQRKL', '感冒清热颗粒', '感冒清热颗粒', '12g*10袋', NULL, '北京同仁堂科技发展股份有限公司制药厂', '盒', 1, 8.50, 50, '', '3天', '2次/天', '口服/外用', 8.50, '12g*10袋', '盒');
INSERT INTO `drug` VALUES (2075, '000215', '', 'NHJDW', '牛黄解毒丸', '牛黄解毒丸', '2g*10袋', NULL, '北京同仁堂科技发展股份有限公司制药厂', '盒', 1, 5.00, 60, '', '3天', '2次/天', '口服/外用', 5.00, '2g*10袋', '盒');
INSERT INTO `drug` VALUES (2076, '000216', '', 'BLFP', '布洛芬片', '布洛芬片', '24片*0.1g', NULL, '昆明振华制药厂有限公司', '盒', 1, 4.00, 93, '', '3天', '2次/天', '口服/外用', 4.00, '24片*0.1g', '盒');
INSERT INTO `drug` VALUES (2077, '000217', '', 'GLBQHSP', '格列吡嗪缓释片', '格列吡嗪缓释片', '5mg*12片', NULL, '扬子江药业集团有限公司', '盒', 1, 9.50, 200, '', '3天', '2次/天', '口服/外用', 9.50, '5mg*12片', '盒');
INSERT INTO `drug` VALUES (2078, '000218', '', 'GLMNP', '格列美脲片', '格列美脲片', '2mg*10片', NULL, '扬子江药业集团广州海瑞药业', '盒', 1, 13.80, 200, '', '3天', '2次/天', '口服/外用', 13.80, '2mg*10片', '盒');
INSERT INTO `drug` VALUES (2079, '000219', '', 'GYSTMXFP', '枸橼酸他莫昔芬片', '枸橼酸他莫昔芬片', '10mg*60片', NULL, '扬子江药业集团有限公司', '瓶', 1, 31.00, 10, '', '3天', '2次/天', '口服/外用', 31.00, '10mg*60片', '瓶');
INSERT INTO `drug` VALUES (2080, '000220', '', 'MLSYNPLP', '马来酸依那普利片', '马来酸依那普利片', '5mg*16片', NULL, '扬子江药业集团江苏制药股份有限公司', '盒', 1, 9.80, 130, '', '3天', '2次/天', '口服/外用', 9.80, '5mg*16片', '盒');
INSERT INTO `drug` VALUES (2081, '000221', '', 'MLSYNPLP', '马来酸依那普利片', '马来酸依那普利片', '10mg*16片', NULL, '扬子江药业集团江苏制药', '盒', 1, 17.20, 999, '', '3天', '2次/天', '口服/外用', 17.20, '10mg*16片', '盒');
INSERT INTO `drug` VALUES (2082, '000222', '', 'XFTDJN', '辛伐他丁胶囊', '辛伐他丁胶囊', '10mg*10粒', NULL, '扬子江药业集团四川海蓉药业', '盒', 1, 11.00, 200, '', '3天', '2次/天', '口服/外用', 11.00, '10mg*10粒', '盒');
INSERT INTO `drug` VALUES (2083, '000223', '', 'EBSTP', '厄贝沙坦片', '厄贝沙坦片', '75mg*12片', NULL, '扬子江药业北京海燕药业', '盒', 1, 16.50, 200, '', '3天', '2次/天', '口服/外用', 16.50, '75mg*12片', '盒');
INSERT INTO `drug` VALUES (2084, '000224', '', 'BHSALDPP', '苯磺酸氨氯地平片', '苯磺酸氨氯地平片', '5mg*7片', NULL, '扬子江药业集团上海海尼药业', '盒', 1, 12.20, 198, '', '3天', '2次/天', '口服/外用', 12.20, '5mg*7片', '盒');
INSERT INTO `drug` VALUES (2085, '000225', '', 'XEAFWAKL', '小儿氨酚烷胺颗粒', '优卡丹', '6g*10袋', NULL, '江西铜鼓仁和制药', '盒', 1, 7.50, 200, '', '3天', '2次/天', '口服/外用', 7.50, '6g*10袋', '盒');
INSERT INTO `drug` VALUES (2086, '000226', '', 'LSBBPLP', '磷酸苯丙哌林片', '磷酸苯丙哌林片', '20mg24片', NULL, '上海皇象铁力蓝天制药有限公司', '盒', 1, 1.50, 190, '', '3天', '2次/天', '口服/外用', 1.50, '20mg24片', '盒');
INSERT INTO `drug` VALUES (2087, '000227', '', 'RFZKJN', '润肺止咳胶囊', '润肺止咳胶囊', '0.35g*24粒', NULL, '青海鲁抗大地制药', '盒', 1, 9.50, 50, '', '3天', '2次/天', '口服/外用', 9.50, '0.35g*24粒', '盒');
INSERT INTO `drug` VALUES (2088, '000228', '', 'JSSMTLEP', '酒石酸美托洛尔片', '酒石酸美托洛尔片', '25mg*24片', NULL, '山东烟台巨先药业有限公司', '盒', 1, 6.50, 400, '', '3天', '2次/天', '口服/外用', 6.50, '25mg*24片', '盒');
INSERT INTO `drug` VALUES (2089, '000229', '', 'TBLJN', '通便灵胶囊', '通便灵胶囊', '0.25g*20粒', NULL, '北京同仁堂科技发展股份有限公司制药厂', '盒', 1, 14.00, 0, '', '3天', '2次/天', '口服/外用', 14.00, '0.25g*20粒', '盒');
INSERT INTO `drug` VALUES (2090, '000230', '', 'KLMSFSP', '克拉霉素分散片', '克拉霉素分散片', '0.25g*12片', NULL, '扬子江药业集团有限公司', '盒', 1, 0.00, 0, '', '3天', '2次/天', '口服/外用', 0.00, '0.25g*12片', '盒');
INSERT INTO `drug` VALUES (2091, '000231', '', 'YXLXMSP', '乙酰螺旋霉素片', '乙酰螺旋霉素片', '0.1g*24片', NULL, '石家庄华新制药厂', '盒', 1, 1.50, 30, '', '3天', '2次/天', '口服/外用', 1.50, '0.1g*24片', '盒');
INSERT INTO `drug` VALUES (2092, '000232', '', 'JWXSPET', '健胃消食片（儿童）', '健胃消食片（儿童）', '0.5g*36片', NULL, '江中药业股份有限公司', '盒', 1, 7.00, 360, '', '3天', '2次/天', '口服/外用', 7.00, '0.5g*36片', '盒');
INSERT INTO `drug` VALUES (2093, '000233', '', 'JWXSPCR', '健胃消食片（成人）', '健胃消食片（成人）', '0.8g*32片', NULL, '江中药业股份有限公司', '盒', 1, 7.00, 299, '', '3天', '2次/天', '口服/外用', 7.00, '0.8g*32片', '盒');
INSERT INTO `drug` VALUES (2094, '000234', '', 'ZZ', '栀子', '栀子', '个   1000克', NULL, '安国市万联中药饮片有限公司', '包', 1, 0.00, 2000, '', '3天', '2次/天', '口服/外用', 0.00, '个   1000克', '包');
INSERT INTO `drug` VALUES (2095, '000235', '', 'SZ', '山楂', '山楂', '片   1000克', NULL, '安国市万联中药饮片有限公司', '包', 1, 0.00, 3000, '', '3天', '2次/天', '口服/外用', 0.00, '片   1000克', '包');
INSERT INTO `drug` VALUES (2096, '000236', '', 'HQ', '黄芩', '黄芩', '片  1000克', NULL, '安国市万联中药饮片有限公司', '包', 1, 0.00, 3000, '', '3天', '2次/天', '口服/外用', 0.00, '片  1000克', '包');
INSERT INTO `drug` VALUES (2097, '000237', '', 'SY', '山药', '山药', '片   1000克', NULL, '安国市万联中药饮片有限公司', '克', 1, 0.00, 3000, '', '3天', '2次/天', '口服/外用', 0.00, '片   1000克', '克');
INSERT INTO `drug` VALUES (2098, '000238', '', 'FS', '佛手', '佛手', '片   1000克', NULL, '安国市万联中药饮片有限公司', '包', 1, 0.00, 2000, '', '3天', '2次/天', '口服/外用', 0.00, '片   1000克', '包');
INSERT INTO `drug` VALUES (2099, '000239', '', 'BB', '百部', '百部', '片  1000克', NULL, '安国市万联中药饮片有限公司', '克', 1, 0.00, 3000, '', '3天', '2次/天', '口服/外用', 0.00, '片  1000克', '克');
INSERT INTO `drug` VALUES (2100, '000240', '', 'CMT', '川木通', '川木通', '片   1000克', NULL, '安国市万联中药饮片有限公司', '克', 1, 0.00, 2000, '', '3天', '2次/天', '口服/外用', 0.00, '片   1000克', '克');
INSERT INTO `drug` VALUES (2101, '000241', '', 'BS', '白术', '白术', '片  1000克', NULL, '安国市万联中药饮片有限公司', '包', 1, 0.10, 2882, '', '3天', '2次/天', '口服/外用', 0.10, '片  1000克', '包');
INSERT INTO `drug` VALUES (2102, '000242', '', 'TFL', '土茯苓', '土茯苓', '片   1000克', NULL, '安国市万联中药饮片有限公司', '克', 1, 0.00, 2000, '', '3天', '2次/天', '口服/外用', 0.00, '片   1000克', '克');
INSERT INTO `drug` VALUES (2103, '000243', '', 'DS', '党参', '党参', '段  1000克', NULL, '安国市万联中药饮片有限公司', '包', 1, 0.00, 3000, '', '3天', '2次/天', '口服/外用', 0.00, '段  1000克', '包');
INSERT INTO `drug` VALUES (2104, '000244', '', 'ZSY', '紫苏叶', '紫苏叶', '个   1000克', NULL, '安国市万联中药饮片有限公司', '克', 1, 0.00, 2000, '', '3天', '2次/天', '口服/外用', 0.00, '个   1000克', '克');
INSERT INTO `drug` VALUES (2105, '000245', '', 'CS', '赤芍', '赤芍', '片   1000克', NULL, '安国市万联中药饮片有限公司', '克', 1, 0.00, 3000, '', '3天', '2次/天', '口服/外用', 0.00, '片   1000克', '克');
INSERT INTO `drug` VALUES (2106, '000246', '', 'CS', '测试', '测试', '10片', NULL, '国际药业', '盒', 1, 20.00, 0, '', '3天', '2次/天', '口服/外用', 20.00, '10片', '盒');
INSERT INTO `drug` VALUES (2107, '000247', '', 'CS1', '测试1', '测试1', '20片', NULL, '国际药业', '盒', 1, 25.00, 0, '', '3天', '2次/天', '口服/外用', 25.00, '20片', '盒');
INSERT INTO `drug` VALUES (2108, '000248', '', 'CS2', '测试2', '测试2', '20片', NULL, '国际药业', '盒', 1, 10.00, 0, '', '3天', '2次/天', '口服/外用', 10.00, '20片', '盒');

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编码',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院名称',
  `comment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hospital
-- ----------------------------
INSERT INTO `hospital` VALUES (1, 'hospital', '管道局医院', '医院名称', 1);

-- ----------------------------
-- Table structure for job_cron_expression
-- ----------------------------
DROP TABLE IF EXISTS `job_cron_expression`;
CREATE TABLE `job_cron_expression`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务运行时间表达式表自增ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务运行时间表达式名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务运行时间表达式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务运行时间表达式表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_cron_expression
-- ----------------------------
INSERT INTO `job_cron_expression` VALUES (1, '每分钟执行', '0 */1 * * * ?');
INSERT INTO `job_cron_expression` VALUES (2, '每天（00:00点）执行', '0 0 0 * * ?');

-- ----------------------------
-- Table structure for log_receive_patient
-- ----------------------------
DROP TABLE IF EXISTS `log_receive_patient`;
CREATE TABLE `log_receive_patient`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据发送方IP地址',
  `data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '接收到的数据（JSON格式）',
  `patient_id` bigint(20) NULL DEFAULT NULL COMMENT '患者ID. FK( ref patient:id)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_receive_patient
-- ----------------------------
INSERT INTO `log_receive_patient` VALUES (1, '2018-03-01 19:57:53', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (2, '2018-03-01 20:51:47', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (3, '2018-03-01 23:45:58', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (4, '2018-03-02 08:41:10', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (5, '2018-03-02 09:31:21', '192.168.125.127', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (6, '2018-03-02 09:31:29', '192.168.125.127', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (7, '2018-03-02 09:51:15', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (8, '2018-03-02 10:02:31', '192.168.125.127', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (9, '2018-03-02 10:09:43', '192.168.125.127', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"无\",\"patientgender\":\"男\",\"patientid\":\"234\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (10, '2018-03-02 11:20:19', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (11, '2018-03-02 12:13:30', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (12, '2018-03-02 13:26:02', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (13, '2018-03-02 13:49:53', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (14, '2018-03-02 14:08:08', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (15, '2018-03-02 14:20:51', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (16, '2018-06-13 10:19:09', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (17, '2018-06-14 10:56:03', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (18, '2018-06-15 12:07:24', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (19, '2018-06-19 10:04:49', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (20, '2018-06-20 11:04:36', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (21, '2018-06-20 11:27:02', '192.168.1.108', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (22, '2018-06-20 11:27:11', '192.168.1.108', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (23, '2018-06-20 11:27:16', '192.168.1.108', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (24, '2018-06-20 11:27:21', '192.168.1.108', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (25, '2018-06-20 11:27:33', '192.168.1.108', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (26, '2018-06-20 17:01:53', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (27, '2018-06-20 17:01:54', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (28, '2018-06-20 17:01:54', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (29, '2018-06-20 17:01:58', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (30, '2018-06-20 17:01:58', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (31, '2018-06-20 17:02:17', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (32, '2018-06-20 17:02:18', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (33, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (34, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (35, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (36, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (37, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (38, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (39, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (40, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (41, '2018-06-20 17:02:19', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (42, '2018-06-20 17:04:15', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (43, '2018-06-20 17:04:26', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (44, '2018-06-20 17:04:52', '192.168.1.108', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (45, '2018-06-20 17:05:32', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (46, '2018-06-20 17:05:38', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (47, '2018-06-20 17:05:47', '127.0.0.1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (48, '2018-06-20 17:05:52', '127.0.0.1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (49, '2018-06-20 17:06:22', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (50, '2018-06-20 18:02:22', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (51, '2018-06-20 18:02:34', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (52, '2018-06-20 18:02:49', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (53, '2018-06-20 18:03:16', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (54, '2018-06-20 18:03:33', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (55, '2018-06-20 18:03:41', '0:0:0:0:0:0:0:1', '{}', NULL);
INSERT INTO `log_receive_patient` VALUES (56, '2018-06-20 18:03:47', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (57, '2018-06-21 11:10:33', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (58, '2018-06-21 11:10:47', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (59, '2018-06-21 11:10:57', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (60, '2018-06-25 15:37:17', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (61, '2018-06-26 12:06:35', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (62, '2018-09-21 14:52:18', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (63, '2018-09-21 15:05:06', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);
INSERT INTO `log_receive_patient` VALUES (64, '2019-12-23 13:24:35', '0:0:0:0:0:0:0:1', '{\"departmentid\":\"15\",\"departmentname\":\"内科\",\"diagnosisresult\":\"消化不良,重感冒\",\"doctorid\":\"12\",\"doctorname\":\"李四\",\"patientcrno\":\"blh12345\",\"patientgender\":\"男\",\"patientid\":\"2345\",\"patientname\":\"张三\",\"patientold\":\"50\",\"patientrn\":\"djh123456789\"}', NULL);

-- ----------------------------
-- Table structure for log_send_presc
-- ----------------------------
DROP TABLE IF EXISTS `log_send_presc`;
CREATE TABLE `log_send_presc`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `data` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '所发送的数据',
  `status` int(4) NULL DEFAULT NULL COMMENT '发送状态。1:成功；2：失败；3：连接失败',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据接收方',
  `presc_id` bigint(20) NULL DEFAULT NULL COMMENT '处方ID(FK ref  prescription:id)',
  `msg` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_send_presc
-- ----------------------------
INSERT INTO `log_send_presc` VALUES (1, '2018-06-20 16:54:00', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"奥美拉唑肠溶胶囊\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000031\"},{\"drug_name\":\"保和丸\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000008\"},{\"drug_name\":\"多潘立酮片\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000040\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2018062000001\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);
INSERT INTO `log_send_presc` VALUES (2, '2018-06-20 16:55:30', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"奥美拉唑肠溶胶囊\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000031\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2018062000002\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);
INSERT INTO `log_send_presc` VALUES (3, '2018-06-20 17:05:09', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"奥美拉唑肠溶胶囊\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000031\"},{\"drug_name\":\"保和丸\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000008\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2018062000003\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);
INSERT INTO `log_send_presc` VALUES (4, '2018-06-25 19:09:22', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"奥美拉唑肠溶胶囊\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000031\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2018062500001\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);
INSERT INTO `log_send_presc` VALUES (5, '2018-06-25 19:09:39', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"奥美拉唑肠溶胶囊\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000031\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2018062500002\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);
INSERT INTO `log_send_presc` VALUES (6, '2018-06-25 19:10:20', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"奥美拉唑肠溶胶囊\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000031\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2018062500003\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);
INSERT INTO `log_send_presc` VALUES (7, '2018-09-21 15:09:26', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"感冒灵颗粒\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000107\"},{\"drug_name\":\"感冒清热颗粒\",\"quantity\":\"3\",\"drug_code\":\"\",\"id\":\"000214\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2018092100001\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);
INSERT INTO `log_send_presc` VALUES (8, '2019-12-23 13:25:43', '{\"data\":{\"doctor\":{\"name\":\"李四\",\"id\":\"12\"},\"prescription\":[{\"drug_name\":\"感冒灵颗粒\",\"quantity\":\"1\",\"drug_code\":\"\",\"id\":\"000107\"}],\"patient\":{\"gender\":\"男\",\"old\":\"50\",\"name\":\"张三\",\"cr_no\":\"blh12345\",\"id\":\"2345\",\"presc_no\":\"O2019122300001\",\"rn\":\"djh123456789\"},\"diagnosis\":[{\"disease\":\"消化不良\",\"id\":\"8\"},{\"disease\":\"重感冒\",\"id\":\"9\"}],\"department\":{\"name\":\"内科\",\"id\":\"03\"}},\"func\":\"presc_patient_nmi\",\"version\":\"1.0\",\"token\":\"HDERP\"}', 3, 'http://127.0.0.1:8080/rx-web/prescapi', NULL, NULL);

-- ----------------------------
-- Table structure for log_sync_drug
-- ----------------------------
DROP TABLE IF EXISTS `log_sync_drug`;
CREATE TABLE `log_sync_drug`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `created_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `status` int(4) NULL DEFAULT NULL COMMENT '同步状态。1:成功；2：文件格式错误；3：连接失败',
  `errormsg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误信息',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据源地址',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同步文件保存目录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_sync_drug
-- ----------------------------
INSERT INTO `log_sync_drug` VALUES (1, '2018-03-02 11:52:01', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180302\\2018030211520081444461.txt');
INSERT INTO `log_sync_drug` VALUES (2, '2018-03-02 12:19:36', 1, '手动初始化药品信息成功：成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180302\\2018030212193575757497.txt');
INSERT INTO `log_sync_drug` VALUES (3, '2018-03-03 00:00:02', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180303\\2018030300000186852173.txt');
INSERT INTO `log_sync_drug` VALUES (4, '2018-06-18 11:30:56', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180618\\2018061811300068919070.txt');
INSERT INTO `log_sync_drug` VALUES (5, '2018-06-18 11:47:29', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180618\\2018061811470063523880.txt');
INSERT INTO `log_sync_drug` VALUES (6, '2018-06-19 12:39:01', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180619\\2018061912390092870121.txt');
INSERT INTO `log_sync_drug` VALUES (7, '2018-06-19 14:42:01', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180619\\2018061914420103173961.txt');
INSERT INTO `log_sync_drug` VALUES (8, '2018-06-19 14:46:18', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180619\\2018061914461753589681.txt');
INSERT INTO `log_sync_drug` VALUES (9, '2018-06-19 14:46:18', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180619\\2018061914461753784550.txt');
INSERT INTO `log_sync_drug` VALUES (10, '2018-06-19 15:56:41', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180619\\2018061915564096165452.txt');
INSERT INTO `log_sync_drug` VALUES (11, '2018-06-19 17:24:05', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180619\\2018061917240537233237.txt');
INSERT INTO `log_sync_drug` VALUES (12, '2018-06-19 17:27:04', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180619\\2018061917270373469382.txt');
INSERT INTO `log_sync_drug` VALUES (13, '2018-06-20 17:39:32', 1, '手动初始化药品信息错误：保存到数据库异常', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180620\\2018062017213739316164.txt');
INSERT INTO `log_sync_drug` VALUES (14, '2018-06-21 10:27:02', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180621\\2018062110270162070962.txt');
INSERT INTO `log_sync_drug` VALUES (15, '2018-06-21 10:39:01', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180621\\2018062110390080553260.txt');
INSERT INTO `log_sync_drug` VALUES (16, '2018-06-21 10:44:00', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180621\\2018062110440007912115.txt');
INSERT INTO `log_sync_drug` VALUES (17, '2018-06-21 11:08:01', 13, '保存数据库异常，等待重新保存。。。', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180621\\2018062111080075557735.txt');
INSERT INTO `log_sync_drug` VALUES (18, '2018-06-21 11:09:00', 1, '成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.0.M13/webapps\\upload\\drug file download\\20180621\\2018062111090014068326.txt');
INSERT INTO `log_sync_drug` VALUES (19, '2018-09-21 14:53:38', 1, '手动初始化药品信息成功：成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.10/webapps\\upload\\drug file download\\20180921\\2018092114533828844482.txt');
INSERT INTO `log_sync_drug` VALUES (20, '2018-09-21 15:00:26', 1, '手动初始化药品信息成功：成功', 'http://127.0.0.1/rx-back/api/demo', 'D:/Tomcat/apache-tomcat-9.0.10/webapps\\upload\\drug file download\\20180921\\2018092115002594251184.txt');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单自增ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标样式',
  `menu_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单描述',
  `menu_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父id(菜单为树结构)',
  `sort_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '用户权限管理', 'glyphicon glyphicon-cog', '用户权限配置', NULL, 0, 0, 1);
INSERT INTO `menu` VALUES (2, '用户管理', 'glyphicon glyphicon-user', '用户管理', 'back/user/selectItems?pagehelperFun=clickPageBtnRequestFun', 1, 0, 1);
INSERT INTO `menu` VALUES (3, '角色管理', 'glyphicon glyphicon-th', '角色管理', 'back/role/selectItems?pagehelperFun=clickPageBtnRequestFun', 1, 0, 1);
INSERT INTO `menu` VALUES (4, '菜单管理', 'glyphicon glyphicon-th-list', '菜单管理', 'back/menu/selectItems', 1, 0, 1);
INSERT INTO `menu` VALUES (5, '日志管理', 'glyphicon glyphicon-pencil', '日志管理', '', 0, 0, 1);
INSERT INTO `menu` VALUES (6, '用药信息维护', 'glyphicon glyphicon-book', '用药信息维护', '', 0, 0, 1);
INSERT INTO `menu` VALUES (7, '用药方式', 'glyphicon glyphicon-book', '用药方式', 'back/dict-mode/selectItems?pagehelperFun=clickPageBtnRequestFun', 6, 0, 1);
INSERT INTO `menu` VALUES (8, '用药次数', 'glyphicon glyphicon-book', '用药次数', 'back/dict-times/selectItems?pagehelperFun=clickPageBtnRequestFun', 6, 0, 1);
INSERT INTO `menu` VALUES (9, '用药剂量单位', 'glyphicon glyphicon-book', '用药剂量单位', 'back/dict-dose-unit/selectItems?pagehelperFun=clickPageBtnRequestFun', 6, 0, 1);
INSERT INTO `menu` VALUES (10, '发送处方日志信息', 'glyphicon glyphicon-pencil', '发送处方日志信息', 'back/log-send-presc/select-items?pagehelperFun=clickPageBtnRequestFun', 5, 0, 1);
INSERT INTO `menu` VALUES (11, '接收患者信息日志', 'glyphicon glyphicon-pencil', '接收患者信息日志', 'back/log-receive-patient/select-items?pagehelperFun=clickPageBtnRequestFun', 5, 0, 1);
INSERT INTO `menu` VALUES (12, '同步药品信息日志', 'glyphicon glyphicon-pencil', '同步药品信息日志', 'back/log-sync-drug/select-items?pagehelperFun=clickPageBtnRequestFun', 5, 0, 1);
INSERT INTO `menu` VALUES (13, '用药疗程', 'glyphicon glyphicon-book', '用药疗程', 'back/dict-days/selectItems?pagehelperFun=clickPageBtnRequestFun', 6, 0, 1);
INSERT INTO `menu` VALUES (14, '基本信息维护', 'glyphicon glyphicon-pencil', '基本信息维护', '', 0, 0, 1);
INSERT INTO `menu` VALUES (15, '接口链接维护', 'glyphicon glyphicon-pencil', '接口链接维护', 'back/dict-common/select-items?pagehelperFun=clickPageBtnRequestFun', 14, 0, 1);
INSERT INTO `menu` VALUES (16, '药房信息维护', 'glyphicon glyphicon-pencil', '药房信息维护', 'back/dispensary/select-items?pagehelperFun=clickPageBtnRequestFun', 14, 0, 1);
INSERT INTO `menu` VALUES (17, '医院信息维护', 'glyphicon glyphicon-pencil', '医院信息维护', 'back/hospital/select-items?pagehelperFun=clickPageBtnRequestFun', 14, 0, 1);
INSERT INTO `menu` VALUES (18, '计划任务', 'glyphicon glyphicon-tasks', '计划任务', '', 0, 0, 1);
INSERT INTO `menu` VALUES (19, '计划任务', 'glyphicon glyphicon-tasks', '计划任务', 'back/quartz/selectItems?pagehelperFun=clickPageBtnRequestFun', 18, 0, 1);
INSERT INTO `menu` VALUES (20, '任务运行时间表达式', 'glyphicon glyphicon-tasks', '任务运行时间表达式', 'back/job-cron-expression/selectItems?pagehelperFun=clickPageBtnRequestFun', 18, 0, 1);
INSERT INTO `menu` VALUES (21, '药品信息维护', 'glyphicon glyphicon-leaf', '药品信息维护', '', 0, 0, 1);
INSERT INTO `menu` VALUES (22, '药品信息维护', 'glyphicon glyphicon-leaf', '药品信息维护', 'back/drug/selectItems?pagehelperFun=clickPageBtnRequestFun', 21, 0, 1);

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `old_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自东华传送过来的原患者ID(就诊号)',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者姓名',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '患者性别。1:男；2：女',
  `old` int(11) NULL DEFAULT NULL COMMENT '患者年龄',
  `cr_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '病历号',
  `rn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (2, '01', '张三', 1, 75, '123456789', NULL);
INSERT INTO `patient` VALUES (3, '123', '李四', 1, 50, '123456', '123456789');
INSERT INTO `patient` VALUES (4, '234', '麻五', 1, 50, '无', '123456789');
INSERT INTO `patient` VALUES (5, '2345', '张三', 1, 50, 'blh12345', 'djh123456789');

-- ----------------------------
-- Table structure for presc_drug
-- ----------------------------
DROP TABLE IF EXISTS `presc_drug`;
CREATE TABLE `presc_drug`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `presc_id` bigint(20) NULL DEFAULT NULL COMMENT '处方ID(FK ref prescription:id)',
  `drug_id` bigint(20) NULL DEFAULT NULL COMMENT '药品ID( FK ref drug:id)',
  `quantity` int(4) NULL DEFAULT NULL,
  `wareid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `barcode` varchar(140) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `warename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `waresimname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `warespec` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prod_addr` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `producer` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wareunit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `saleprice` decimal(12, 4) NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '加入日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 210 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of presc_drug
-- ----------------------------
INSERT INTO `presc_drug` VALUES (1, 1, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 06:11:55');
INSERT INTO `presc_drug` VALUES (2, 2, 2, 6, '2', '2', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-23 06:11:55');
INSERT INTO `presc_drug` VALUES (3, 3, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 06:49:29');
INSERT INTO `presc_drug` VALUES (4, 4, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 06:51:46');
INSERT INTO `presc_drug` VALUES (5, 5, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 06:55:41');
INSERT INTO `presc_drug` VALUES (6, 6, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 06:59:18');
INSERT INTO `presc_drug` VALUES (7, 7, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 08:13:16');
INSERT INTO `presc_drug` VALUES (8, 8, 2, 6, '2', '2', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-23 08:13:17');
INSERT INTO `presc_drug` VALUES (9, 9, 1, 1, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 08:28:47');
INSERT INTO `presc_drug` VALUES (10, 10, 2, 2, '2', '2', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-23 08:28:47');
INSERT INTO `presc_drug` VALUES (11, 11, 3, 3, '3', '3', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-23 08:28:48');
INSERT INTO `presc_drug` VALUES (12, 12, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 09:16:24');
INSERT INTO `presc_drug` VALUES (13, 13, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 09:23:37');
INSERT INTO `presc_drug` VALUES (14, 14, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 10:07:02');
INSERT INTO `presc_drug` VALUES (15, 15, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 10:09:53');
INSERT INTO `presc_drug` VALUES (16, 16, 2, 6, '2', '2', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-23 10:09:53');
INSERT INTO `presc_drug` VALUES (17, 17, 3, 6, '3', '3', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-23 10:09:53');
INSERT INTO `presc_drug` VALUES (18, 18, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-23 10:10:41');
INSERT INTO `presc_drug` VALUES (19, 19, 2, 6, '2', '2', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-23 10:10:41');
INSERT INTO `presc_drug` VALUES (20, 20, 3, 6, '3', '3', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-23 10:10:41');
INSERT INTO `presc_drug` VALUES (21, 21, 4, 8, '4', '4', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-23 10:10:41');
INSERT INTO `presc_drug` VALUES (22, 22, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 00:58:19');
INSERT INTO `presc_drug` VALUES (23, 23, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 15:10:08');
INSERT INTO `presc_drug` VALUES (24, 24, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 15:15:06');
INSERT INTO `presc_drug` VALUES (25, 25, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 17:47:16');
INSERT INTO `presc_drug` VALUES (26, 26, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 18:22:36');
INSERT INTO `presc_drug` VALUES (27, 27, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 21:02:55');
INSERT INTO `presc_drug` VALUES (28, 28, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 21:33:58');
INSERT INTO `presc_drug` VALUES (29, 29, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 21:50:38');
INSERT INTO `presc_drug` VALUES (30, 30, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 22:12:38');
INSERT INTO `presc_drug` VALUES (31, 31, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 22:18:36');
INSERT INTO `presc_drug` VALUES (32, 32, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 22:28:05');
INSERT INTO `presc_drug` VALUES (33, 33, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 22:29:53');
INSERT INTO `presc_drug` VALUES (34, 34, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 23:01:53');
INSERT INTO `presc_drug` VALUES (35, 35, 1, 6, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-24 23:07:07');
INSERT INTO `presc_drug` VALUES (36, 36, 1, 5, '1', '1', '感冒清', '感冒清', '1.0g*20', '石家庄', '华润制药', '盒', 12.2500, '2018-01-25 13:25:46');
INSERT INTO `presc_drug` VALUES (37, 37, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-26 09:41:30');
INSERT INTO `presc_drug` VALUES (38, 38, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-26 09:41:30');
INSERT INTO `presc_drug` VALUES (39, 39, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 19:15:35');
INSERT INTO `presc_drug` VALUES (40, 39, 2, 7, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-29 19:15:36');
INSERT INTO `presc_drug` VALUES (41, 40, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 19:18:12');
INSERT INTO `presc_drug` VALUES (42, 40, 2, 7, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-29 19:18:12');
INSERT INTO `presc_drug` VALUES (43, 41, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 19:39:51');
INSERT INTO `presc_drug` VALUES (44, 42, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 19:41:27');
INSERT INTO `presc_drug` VALUES (45, 43, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 19:58:44');
INSERT INTO `presc_drug` VALUES (46, 44, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 20:43:48');
INSERT INTO `presc_drug` VALUES (47, 45, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 20:48:39');
INSERT INTO `presc_drug` VALUES (48, 46, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 20:51:31');
INSERT INTO `presc_drug` VALUES (49, 47, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 20:57:35');
INSERT INTO `presc_drug` VALUES (50, 48, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 21:16:20');
INSERT INTO `presc_drug` VALUES (51, 49, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 21:25:45');
INSERT INTO `presc_drug` VALUES (52, 50, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 21:30:56');
INSERT INTO `presc_drug` VALUES (53, 51, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 21:48:11');
INSERT INTO `presc_drug` VALUES (54, 52, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 21:52:47');
INSERT INTO `presc_drug` VALUES (55, 53, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-29 22:39:24');
INSERT INTO `presc_drug` VALUES (56, 54, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-30 11:22:17');
INSERT INTO `presc_drug` VALUES (57, 54, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-30 11:22:18');
INSERT INTO `presc_drug` VALUES (58, 54, 3, 8, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-30 11:22:18');
INSERT INTO `presc_drug` VALUES (59, 54, 4, 9, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 11:22:18');
INSERT INTO `presc_drug` VALUES (60, 54, 5, 7, '555', '555', '清热颗粒', '清热颗粒', '3.0g', '石家庄', '华润制药', '袋', 20.0000, '2018-01-30 11:22:18');
INSERT INTO `presc_drug` VALUES (61, 55, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-30 11:23:42');
INSERT INTO `presc_drug` VALUES (62, 55, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-30 11:23:42');
INSERT INTO `presc_drug` VALUES (63, 55, 3, 8, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-30 11:23:42');
INSERT INTO `presc_drug` VALUES (64, 55, 4, 9, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 11:23:42');
INSERT INTO `presc_drug` VALUES (65, 55, 5, 7, '555', '555', '清热颗粒', '清热颗粒', '3.0g', '石家庄', '华润制药', '袋', 20.0000, '2018-01-30 11:23:42');
INSERT INTO `presc_drug` VALUES (66, 56, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-30 11:23:48');
INSERT INTO `presc_drug` VALUES (67, 56, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-30 11:23:48');
INSERT INTO `presc_drug` VALUES (68, 56, 3, 8, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-30 11:23:48');
INSERT INTO `presc_drug` VALUES (69, 56, 4, 9, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 11:23:48');
INSERT INTO `presc_drug` VALUES (70, 56, 5, 7, '555', '555', '清热颗粒', '清热颗粒', '3.0g', '石家庄', '华润制药', '袋', 20.0000, '2018-01-30 11:23:48');
INSERT INTO `presc_drug` VALUES (71, 57, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-30 11:23:51');
INSERT INTO `presc_drug` VALUES (72, 57, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-30 11:23:51');
INSERT INTO `presc_drug` VALUES (73, 57, 3, 8, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-30 11:23:51');
INSERT INTO `presc_drug` VALUES (74, 57, 4, 9, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 11:23:51');
INSERT INTO `presc_drug` VALUES (75, 57, 5, 7, '555', '555', '清热颗粒', '清热颗粒', '3.0g', '石家庄', '华润制药', '袋', 20.0000, '2018-01-30 11:23:51');
INSERT INTO `presc_drug` VALUES (76, 58, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-30 14:37:48');
INSERT INTO `presc_drug` VALUES (77, 59, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-30 14:41:17');
INSERT INTO `presc_drug` VALUES (78, 59, 6, 5, '666', '666', '感冒冲剂', '感冒冲剂', '5.0g/袋', '石家庄', '华北制药', '袋', 10.0000, '2018-01-30 14:41:17');
INSERT INTO `presc_drug` VALUES (79, 60, 5, 5, '555', '555', '清热颗粒', '清热颗粒', '3.0g', '石家庄', '华润制药', '袋', 20.0000, '2018-01-30 14:51:46');
INSERT INTO `presc_drug` VALUES (80, 61, 5, 5, '555', '555', '清热颗粒', '清热颗粒', '3.0g', '石家庄', '华润制药', '袋', 20.0000, '2018-01-30 14:54:41');
INSERT INTO `presc_drug` VALUES (81, 62, 5, 5, '555', '555', '清热颗粒', '清热颗粒', '3.0g', '石家庄', '华润制药', '袋', 20.0000, '2018-01-30 14:55:10');
INSERT INTO `presc_drug` VALUES (82, 63, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:03:35');
INSERT INTO `presc_drug` VALUES (83, 64, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:04:43');
INSERT INTO `presc_drug` VALUES (84, 65, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:05:52');
INSERT INTO `presc_drug` VALUES (85, 66, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:05:56');
INSERT INTO `presc_drug` VALUES (86, 67, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:06:45');
INSERT INTO `presc_drug` VALUES (87, 68, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:06:57');
INSERT INTO `presc_drug` VALUES (88, 69, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:07:07');
INSERT INTO `presc_drug` VALUES (89, 70, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:09:37');
INSERT INTO `presc_drug` VALUES (90, 71, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:09:41');
INSERT INTO `presc_drug` VALUES (91, 72, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-30 15:09:45');
INSERT INTO `presc_drug` VALUES (92, 73, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-30 15:24:11');
INSERT INTO `presc_drug` VALUES (93, 74, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 08:17:59');
INSERT INTO `presc_drug` VALUES (94, 74, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-31 08:17:59');
INSERT INTO `presc_drug` VALUES (95, 74, 3, 7, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-31 08:17:59');
INSERT INTO `presc_drug` VALUES (96, 74, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-31 08:17:59');
INSERT INTO `presc_drug` VALUES (97, 74, 6, 3, '666', '666', '感冒冲剂', '感冒冲剂', '5.0g/袋', '石家庄', '华北制药', '袋', 10.0000, '2018-01-31 08:17:59');
INSERT INTO `presc_drug` VALUES (98, 75, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 08:21:19');
INSERT INTO `presc_drug` VALUES (99, 75, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-31 08:21:19');
INSERT INTO `presc_drug` VALUES (100, 75, 3, 7, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-31 08:21:19');
INSERT INTO `presc_drug` VALUES (101, 75, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-31 08:21:19');
INSERT INTO `presc_drug` VALUES (102, 75, 6, 3, '666', '666', '感冒冲剂', '感冒冲剂', '5.0g/袋', '石家庄', '华北制药', '袋', 10.0000, '2018-01-31 08:21:19');
INSERT INTO `presc_drug` VALUES (103, 76, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 08:21:51');
INSERT INTO `presc_drug` VALUES (104, 76, 2, 6, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-01-31 08:21:51');
INSERT INTO `presc_drug` VALUES (105, 76, 3, 7, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-01-31 08:21:51');
INSERT INTO `presc_drug` VALUES (106, 76, 4, 6, '444', '456', '感冒胶囊', '感冒胶囊', '2.0g*20', '石家庄', '华润制药', '盒', 15.5000, '2018-01-31 08:21:51');
INSERT INTO `presc_drug` VALUES (107, 76, 6, 3, '666', '666', '感冒冲剂', '感冒冲剂', '5.0g/袋', '石家庄', '华北制药', '袋', 10.0000, '2018-01-31 08:21:51');
INSERT INTO `presc_drug` VALUES (108, 77, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 08:57:27');
INSERT INTO `presc_drug` VALUES (109, 78, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:10:10');
INSERT INTO `presc_drug` VALUES (110, 79, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:12:59');
INSERT INTO `presc_drug` VALUES (111, 80, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:14:33');
INSERT INTO `presc_drug` VALUES (112, 81, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:16:33');
INSERT INTO `presc_drug` VALUES (113, 82, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:18:05');
INSERT INTO `presc_drug` VALUES (114, 83, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:22:47');
INSERT INTO `presc_drug` VALUES (115, 84, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:29:07');
INSERT INTO `presc_drug` VALUES (116, 85, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 09:30:41');
INSERT INTO `presc_drug` VALUES (117, 86, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 11:10:50');
INSERT INTO `presc_drug` VALUES (118, 87, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-01-31 17:46:31');
INSERT INTO `presc_drug` VALUES (119, 88, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 02:11:38');
INSERT INTO `presc_drug` VALUES (120, 89, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 02:23:47');
INSERT INTO `presc_drug` VALUES (121, 90, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 02:30:33');
INSERT INTO `presc_drug` VALUES (122, 91, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 02:42:58');
INSERT INTO `presc_drug` VALUES (123, 92, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 02:44:53');
INSERT INTO `presc_drug` VALUES (124, 93, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 02:46:16');
INSERT INTO `presc_drug` VALUES (125, 94, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 03:03:51');
INSERT INTO `presc_drug` VALUES (126, 95, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 03:21:24');
INSERT INTO `presc_drug` VALUES (127, 96, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 03:24:48');
INSERT INTO `presc_drug` VALUES (128, 97, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 03:38:23');
INSERT INTO `presc_drug` VALUES (129, 98, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 03:49:44');
INSERT INTO `presc_drug` VALUES (130, 99, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 03:51:36');
INSERT INTO `presc_drug` VALUES (131, 100, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 04:04:16');
INSERT INTO `presc_drug` VALUES (132, 101, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 04:04:22');
INSERT INTO `presc_drug` VALUES (133, 102, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 04:14:28');
INSERT INTO `presc_drug` VALUES (134, 103, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-01 13:37:34');
INSERT INTO `presc_drug` VALUES (135, 104, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-02 01:23:08');
INSERT INTO `presc_drug` VALUES (136, 105, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-02 16:27:15');
INSERT INTO `presc_drug` VALUES (137, 106, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-02 16:27:52');
INSERT INTO `presc_drug` VALUES (138, 107, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-02 17:25:56');
INSERT INTO `presc_drug` VALUES (139, 108, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-02 17:44:13');
INSERT INTO `presc_drug` VALUES (140, 109, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-02 17:44:52');
INSERT INTO `presc_drug` VALUES (141, 109, 2, 8, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-02-02 17:44:52');
INSERT INTO `presc_drug` VALUES (142, 110, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 08:57:27');
INSERT INTO `presc_drug` VALUES (143, 111, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:24:07');
INSERT INTO `presc_drug` VALUES (144, 112, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:43:01');
INSERT INTO `presc_drug` VALUES (145, 113, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:43:58');
INSERT INTO `presc_drug` VALUES (146, 114, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:44:02');
INSERT INTO `presc_drug` VALUES (147, 115, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:44:16');
INSERT INTO `presc_drug` VALUES (148, 116, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:44:48');
INSERT INTO `presc_drug` VALUES (149, 117, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:44:57');
INSERT INTO `presc_drug` VALUES (150, 118, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:45:15');
INSERT INTO `presc_drug` VALUES (151, 119, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:45:34');
INSERT INTO `presc_drug` VALUES (152, 120, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 09:54:37');
INSERT INTO `presc_drug` VALUES (153, 121, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 10:18:01');
INSERT INTO `presc_drug` VALUES (154, 122, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 10:22:59');
INSERT INTO `presc_drug` VALUES (155, 123, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 10:24:31');
INSERT INTO `presc_drug` VALUES (156, 124, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 10:25:43');
INSERT INTO `presc_drug` VALUES (157, 125, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 12:59:12');
INSERT INTO `presc_drug` VALUES (158, 125, 3, 6, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-02-03 12:59:12');
INSERT INTO `presc_drug` VALUES (159, 126, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 13:42:20');
INSERT INTO `presc_drug` VALUES (160, 127, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 21:31:01');
INSERT INTO `presc_drug` VALUES (161, 128, 1, 10, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 22:05:21');
INSERT INTO `presc_drug` VALUES (162, 129, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 22:09:26');
INSERT INTO `presc_drug` VALUES (163, 130, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 22:18:19');
INSERT INTO `presc_drug` VALUES (164, 131, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 22:20:49');
INSERT INTO `presc_drug` VALUES (165, 132, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-03 22:44:10');
INSERT INTO `presc_drug` VALUES (166, 133, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-04 00:34:11');
INSERT INTO `presc_drug` VALUES (167, 134, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-04 00:37:24');
INSERT INTO `presc_drug` VALUES (168, 135, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-04 00:45:03');
INSERT INTO `presc_drug` VALUES (169, 136, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-04 00:50:19');
INSERT INTO `presc_drug` VALUES (170, 137, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-04 11:10:33');
INSERT INTO `presc_drug` VALUES (171, 138, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 10:42:14');
INSERT INTO `presc_drug` VALUES (172, 140, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 12:36:27');
INSERT INTO `presc_drug` VALUES (173, 141, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 12:39:13');
INSERT INTO `presc_drug` VALUES (174, 142, 3, 2, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-02-06 15:26:39');
INSERT INTO `presc_drug` VALUES (175, 142, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 15:26:39');
INSERT INTO `presc_drug` VALUES (176, 143, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 15:30:22');
INSERT INTO `presc_drug` VALUES (177, 144, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 15:33:24');
INSERT INTO `presc_drug` VALUES (178, 144, 3, 6, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-02-06 15:33:24');
INSERT INTO `presc_drug` VALUES (179, 145, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 15:39:51');
INSERT INTO `presc_drug` VALUES (180, 146, 1, 2, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 15:40:53');
INSERT INTO `presc_drug` VALUES (181, 147, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 16:25:49');
INSERT INTO `presc_drug` VALUES (182, 148, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-06 17:52:13');
INSERT INTO `presc_drug` VALUES (183, 148, 3, 5, '333', '345', '感冒片', '感冒片', '1.0g*5', '石家庄', '华润制药', '盒', 5.5600, '2018-02-06 17:52:13');
INSERT INTO `presc_drug` VALUES (184, 149, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-07 10:16:35');
INSERT INTO `presc_drug` VALUES (185, 150, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-08 03:28:23');
INSERT INTO `presc_drug` VALUES (186, 151, 1, 6, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-08 03:29:37');
INSERT INTO `presc_drug` VALUES (187, 152, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-08 04:03:30');
INSERT INTO `presc_drug` VALUES (188, 152, 2, 8, '222', '234', '感冒灵', '感冒灵', '1.0g*10', '石家庄', '华润制药', '盒', 10.2500, '2018-02-08 04:03:30');
INSERT INTO `presc_drug` VALUES (189, 153, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-14 00:50:40');
INSERT INTO `presc_drug` VALUES (190, 154, 1, 5, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-22 12:18:09');
INSERT INTO `presc_drug` VALUES (191, 155, 1, 1, '111', '123', '感冒清', '感冒清', '1', '石家庄', '华润制药', '盒', 12.2500, '2018-02-23 00:47:47');
INSERT INTO `presc_drug` VALUES (192, 156, 1, 1, '000001', '', '(严力宁)黄藤素分散片', '(严力宁)黄藤素分散片', '0.1g*36T', NULL, '湖南方盛制药股份有限公司', '盒', 0.0000, '2018-02-26 13:53:11');
INSERT INTO `presc_drug` VALUES (193, 156, 2, 2, '000002', '', '(可定)瑞舒伐他汀钙片', '(可定)瑞舒伐他汀钙片', '10mg*7T', NULL, '阿斯利康药业（中国）有限公司', '盒', 0.0000, '2018-02-26 13:53:11');
INSERT INTO `presc_drug` VALUES (194, 156, 3, 3, '000003', '', '(久保平)硝苯地平缓释片(III)', '(久保平)硝苯地平缓释片(III)', '30mg*7T', NULL, '北京红林制药有限公司', '盒', 0.0000, '2018-02-26 13:53:12');
INSERT INTO `presc_drug` VALUES (195, 157, 5, 1, '000005', '', '一次性使用输液器(带针)', '一次性使用输液器(带针)', '0.7#1支 QBA17-A', NULL, '山东侨牌集团有限公司', '支', 0.0000, '2018-02-27 02:31:42');
INSERT INTO `presc_drug` VALUES (196, 158, 2, 1, '000002', '', '(可定)瑞舒伐他汀钙片', '(可定)瑞舒伐他汀钙片', '10mg*7T', NULL, '阿斯利康药业（中国）有限公司', '盒', 0.0000, '2018-02-27 02:34:58');
INSERT INTO `presc_drug` VALUES (197, 159, 4, 1, '000004', '', '(达克宁)硝酸咪康唑乳膏', '(达克宁)硝酸咪康唑乳膏', '20g', NULL, '西安杨森制药有限公司', '支', 0.0000, '2018-02-27 16:39:55');
INSERT INTO `presc_drug` VALUES (198, 160, 265, 1, '000031', '', '奥美拉唑肠溶胶囊', '奥美拉唑肠溶胶囊', '20mg', NULL, '成都天台山制药有限公司', '瓶', 2.5000, '2018-06-20 16:53:58');
INSERT INTO `presc_drug` VALUES (199, 160, 242, 1, '000008', '', '保和丸', '保和丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 5.8000, '2018-06-20 16:53:59');
INSERT INTO `presc_drug` VALUES (200, 160, 274, 1, '000040', '', '多潘立酮片', '多潘立酮片', '10mg*30片', NULL, '西安杨森制药', '盒', 16.0000, '2018-06-20 16:53:59');
INSERT INTO `presc_drug` VALUES (201, 161, 265, 1, '000031', '', '奥美拉唑肠溶胶囊', '奥美拉唑肠溶胶囊', '20mg', NULL, '成都天台山制药有限公司', '瓶', 2.5000, '2018-06-20 16:55:30');
INSERT INTO `presc_drug` VALUES (202, 162, 265, 1, '000031', '', '奥美拉唑肠溶胶囊', '奥美拉唑肠溶胶囊', '20mg', NULL, '成都天台山制药有限公司', '瓶', 2.5000, '2018-06-20 17:05:09');
INSERT INTO `presc_drug` VALUES (203, 162, 242, 1, '000008', '', '保和丸', '保和丸', '9g*10丸', NULL, '药都制药集团股份有限公司', '盒', 5.8000, '2018-06-20 17:05:09');
INSERT INTO `presc_drug` VALUES (204, 163, 1433, 1, '000031', '', '奥美拉唑肠溶胶囊', '奥美拉唑肠溶胶囊', '20mg', NULL, '成都天台山制药有限公司', '瓶', 2.5000, '2018-06-25 19:09:22');
INSERT INTO `presc_drug` VALUES (205, 164, 1433, 1, '000031', '', '奥美拉唑肠溶胶囊', '奥美拉唑肠溶胶囊', '20mg', NULL, '成都天台山制药有限公司', '瓶', 2.5000, '2018-06-25 19:09:39');
INSERT INTO `presc_drug` VALUES (206, 165, 1433, 1, '000031', '', '奥美拉唑肠溶胶囊', '奥美拉唑肠溶胶囊', '20mg', NULL, '成都天台山制药有限公司', '瓶', 2.5000, '2018-06-25 19:10:20');
INSERT INTO `presc_drug` VALUES (207, 166, 1974, 1, '000107', '', '感冒灵颗粒', '感冒灵颗粒', '10g*6包', NULL, '广州白云山何济公制药厂', '盒', 6.0000, '2018-09-21 15:09:26');
INSERT INTO `presc_drug` VALUES (208, 166, 2074, 3, '000214', '', '感冒清热颗粒', '感冒清热颗粒', '12g*10袋', NULL, '北京同仁堂科技发展股份有限公司制药厂', '盒', 8.5000, '2018-09-21 15:09:26');
INSERT INTO `presc_drug` VALUES (209, 167, 1974, 1, '000107', '', '感冒灵颗粒', '感冒灵颗粒', '10g*6包', NULL, '广州白云山何济公制药厂', '盒', 6.0000, '2019-12-23 13:25:43');

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `doctor_id` bigint(20) NULL DEFAULT NULL COMMENT '医生ID(FK ref doctor:id)',
  `patient_id` bigint(20) NULL DEFAULT NULL COMMENT '患者ID(FK ref patient:id)',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '处方创建时间',
  `rx_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处方编号（按相应的业务规则生成）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 168 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prescription
-- ----------------------------
INSERT INTO `prescription` VALUES (1, 2, 2, '2018-01-23 06:11:55', '20180123_1');
INSERT INTO `prescription` VALUES (2, 2, 2, '2018-01-23 06:11:55', '20180123_2');
INSERT INTO `prescription` VALUES (3, 2, 2, '2018-01-23 06:49:29', '20180123_1');
INSERT INTO `prescription` VALUES (4, 2, 2, '2018-01-23 06:51:46', '20180123_1');
INSERT INTO `prescription` VALUES (5, 2, 2, '2018-01-23 06:55:41', '20180123_1');
INSERT INTO `prescription` VALUES (6, 2, 2, '2018-01-23 06:59:18', '20180123_2');
INSERT INTO `prescription` VALUES (7, 2, 2, '2018-01-23 08:13:16', '20180123_1');
INSERT INTO `prescription` VALUES (8, 2, 2, '2018-01-23 08:13:16', '20180123_2');
INSERT INTO `prescription` VALUES (9, 2, 2, '2018-01-23 08:28:47', '20180123_1');
INSERT INTO `prescription` VALUES (10, 2, 2, '2018-01-23 08:28:47', '20180123_2');
INSERT INTO `prescription` VALUES (11, 2, 2, '2018-01-23 08:28:48', '20180123_3');
INSERT INTO `prescription` VALUES (12, 2, 2, '2018-01-23 09:16:24', '20180123_1');
INSERT INTO `prescription` VALUES (13, 2, 2, '2018-01-23 09:23:37', '20180123_1');
INSERT INTO `prescription` VALUES (14, 2, 2, '2018-01-23 10:07:02', '20180123_1');
INSERT INTO `prescription` VALUES (15, 2, 2, '2018-01-23 10:09:53', '20180123_2');
INSERT INTO `prescription` VALUES (16, 2, 2, '2018-01-23 10:09:53', '20180123_3');
INSERT INTO `prescription` VALUES (17, 2, 2, '2018-01-23 10:09:53', '20180123_4');
INSERT INTO `prescription` VALUES (18, 2, 2, '2018-01-23 10:10:41', '20180123_5');
INSERT INTO `prescription` VALUES (19, 2, 2, '2018-01-23 10:10:41', '20180123_6');
INSERT INTO `prescription` VALUES (20, 2, 2, '2018-01-23 10:10:41', '20180123_7');
INSERT INTO `prescription` VALUES (21, 2, 2, '2018-01-23 10:10:41', '20180123_8');
INSERT INTO `prescription` VALUES (22, 2, 2, '2018-01-24 00:58:18', '20180123_1');
INSERT INTO `prescription` VALUES (23, 2, 2, '2018-01-24 15:10:07', '20180123_1');
INSERT INTO `prescription` VALUES (24, 2, 2, '2018-01-24 15:15:06', '20180123_2');
INSERT INTO `prescription` VALUES (25, 2, 2, '2018-01-24 17:47:16', '20180123_1');
INSERT INTO `prescription` VALUES (26, 2, 2, '2018-01-24 18:22:36', '20180123_1');
INSERT INTO `prescription` VALUES (27, 2, 2, '2018-01-24 21:02:54', '20180123_1');
INSERT INTO `prescription` VALUES (28, 2, 2, '2018-01-24 21:33:57', '20180123_1');
INSERT INTO `prescription` VALUES (29, 2, 2, '2018-01-24 21:50:38', '20180123_1');
INSERT INTO `prescription` VALUES (30, 2, 2, '2018-01-24 22:12:37', '20180123_1');
INSERT INTO `prescription` VALUES (31, 2, 2, '2018-01-24 22:18:36', '20180123_1');
INSERT INTO `prescription` VALUES (32, 2, 2, '2018-01-24 22:28:05', '20180123_1');
INSERT INTO `prescription` VALUES (33, 2, 2, '2018-01-24 22:29:53', '20180123_1');
INSERT INTO `prescription` VALUES (34, 2, 2, '2018-01-24 23:01:53', '20180123_1');
INSERT INTO `prescription` VALUES (35, 2, 2, '2018-01-24 23:07:07', '20180123_1');
INSERT INTO `prescription` VALUES (36, 2, 2, '2018-01-25 13:25:46', '20180123_1');
INSERT INTO `prescription` VALUES (37, 2, 2, '2018-01-26 09:41:29', '20180123_1');
INSERT INTO `prescription` VALUES (38, 2, 2, '2018-01-26 09:41:30', '20180123_2');
INSERT INTO `prescription` VALUES (39, 3, 3, '2018-01-29 19:15:35', 'O2018012900001');
INSERT INTO `prescription` VALUES (40, 3, 3, '2018-01-29 19:18:12', 'O2018012900002');
INSERT INTO `prescription` VALUES (41, 3, 3, '2018-01-29 19:39:51', 'O2018012900003');
INSERT INTO `prescription` VALUES (42, 3, 3, '2018-01-29 19:41:27', 'O2018012900004');
INSERT INTO `prescription` VALUES (43, 3, 3, '2018-01-29 19:58:44', 'O2018012900005');
INSERT INTO `prescription` VALUES (44, 3, 3, '2018-01-29 20:43:48', 'O2018012900006');
INSERT INTO `prescription` VALUES (45, 3, 3, '2018-01-29 20:48:39', 'O2018012900007');
INSERT INTO `prescription` VALUES (46, 3, 3, '2018-01-29 20:51:31', 'O2018012900008');
INSERT INTO `prescription` VALUES (47, 3, 3, '2018-01-29 20:57:35', 'O2018012900009');
INSERT INTO `prescription` VALUES (48, 3, 3, '2018-01-29 21:16:20', 'O2018012900010');
INSERT INTO `prescription` VALUES (49, 3, 3, '2018-01-29 21:25:45', 'O2018012900011');
INSERT INTO `prescription` VALUES (50, 3, 3, '2018-01-29 21:30:56', 'O2018012900012');
INSERT INTO `prescription` VALUES (51, 3, 3, '2018-01-29 21:48:11', 'O2018012900013');
INSERT INTO `prescription` VALUES (52, 3, 3, '2018-01-29 21:52:47', 'O2018012900014');
INSERT INTO `prescription` VALUES (53, 3, 3, '2018-01-29 22:39:24', 'O2018012900015');
INSERT INTO `prescription` VALUES (54, 3, 3, '2018-01-30 11:22:17', 'O2018013000001');
INSERT INTO `prescription` VALUES (55, 3, 3, '2018-01-30 11:23:42', 'O2018013000002');
INSERT INTO `prescription` VALUES (56, 3, 3, '2018-01-30 11:23:48', 'O2018013000003');
INSERT INTO `prescription` VALUES (57, 3, 3, '2018-01-30 11:23:51', 'O2018013000004');
INSERT INTO `prescription` VALUES (58, 3, 3, '2018-01-30 14:37:48', 'O2018013000005');
INSERT INTO `prescription` VALUES (59, 3, 3, '2018-01-30 14:41:17', 'O2018013000006');
INSERT INTO `prescription` VALUES (60, 3, 3, '2018-01-30 14:51:46', 'O2018013000007');
INSERT INTO `prescription` VALUES (61, 3, 3, '2018-01-30 14:54:41', 'O2018013000008');
INSERT INTO `prescription` VALUES (62, 3, 3, '2018-01-30 14:55:10', 'O2018013000009');
INSERT INTO `prescription` VALUES (63, 3, 3, '2018-01-30 15:03:35', 'O2018013000010');
INSERT INTO `prescription` VALUES (64, 3, 3, '2018-01-30 15:04:43', 'O2018013000011');
INSERT INTO `prescription` VALUES (65, 3, 3, '2018-01-30 15:05:52', 'O2018013000012');
INSERT INTO `prescription` VALUES (66, 3, 3, '2018-01-30 15:05:56', 'O2018013000013');
INSERT INTO `prescription` VALUES (67, 3, 3, '2018-01-30 15:06:45', 'O2018013000014');
INSERT INTO `prescription` VALUES (68, 3, 3, '2018-01-30 15:06:57', 'O2018013000015');
INSERT INTO `prescription` VALUES (69, 3, 3, '2018-01-30 15:07:07', 'O2018013000016');
INSERT INTO `prescription` VALUES (70, 3, 3, '2018-01-30 15:09:37', 'O2018013000017');
INSERT INTO `prescription` VALUES (71, 3, 3, '2018-01-30 15:09:41', 'O2018013000018');
INSERT INTO `prescription` VALUES (72, 3, 3, '2018-01-30 15:09:45', 'O2018013000019');
INSERT INTO `prescription` VALUES (73, 3, 3, '2018-01-30 15:24:11', 'O2018013000020');
INSERT INTO `prescription` VALUES (74, 3, 3, '2018-01-31 08:17:58', 'O2018013100001');
INSERT INTO `prescription` VALUES (75, 3, 3, '2018-01-31 08:21:19', 'O2018013100002');
INSERT INTO `prescription` VALUES (76, 3, 3, '2018-01-31 08:21:50', 'O2018013100003');
INSERT INTO `prescription` VALUES (77, 3, 3, '2018-01-31 08:57:27', 'O2018013100004');
INSERT INTO `prescription` VALUES (78, 3, 3, '2018-01-31 09:10:10', 'O2018013100005');
INSERT INTO `prescription` VALUES (79, 3, 3, '2018-01-31 09:12:59', 'O2018013100006');
INSERT INTO `prescription` VALUES (80, 3, 3, '2018-01-31 09:14:33', 'O2018013100007');
INSERT INTO `prescription` VALUES (81, 3, 3, '2018-01-31 09:16:33', 'O2018013100008');
INSERT INTO `prescription` VALUES (82, 3, 3, '2018-01-31 09:18:05', 'O2018013100009');
INSERT INTO `prescription` VALUES (83, 3, 3, '2018-01-31 09:22:47', 'O2018013100010');
INSERT INTO `prescription` VALUES (84, 3, 3, '2018-01-31 09:29:07', 'O2018013100011');
INSERT INTO `prescription` VALUES (85, 3, 3, '2018-01-31 09:30:41', 'O2018013100012');
INSERT INTO `prescription` VALUES (86, 3, 3, '2018-01-31 11:10:50', 'O2018013100013');
INSERT INTO `prescription` VALUES (87, 3, 3, '2018-01-31 17:46:30', 'O2018013100014');
INSERT INTO `prescription` VALUES (88, 3, 3, '2018-02-01 02:11:38', 'O2018020100001');
INSERT INTO `prescription` VALUES (89, 3, 3, '2018-02-01 02:23:47', 'O2018020100002');
INSERT INTO `prescription` VALUES (90, 3, 3, '2018-02-01 02:30:33', 'O2018020100003');
INSERT INTO `prescription` VALUES (91, 3, 3, '2018-02-01 02:42:58', 'O2018020100004');
INSERT INTO `prescription` VALUES (92, 3, 3, '2018-02-01 02:44:53', 'O2018020100005');
INSERT INTO `prescription` VALUES (93, 3, 3, '2018-02-01 02:46:16', 'O2018020100006');
INSERT INTO `prescription` VALUES (94, 3, 3, '2018-02-01 03:03:51', 'O2018020100007');
INSERT INTO `prescription` VALUES (95, 3, 3, '2018-02-01 03:21:23', 'O2018020100008');
INSERT INTO `prescription` VALUES (96, 3, 3, '2018-02-01 03:24:48', 'O2018020100009');
INSERT INTO `prescription` VALUES (97, 3, 3, '2018-02-01 03:38:23', 'O2018020100010');
INSERT INTO `prescription` VALUES (98, 3, 3, '2018-02-01 03:49:43', 'O2018020100011');
INSERT INTO `prescription` VALUES (99, 3, 3, '2018-02-01 03:51:35', 'O2018020100012');
INSERT INTO `prescription` VALUES (100, 3, 3, '2018-02-01 04:04:16', 'O2018020100013');
INSERT INTO `prescription` VALUES (101, 3, 3, '2018-02-01 04:04:22', 'O2018020100014');
INSERT INTO `prescription` VALUES (102, 3, 3, '2018-02-01 04:14:28', 'O2018020100015');
INSERT INTO `prescription` VALUES (103, 3, 3, '2018-02-01 13:37:34', 'O2018020100016');
INSERT INTO `prescription` VALUES (104, 3, 3, '2018-02-02 01:23:08', 'O2018020200001');
INSERT INTO `prescription` VALUES (105, 3, 3, '2018-02-02 16:27:15', 'O2018020200002');
INSERT INTO `prescription` VALUES (106, 3, 3, '2018-02-02 16:27:51', 'O2018020200003');
INSERT INTO `prescription` VALUES (107, 3, 3, '2018-02-02 17:25:56', 'O2018020200004');
INSERT INTO `prescription` VALUES (108, 3, 3, '2018-02-02 17:44:13', 'O2018020200005');
INSERT INTO `prescription` VALUES (109, 3, 3, '2018-02-02 17:44:52', 'O2018020200006');
INSERT INTO `prescription` VALUES (110, 3, 3, '2018-02-03 08:57:27', 'O2018020300001');
INSERT INTO `prescription` VALUES (111, 3, 3, '2018-02-03 09:24:07', 'O2018020300002');
INSERT INTO `prescription` VALUES (112, 3, 3, '2018-02-03 09:43:01', 'O2018020300003');
INSERT INTO `prescription` VALUES (113, 3, 3, '2018-02-03 09:43:58', 'O2018020300004');
INSERT INTO `prescription` VALUES (114, 3, 3, '2018-02-03 09:44:02', 'O2018020300005');
INSERT INTO `prescription` VALUES (115, 3, 3, '2018-02-03 09:44:16', 'O2018020300006');
INSERT INTO `prescription` VALUES (116, 3, 3, '2018-02-03 09:44:48', 'O2018020300007');
INSERT INTO `prescription` VALUES (117, 3, 3, '2018-02-03 09:44:57', 'O2018020300008');
INSERT INTO `prescription` VALUES (118, 3, 3, '2018-02-03 09:45:15', 'O2018020300009');
INSERT INTO `prescription` VALUES (119, 3, 3, '2018-02-03 09:45:34', 'O2018020300010');
INSERT INTO `prescription` VALUES (120, 3, 3, '2018-02-03 09:54:37', 'O2018020300011');
INSERT INTO `prescription` VALUES (121, 3, 3, '2018-02-03 10:18:01', 'O2018020300012');
INSERT INTO `prescription` VALUES (122, 3, 3, '2018-02-03 10:22:59', 'O2018020300013');
INSERT INTO `prescription` VALUES (123, 3, 3, '2018-02-03 10:24:31', 'O2018020300014');
INSERT INTO `prescription` VALUES (124, 3, 3, '2018-02-03 10:25:43', 'O2018020300015');
INSERT INTO `prescription` VALUES (125, 3, 3, '2018-02-03 12:59:12', 'O2018020300016');
INSERT INTO `prescription` VALUES (126, 3, 3, '2018-02-03 13:42:20', 'O2018020300017');
INSERT INTO `prescription` VALUES (127, 3, 3, '2018-02-03 21:31:01', 'O2018020300018');
INSERT INTO `prescription` VALUES (128, 3, 3, '2018-02-03 22:05:21', 'O2018020300019');
INSERT INTO `prescription` VALUES (129, 3, 3, '2018-02-03 22:09:26', 'O2018020300020');
INSERT INTO `prescription` VALUES (130, 3, 3, '2018-02-03 22:18:19', 'O2018020300021');
INSERT INTO `prescription` VALUES (131, 3, 3, '2018-02-03 22:20:49', 'O2018020300022');
INSERT INTO `prescription` VALUES (132, 3, 3, '2018-02-03 22:44:10', 'O2018020300023');
INSERT INTO `prescription` VALUES (133, 3, 3, '2018-02-04 00:34:10', 'O2018020400001');
INSERT INTO `prescription` VALUES (134, 3, 3, '2018-02-04 00:37:24', 'O2018020400002');
INSERT INTO `prescription` VALUES (135, 3, 3, '2018-02-04 00:45:03', 'O2018020400003');
INSERT INTO `prescription` VALUES (136, 3, 3, '2018-02-04 00:50:19', 'O2018020400004');
INSERT INTO `prescription` VALUES (137, 3, 3, '2018-02-04 11:10:33', 'O2018020400005');
INSERT INTO `prescription` VALUES (138, 3, 3, '2018-02-06 10:42:14', 'O2018020600001');
INSERT INTO `prescription` VALUES (139, 3, 3, '2018-02-06 10:44:45', 'O2018020600002');
INSERT INTO `prescription` VALUES (140, 3, 3, '2018-02-06 12:36:26', 'O2018020600003');
INSERT INTO `prescription` VALUES (141, 3, 3, '2018-02-06 12:39:13', 'O2018020600004');
INSERT INTO `prescription` VALUES (142, 3, 3, '2018-02-06 15:26:39', 'O2018020600005');
INSERT INTO `prescription` VALUES (143, 3, 3, '2018-02-06 15:30:22', 'O2018020600006');
INSERT INTO `prescription` VALUES (144, 3, 3, '2018-02-06 15:33:24', 'O2018020600007');
INSERT INTO `prescription` VALUES (145, 3, 3, '2018-02-06 15:39:51', 'O2018020600008');
INSERT INTO `prescription` VALUES (146, 3, 3, '2018-02-06 15:40:53', 'O2018020600009');
INSERT INTO `prescription` VALUES (147, 3, 3, '2018-02-06 16:25:49', 'O2018020600010');
INSERT INTO `prescription` VALUES (148, 3, 3, '2018-02-06 17:52:13', 'O2018020600011');
INSERT INTO `prescription` VALUES (149, 3, 3, '2018-02-07 10:16:34', 'O2018020700001');
INSERT INTO `prescription` VALUES (150, 3, 3, '2018-02-08 03:28:22', 'O2018020800001');
INSERT INTO `prescription` VALUES (151, 3, 3, '2018-02-08 03:29:37', 'O2018020800002');
INSERT INTO `prescription` VALUES (152, 3, 3, '2018-02-08 04:03:30', 'O2018020800003');
INSERT INTO `prescription` VALUES (153, 3, 3, '2018-02-14 00:50:40', 'O2018021400001');
INSERT INTO `prescription` VALUES (154, 3, 3, '2018-02-22 12:18:09', 'O2018022200001');
INSERT INTO `prescription` VALUES (155, 3, 3, '2018-02-23 00:47:46', 'O2018022300001');
INSERT INTO `prescription` VALUES (156, 3, 3, '2018-02-26 13:53:11', 'O2018022600001');
INSERT INTO `prescription` VALUES (157, 3, 3, '2018-02-27 02:31:42', 'O2018022700001');
INSERT INTO `prescription` VALUES (158, 3, 3, '2018-02-27 02:34:58', 'O2018022700002');
INSERT INTO `prescription` VALUES (159, 3, 3, '2018-02-27 16:39:55', 'O2018022700003');
INSERT INTO `prescription` VALUES (160, 3, 5, '2018-06-20 16:53:58', 'O2018062000001');
INSERT INTO `prescription` VALUES (161, 3, 5, '2018-06-20 16:55:30', 'O2018062000002');
INSERT INTO `prescription` VALUES (162, 3, 5, '2018-06-20 17:05:09', 'O2018062000003');
INSERT INTO `prescription` VALUES (163, 3, 5, '2018-06-25 19:09:22', 'O2018062500001');
INSERT INTO `prescription` VALUES (164, 3, 5, '2018-06-25 19:09:39', 'O2018062500002');
INSERT INTO `prescription` VALUES (165, 3, 5, '2018-06-25 19:10:20', 'O2018062500003');
INSERT INTO `prescription` VALUES (166, 3, 5, '2018-09-21 15:09:26', 'O2018092100001');
INSERT INTO `prescription` VALUES (167, 3, 5, '2019-12-23 13:25:43', 'O2019122300001');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色自增ID',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', '系统管理员', 1);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色权限关系表自增ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT 'FK. 引用角色ID( ref role:role_id)',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT 'FK,引用权限ID( ref menu:menu_id)',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`role_permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (69, 1, 1, 1);
INSERT INTO `role_permission` VALUES (70, 1, 2, 1);
INSERT INTO `role_permission` VALUES (71, 1, 3, 1);
INSERT INTO `role_permission` VALUES (72, 1, 4, 1);
INSERT INTO `role_permission` VALUES (73, 1, 6, 1);
INSERT INTO `role_permission` VALUES (74, 1, 7, 1);
INSERT INTO `role_permission` VALUES (75, 1, 8, 1);
INSERT INTO `role_permission` VALUES (76, 1, 9, 1);
INSERT INTO `role_permission` VALUES (77, 1, 5, 1);
INSERT INTO `role_permission` VALUES (78, 1, 10, 1);
INSERT INTO `role_permission` VALUES (79, 1, 11, 1);
INSERT INTO `role_permission` VALUES (80, 1, 12, 1);
INSERT INTO `role_permission` VALUES (81, 1, 13, 1);
INSERT INTO `role_permission` VALUES (82, 1, 14, 1);
INSERT INTO `role_permission` VALUES (83, 1, 15, 1);
INSERT INTO `role_permission` VALUES (84, 1, 16, 1);
INSERT INTO `role_permission` VALUES (85, 1, 17, 1);
INSERT INTO `role_permission` VALUES (86, 1, 18, 1);
INSERT INTO `role_permission` VALUES (87, 1, 19, 1);
INSERT INTO `role_permission` VALUES (88, 1, 20, 1);
INSERT INTO `role_permission` VALUES (89, 1, 22, 1);
INSERT INTO `role_permission` VALUES (90, 1, 21, 1);

-- ----------------------------
-- Table structure for rx_knowledge_base
-- ----------------------------
DROP TABLE IF EXISTS `rx_knowledge_base`;
CREATE TABLE `rx_knowledge_base`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '处方知识库自增ID',
  `acb` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '助记码',
  `warename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `waresimname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通用名',
  `warespec` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `prod_addr` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产地',
  `producer` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产厂家',
  `wareuint` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `dosage` decimal(12, 2) NULL DEFAULT NULL COMMENT '单次剂量',
  `doseunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单次剂量单位',
  `times` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频次（给药次数）:1次/天',
  `mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用法（给药方式）:口服，外用',
  `days` int(4) NULL DEFAULT NULL COMMENT '疗程（天数）',
  `saleminspec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小可出售包装规格',
  `saleminunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小可出售包装剂量单位',
  `saleminprice` decimal(12, 2) NULL DEFAULT NULL COMMENT '最小可出售包装剂量单位单价',
  `count` int(11) NULL DEFAULT NULL COMMENT '药品数量',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '患者性别 1:男；2：女',
  `old` int(3) NULL DEFAULT NULL COMMENT '患者年龄',
  `diseae` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疾病名称（诊断结果） 多个结果用英文“,”分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '处方知识库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rx_knowledge_base_raw
-- ----------------------------
DROP TABLE IF EXISTS `rx_knowledge_base_raw`;
CREATE TABLE `rx_knowledge_base_raw`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '处方知识库自增ID',
  `acb` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '助记码',
  `warename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `waresimname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通用名',
  `warespec` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
  `prod_addr` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产地',
  `producer` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产厂家',
  `wareuint` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `dosage` decimal(12, 2) NULL DEFAULT NULL COMMENT '单次剂量',
  `doseunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单次剂量单位',
  `times` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频次（给药次数）:1次/天',
  `mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用法（给药方式）:口服，外用',
  `days` int(4) NULL DEFAULT NULL COMMENT '疗程（天数）',
  `saleminspec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小可出售包装规格',
  `saleminunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最小可出售包装剂量单位',
  `saleminprice` decimal(12, 2) NULL DEFAULT NULL COMMENT '最小可出售包装剂量单位单价',
  `count` int(11) NULL DEFAULT NULL COMMENT '药品数量',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '患者性别 1:男；2：女',
  `old` int(3) NULL DEFAULT NULL COMMENT '患者年龄',
  `diseae` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '疾病名称（诊断结果） 多个结果用英文“,”分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '未处理的处方知识库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务自增ID',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `job_status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态 1：任务中；2：暂停；3：停止',
  `cron_expression_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务运行时间表达式名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务运行时间表达式',
  `job_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '计划任务信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'EVERY_MINUTE', 'QUARTZ_JOB_GROUP', 3, '每分钟执行', '0 */1 * * * ?', '每分钟执行');
INSERT INTO `schedule_job` VALUES (2, 'EVERY_DAY_TIME_ZERO', 'QUARTZ_JOB_GROUP', 3, '每天（00:00点）执行', '0 0 0 * * ?', '每天（00:00点）执行');

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `doctor_id` bigint(20) NULL DEFAULT NULL COMMENT '医生ID(在本地数据库中)',
  `doctor_old_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Doctor ID(在东华系统中)',
  `comment` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板备注',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES (1, '123', 3, '12', NULL, 1, '2018-02-22 11:20:38');

-- ----------------------------
-- Table structure for template_detail
-- ----------------------------
DROP TABLE IF EXISTS `template_detail`;
CREATE TABLE `template_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `template_id` bigint(20) NULL DEFAULT NULL COMMENT '模板id(FK ref template:id)',
  `wareid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `barcode` varchar(140) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `abc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `warename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `waresimname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `warespec` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prod_addr` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `producer` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wareunit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  `saleprice` decimal(12, 2) NULL DEFAULT NULL,
  `inventory` int(4) NULL DEFAULT NULL,
  `mode` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `times` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dosage` decimal(12, 2) NULL DEFAULT NULL,
  `doseunit` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `days` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NULL DEFAULT NULL,
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父帐户ID（某帐户下可以有子帐户）',
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录名',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录口令',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '帐户创建时间',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在部门',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户EMAIL地址',
  `type` int(4) NULL DEFAULT NULL COMMENT '帐户类型。1：前台帐户；2：后台帐户',
  `link_man` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `link_phone_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话号码',
  `mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` int(4) NULL DEFAULT NULL COMMENT '帐户状态。1：有效；2：无效',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, 'admin', '管理员', 'd2abaa37a7c3db1137d385e1d8c15fd2', NULL, NULL, NULL, 1, '宋先生', '15383992472', '15383992472', NULL, 1, NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色关系表自增ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT 'FK,引用用户ID( ref user:id )',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT 'FK,引用角色ID ( ref role:role_id )',
  `deleted` int(4) NULL DEFAULT 1 COMMENT '是否删除（1-未删除，2-删除，默认1）',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
