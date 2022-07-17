/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1（mysql）
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : tansci_mall

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 17/07/2022 22:45:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品编码',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品型号',
  `status` int(1) DEFAULT 0 COMMENT '状态：0、未上架，1、已上架，2、已下架',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '价格',
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '商品详情',
  `stock` int(10) DEFAULT NULL COMMENT '库存',
  `evaluate` int(10) DEFAULT NULL COMMENT '好评数量',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商户id',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1000001', '商品交付给华府丹郡号', '1', '1', 1, 200.00, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', NULL, 12, 51, 'bc3ac26e69731b617eb80274453f6dba', '2022-04-08 13:57:13', '2022-03-29 21:21:20', NULL);
INSERT INTO `goods` VALUES ('1000002', '商品交付给华府丹郡号', '2', '1', 1, 200.00, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', NULL, 12, 51, 'bc3ac26e69731b617eb80274453f6dba', '2022-04-08 13:57:21', '2022-03-29 21:21:20', NULL);
INSERT INTO `goods` VALUES ('1000003', '商品交付给华府丹郡号', '3', '1', 1, 200.00, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', NULL, 12, 51, 'bc3ac26e69731b617eb80274453f6dba', '2022-04-08 13:57:27', '2022-03-29 21:21:20', NULL);
INSERT INTO `goods` VALUES ('1000004', '商品交付给华府丹郡号', '4', '1', 1, 200.00, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', NULL, 12, 51, 'bc3ac26e69731b617eb80274453f6dba', '2022-04-08 13:57:32', '2022-03-29 21:21:20', NULL);
INSERT INTO `goods` VALUES ('1000005', '商品交付给华府丹郡号', '5', '1', 1, 200.00, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', NULL, 12, 51, 'bc3ac26e69731b617eb80274453f6dba', '2022-04-08 13:57:38', '2022-03-29 21:21:20', NULL);
INSERT INTO `goods` VALUES ('1000006', '商品交付给华府丹郡号', '6', '1', 1, 200.00, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', NULL, 12, 51, '22f60277c18a0f185cfa794af0d58f2a', '2022-04-08 13:57:43', '2022-03-29 21:21:20', NULL);
INSERT INTO `goods` VALUES ('1000007', '商品交付给华府丹郡号', '7', '1', 1, 200.00, 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png', NULL, 12, 51, '496347ab137029d80ed8929283d9a48b', '2022-04-08 13:57:43', '2022-03-29 21:21:20', NULL);

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品id',
  `status` int(1) DEFAULT NULL COMMENT '状态：0、未支付，1、已支付，2、失败，3、退款',
  `pay_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付说明',
  `pay_time` datetime(0) DEFAULT NULL COMMENT '支付时间',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付类型：1、微信，2、支付宝',
  `amount` decimal(20, 2) DEFAULT NULL COMMENT '金额',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商户id',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `os` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP地址',
  `login_time` datetime(0) DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父id',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分组名称',
  `type` int(16) DEFAULT NULL COMMENT '类型：0、系统，1、业务',
  `dic_value` int(16) DEFAULT NULL COMMENT '值',
  `dic_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `sort` int(16) DEFAULT NULL COMMENT '排序',
  `text1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段1',
  `text2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段2',
  `text3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预留字段3',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a6', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 0, '系统', 1, NULL, NULL, NULL, NULL, '2021-07-06 16:18:05', '');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a7', '723702a73f1d432092d10140c8b789a5', 'sys_dic_type', 0, -1, '系统数据字典类型', 0, NULL, NULL, NULL, '2021-07-06 17:08:04', '2021-07-06 12:44:36', '系统数据字典类型');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a8', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 1, '业务', 2, NULL, NULL, NULL, NULL, '2021-07-06 16:18:19', '');
INSERT INTO `sys_dic` VALUES ('723702a73f1d432092d10140c8b789a5', '0', 'sys_dic', 0, 0, '数据字典', 0, NULL, NULL, NULL, '2021-07-06 14:40:33', '2021-07-06 11:38:58', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路由',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `type` int(1) NOT NULL COMMENT '类型：0、按钮，1、后台菜单，2、前台菜单',
  `status` int(1) DEFAULT NULL COMMENT '状态：0、未上架，1、正常，2、下架',
  `chinese_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '中文名称',
  `english_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '英文名称',
  `level` int(1) DEFAULT NULL COMMENT '级别',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('52be034c743ff0ce55b4dc2627a750d8', '856eabdd6f15324d3c51560e0d82ac7f', 'loginLog', '/admin/LoginLog', 'Cpu', 1, 1, '登录日志', 'LoginLog', 2, 5, '2022-07-17 22:37:32', '2022-07-17 22:37:32', '');
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7d093f0534', '856eabdd6f15324d3c51560e0d82ac7f', 'dic', '/admin/dic', 'SetUp', 1, 1, '字典管理', 'DicInfo', 2, 4, '2022-07-17 21:51:04', '2022-07-17 21:37:25', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7d09snjfsd1', '0', 'order', '/admin/Order', 'Avatar', 1, 1, '订单管理', 'UserList', 1, 4, '2022-07-17 21:52:22', '2021-07-20 22:09:46', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7d0sbhjfshf', '0', 'goods', '/admin/Goods', 'Tools', 1, 1, '商品管理', 'System', 1, 3, '2022-07-17 21:52:13', '2021-10-23 19:50:28', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7d2545fsds', '856eabdd6f15324d3c51560e0d82ac7f', 'role', '/admin/Role', 'Guide', 1, 1, '角色管理', 'Role', 2, 3, '2022-07-17 21:50:55', '2021-07-22 17:09:46', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7ddsbvdsbh', '856eabdd6f15324d3c51560e0d82ac7f', 'user', '/admin/User', 'Help', 1, 1, '用户管理', 'Role', 2, 1, '2022-07-17 21:50:27', '2021-07-23 16:09:30', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7dndjshgus', '856eabdd6f15324d3c51560e0d82ac7f', 'menu', '/admin/Menu', 'Grid', 1, 1, '菜单管理', 'Menu', 2, 2, '2022-07-17 21:50:39', '2021-07-22 17:11:10', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7snjfhsd1545dsgds45', '0', 'home', '/admin/Home', 'HomeFilled', 1, 1, '工作台', 'Home', 1, 1, '2022-07-17 21:47:13', '2021-07-20 22:02:18', NULL);
INSERT INTO `sys_menu` VALUES ('856eabdd6f15324d3c51560e0d82ac7f', '0', 'system', '/system', 'CopyDocument', 1, 1, '系统管理', 'System', 1, 1, '2022-07-17 21:49:14', '2022-07-17 21:49:14', '');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('7908f010a012a2c004120adf487e6e66', '69c502ebb2ff3a7120721e7d09snjfsd1');
INSERT INTO `sys_menu_role` VALUES ('7908f010a012a2c004120adf487e6e66', '69c502ebb2ff3a7120721e7d0sbhjfshf');
INSERT INTO `sys_menu_role` VALUES ('7908f010a012a2c004120adf487e6e66', '69c502ebb2ff3a7snjfhsd1545dsgds45');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '69c502ebb2ff3a7120721e7d093f0534');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '69c502ebb2ff3a7120721e7d09snjfsd1');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '69c502ebb2ff3a7120721e7d0sbhjfshf');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '69c502ebb2ff3a7120721e7d2545fsds');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '69c502ebb2ff3a7120721e7ddsbvdsbh');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '69c502ebb2ff3a7120721e7dndjshgus');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '69c502ebb2ff3a7snjfhsd1545dsgds45');
INSERT INTO `sys_menu_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '856eabdd6f15324d3c51560e0d82ac7f');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `status` int(1) NOT NULL COMMENT '状态：0、正常，1、禁用',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('7908f010a012a2c004120adf487e6e66', '商户权限', 1, 'bc3ac26e69731b617eb80274453f6dba', '2022-07-17 22:11:33', '2022-07-17 22:11:33', '商户权限');
INSERT INTO `sys_role` VALUES ('967e5b1d1fd3a350adf8a1257662459d', '管理员权限', 1, 'bc3ac26e69731b617eb80274453f6dba', '2022-07-17 22:11:10', '2022-07-17 22:11:10', '后台管理员');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `type` int(1) DEFAULT 2 COMMENT '用户类型：1、管理员，2、普通用户',
  `gender` int(1) DEFAULT NULL COMMENT '性别：0、男，1、女',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信唯一标识',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('22f60277c18a0f185cfa794af0d58f2a', 'user2', '$2a$10$P.RRnq7Ys.6.19XW/ju61O9dtO41AMS06d4ua5M9wjkwno4pzG9bm', '用户2', 2, 1, '2022-06-26', '', '18812563214', NULL, NULL, '', '2022-06-30 14:33:47', '2022-06-30 14:33:47', NULL);
INSERT INTO `sys_user` VALUES ('496347ab137029d80ed8929283d9a48b', 'user1', '$2a$10$NyePi0WfVaT0wzL8dWi7iego7vwENNVCHTUXNtaUzVf7bnh0v6f7y', '用户1', 2, 0, '2022-06-21', '', '18893845632', NULL, NULL, '', '2022-06-30 14:33:15', '2022-06-30 14:33:15', NULL);
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6dba', 'admin', '$2a$10$tlWWfjTObqLsC6ONrhNL/.GIpAoFu205TXPK6xUPHHr1kA/paK4lq', '管理员', 1, 0, '2021-07-09', '甘肃兰州', '18893817562', NULL, NULL, '', '2022-03-29 14:01:05', '2022-03-29 14:01:07', '');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('22f60277c18a0f185cfa794af0d58f2a', '7908f010a012a2c004120adf487e6e66');
INSERT INTO `sys_user_role` VALUES ('496347ab137029d80ed8929283d9a48b', '7908f010a012a2c004120adf487e6e66');
INSERT INTO `sys_user_role` VALUES ('bc3ac26e69731b617eb80274453f6dba', '967e5b1d1fd3a350adf8a1257662459d');

SET FOREIGN_KEY_CHECKS = 1;
