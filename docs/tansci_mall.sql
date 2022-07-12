/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : tansci_mall

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/07/2022 15:45:15
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
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编码',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品型号',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态：0、未上架，1、已上架，2、已下架',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品详情',
  `stock` int(10) NULL DEFAULT NULL COMMENT '库存',
  `evaluate` int(10) NULL DEFAULT NULL COMMENT '好评数量',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

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
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、未支付，1、已支付，2、失败，3、退款',
  `pay_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付说明',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `pay_type` int(1) NULL DEFAULT NULL COMMENT '支付类型：1、微信，2、支付宝',
  `amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '金额',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_order
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(11) NOT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路由',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `type` int(1) NOT NULL COMMENT '类型：0、按钮，1、后台菜单，2、前台菜单',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、未上架，1、正常，2、下架',
  `chinese_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `english_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'home', '/admin/Home', 'HomeFilled', 1, 1, '工作台', 'Home', 1, '2021-07-28 15:43:30', '2021-07-20 22:02:18');
INSERT INTO `sys_menu` VALUES (2, 0, 'goods', '/admin/Goods', 'Tools', 1, 1, '商品管理', 'System', 4, '2021-12-17 17:33:08', '2021-10-23 19:50:28');
INSERT INTO `sys_menu` VALUES (3, 0, 'order', '/admin/Order', 'Avatar', 1, 1, '订单管理', 'UserList', 5, '2021-12-17 17:37:24', '2021-07-20 22:09:46');
INSERT INTO `sys_menu` VALUES (4, 0, 'paymentCode', '/admin/PaymentCode', 'Notebook', 1, 1, '收款码', 'Dic', 6, '2021-12-17 17:38:14', '2021-07-22 17:09:46');
INSERT INTO `sys_menu` VALUES (5, 0, 'menu', '/admin/Menu', 'Grid', 1, 1, '菜单管理', 'Menu', 2, '2021-12-17 17:34:53', '2021-07-22 17:11:10');
INSERT INTO `sys_menu` VALUES (6, 0, 'user', '/admin/User', 'Help', 1, 1, '用户管理', 'Role', 3, '2021-12-17 17:36:32', '2021-07-23 16:09:30');
INSERT INTO `sys_menu` VALUES (7, 0, 'product', '/mobile/Product', 'SetUp', 2, 1, '商品', 'Org', 7, '2021-12-17 17:37:10', '2021-10-23 14:23:11');
INSERT INTO `sys_menu` VALUES (8, 0, 'payment', '/mobile/Payment', 'Document', 2, 1, '支付', 'Log', 8, '2022-06-30 13:16:34', '2021-12-20 10:49:42');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `type` int(1) NULL DEFAULT 2 COMMENT '用户类型：1、管理员，2、普通用户',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别：0、男，1、女',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信唯一标识',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('22f60277c18a0f185cfa794af0d58f2a', 'user2', '$2a$10$P.RRnq7Ys.6.19XW/ju61O9dtO41AMS06d4ua5M9wjkwno4pzG9bm', '用户2', 2, 1, '2022-06-26', '', '18812563214', NULL, '', '2022-06-30 14:33:47', '2022-06-30 14:33:47', NULL);
INSERT INTO `sys_user` VALUES ('496347ab137029d80ed8929283d9a48b', 'user1', '$2a$10$NyePi0WfVaT0wzL8dWi7iego7vwENNVCHTUXNtaUzVf7bnh0v6f7y', '用户1', 2, 0, '2022-06-21', '', '18893845632', NULL, '', '2022-06-30 14:33:15', '2022-06-30 14:33:15', NULL);
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6dba', 'admin', '$2a$10$tlWWfjTObqLsC6ONrhNL/.GIpAoFu205TXPK6xUPHHr1kA/paK4lq', '管理员', 1, 0, '2021-07-09', '甘肃兰州', '18893817562', NULL, '', '2022-03-29 14:01:05', '2022-03-29 14:01:07', '');

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu`  (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `menu_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`user_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
