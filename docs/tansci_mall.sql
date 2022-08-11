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

 Date: 11/08/2022 11:30:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cart_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购物车id',
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `goods_num` int(10) NOT NULL COMMENT '数量',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、正常，1、失效',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`cart_id`, `goods_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠券id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `price` decimal(10, 2) NOT NULL COMMENT '优惠金额',
  `norm` decimal(10, 2) NOT NULL COMMENT '限额',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、正常，1、已领取，2、已使用，3、已失效',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型：0、平台券，1、店铺券',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `shop_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领取人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID',
  `shop_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品简介',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态：0、未上架，1、已上架，2、已下架',
  `classify` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `cover_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品主图',
  `stock` int(10) NULL DEFAULT NULL COMMENT '库存',
  `sales` int(11) NULL DEFAULT NULL COMMENT '销量',
  `labels` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签：多个以逗号分隔',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '商品详情',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('f01c51c427d8568a162f7327b4aa91a8', NULL, '鸿星尔克跑步鞋', '鸿星尔克跑步鞋', 1, 'c10002', 99.00, 'http://tansci.top:18003/images/202208114d23c2eac2.jpg', 100, 4, 'gl10001,gl10002,gl10003', '<p>鸿星尔克跑步鞋</p><p><img src=\"http://tansci.top:18003/images/2022081138da8be710.jpg\" alt=\"null\" data-href=\"null\" style=\"\"/></p>', 'bc3ac26e69731b617eb80274453f6dba', '2022-08-11 09:16:05', '2022-08-11 09:16:05', '');
INSERT INTO `goods` VALUES ('f9468bdbff35242d3ff761dcc9b1da3d', NULL, '测试商品', '测试商品测试商品测试商品', 0, 'c10003', 19.00, 'http://tansci.top:18003/images/202208110aa378bfa0.jpg', 10, 2, 'gl10001', '<p>测试商品测试商品测试商品测试商品测试商品</p><p>测试商品</p><p><img src=\"http://tansci.top:18003/images/202207235953bf8b92.png\" alt=\"null\" data-href=\"null\" style=\"\"/></p><p>测试商品</p>', 'bc3ac26e69731b617eb80274453f6dba', '2022-07-23 13:58:00', '2022-07-23 13:58:00', '');

-- ----------------------------
-- Table structure for goods_classify
-- ----------------------------
DROP TABLE IF EXISTS `goods_classify`;
CREATE TABLE `goods_classify`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `sort` int(20) NOT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_classify
-- ----------------------------
INSERT INTO `goods_classify` VALUES ('c10001', '0', '全类', 1, '2022-07-22 17:53:07', '2022-07-22 17:53:09');
INSERT INTO `goods_classify` VALUES ('c10002', 'c10001', '运动', 1, '2022-07-22 17:53:07', '2022-07-22 17:53:09');
INSERT INTO `goods_classify` VALUES ('c10003', 'c10001', '休闲', 2, '2022-07-22 17:53:07', '2022-07-22 17:53:09');
INSERT INTO `goods_classify` VALUES ('c10004', 'c10001', '老年', 3, '2022-07-22 17:53:07', '2022-07-22 17:53:09');

-- ----------------------------
-- Table structure for goods_comment
-- ----------------------------
DROP TABLE IF EXISTS `goods_comment`;
CREATE TABLE `goods_comment`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论id',
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `date` date NULL DEFAULT NULL COMMENT '评价时间',
  `rating` double(10, 2) NULL DEFAULT NULL COMMENT '评分',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评语',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`, `goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品评价表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_comment
-- ----------------------------

-- ----------------------------
-- Table structure for goods_image
-- ----------------------------
DROP TABLE IF EXISTS `goods_image`;
CREATE TABLE `goods_image`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`, `goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品图片表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_image
-- ----------------------------
INSERT INTO `goods_image` VALUES ('0e32a11b3acf87f421f2c4c61e56a6ab', 'f01c51c427d8568a162f7327b4aa91a8', '20220811e53c7f37e3.jpg', 'http://tansci.top:18003/images/20220811e53c7f37e3.jpg', '2022-08-11 09:16:05', '2022-08-11 09:16:05');
INSERT INTO `goods_image` VALUES ('31d50689db45c78163b1269095ab3ab5', 'f9468bdbff35242d3ff761dcc9b1da3d', '202207237a14f7870f.png', 'http://tansci.top:18003/images/202207237a14f7870f.png', '2022-07-23 14:24:51', '2022-07-23 14:24:51');
INSERT INTO `goods_image` VALUES ('87d2f1ee4e775268d9919ccf23af6c69', 'f01c51c427d8568a162f7327b4aa91a8', '202208110aa378bfa0.jpg', 'http://tansci.top:18003/images/202208110aa378bfa0.jpg', '2022-08-11 09:16:05', '2022-08-11 09:16:05');
INSERT INTO `goods_image` VALUES ('9d03433b927a0225c9a90f0e397dbbdb', 'f01c51c427d8568a162f7327b4aa91a8', '20220811b6164cb96a.jpg', 'http://tansci.top:18003/images/20220811b6164cb96a.jpg', '2022-08-11 09:16:05', '2022-08-11 09:16:05');
INSERT INTO `goods_image` VALUES ('b5d9586120e46aa5a394f35601bac15a', 'f01c51c427d8568a162f7327b4aa91a8', '20220811a600642489.jpg', 'http://tansci.top:18003/images/20220811a600642489.jpg', '2022-08-11 09:16:05', '2022-08-11 09:16:05');

-- ----------------------------
-- Table structure for goods_label
-- ----------------------------
DROP TABLE IF EXISTS `goods_label`;
CREATE TABLE `goods_label`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型：primary、success、warning、danger、info',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品标签' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_label
-- ----------------------------
INSERT INTO `goods_label` VALUES ('gl10001', '新品', 'success', '2022-07-22 17:51:17', '2022-07-22 17:51:19');
INSERT INTO `goods_label` VALUES ('gl10002', '爆品', 'warning', '2022-07-22 17:51:17', '2022-07-22 17:51:19');
INSERT INTO `goods_label` VALUES ('gl10003', '精品', 'danger', '2022-07-22 17:51:17', '2022-07-22 17:51:19');

-- ----------------------------
-- Table structure for goods_order
-- ----------------------------
DROP TABLE IF EXISTS `goods_order`;
CREATE TABLE `goods_order`  (
  `goods_order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品订单id',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `goods_num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、未支付，1、已支付，2、失败，3、退款',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`goods_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods_order
-- ----------------------------
INSERT INTO `goods_order` VALUES ('HGDHFG4545BSDHF4', 'o100001', 'f01c51c427d8568a162f7327b4aa91a8', 1, 1, '2022-08-11 09:39:13', 99.00, '496347ab137029d80ed8929283d9a48b', '2022-08-11 10:28:48', '2022-08-11 09:39:30', NULL);

-- ----------------------------
-- Table structure for index_carousel
-- ----------------------------
DROP TABLE IF EXISTS `index_carousel`;
CREATE TABLE `index_carousel`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '连接地址',
  `sort` int(10) NOT NULL COMMENT '排序',
  `status` int(2) NOT NULL COMMENT '状态：0：正常，1：下线',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '首页轮播' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of index_carousel
-- ----------------------------

-- ----------------------------
-- Table structure for index_config
-- ----------------------------
DROP TABLE IF EXISTS `index_config`;
CREATE TABLE `index_config`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `color` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '色值',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、正常，1、下线',
  `goods_classify` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '首页标签配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of index_config
-- ----------------------------

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `order_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总价',
  `discount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `pay_status` int(1) NULL DEFAULT NULL COMMENT '支付状态：0、未支付，1、支付成功，2、支付失败',
  `order_status` int(1) NULL DEFAULT NULL COMMENT '订单状态：0、待支付，1、已支付，2、配货完成，3、出库成功，4、交易成功，5、商家关闭，6、超时关闭',
  `pay_type` int(1) NULL DEFAULT NULL COMMENT '支付类型： 1、支付宝，2、微信，3、银联',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_id`, `order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('o100001', 'o10000214', 56.99, 4.00, 1, 2, 2, '2022-07-23 21:49:38', '22f60277c18a0f185cfa794af0d58f2a', '2022-07-23 21:49:42', '2022-07-23 21:49:46', '支付成功');
INSERT INTO `order_info` VALUES ('o100002', 'o10000216', 120.00, 4.00, 1, 4, 1, '2022-07-23 21:49:38', '496347ab137029d80ed8929283d9a48b', '2022-07-23 21:49:42', '2022-07-23 21:49:46', '交易完成');

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund`  (
  `refund_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退款id',
  `refund_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退款订单号',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `goods_order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品订单id',
  `refund_status` int(1) NOT NULL COMMENT '退款状态：0、退款中，1、退款成功，2、退款失败，3、退款关闭',
  `refund_price` decimal(10, 2) NOT NULL COMMENT '退款金额',
  `refund_time` datetime NULL DEFAULT NULL COMMENT '退款时间',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退款原因',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`refund_id`, `refund_no`, `order_id`, `goods_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退款表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of refund
-- ----------------------------

-- ----------------------------
-- Table structure for seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `seckill_goods`;
CREATE TABLE `seckill_goods`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `goods_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of seckill_goods
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shop_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺介绍',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `level` double(10, 2) NULL DEFAULT NULL COMMENT '店铺星级',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、正常，1、下线',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户id',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`shop_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `type` int(16) NULL DEFAULT NULL COMMENT '类型：0、系统，1、业务',
  `dic_value` int(16) NULL DEFAULT NULL COMMENT '值',
  `dic_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(16) NULL DEFAULT NULL COMMENT '排序',
  `text1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `text2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `text3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段3',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('162c9180378844fdb8b298a131197e34', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 5, '商家关闭', 5, NULL, NULL, NULL, '2022-08-11 09:57:18', '2022-08-11 09:57:18', NULL);
INSERT INTO `sys_dic` VALUES ('1ce305ca80747191d241463e72725f05', '6ae30ff9e88080ef1005f2dd90639245', 'goods_order_status', 1, 3, '已退款', 3, NULL, NULL, NULL, '2022-08-11 09:59:44', '2022-08-11 09:59:44', NULL);
INSERT INTO `sys_dic` VALUES ('254147c0d4243d5995795708821211f4', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 3, '出库成功', 3, NULL, NULL, NULL, '2022-08-11 09:56:21', '2022-08-11 09:56:21', NULL);
INSERT INTO `sys_dic` VALUES ('322fd63dcad01e0af57878065cee901f', '723702a73f1d432092d10140c8b789a5', 'order_status', 1, -1, '订单状态', 1, NULL, NULL, NULL, '2022-08-11 09:55:05', '2022-08-11 09:55:05', '订单状态');
INSERT INTO `sys_dic` VALUES ('3f227ed25d7e2020a30443540d2ae769', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 4, '交易成功', 4, NULL, NULL, NULL, '2022-08-11 09:56:48', '2022-08-11 09:56:48', NULL);
INSERT INTO `sys_dic` VALUES ('44887f0776411044c39dd926bcde31fb', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 6, '超时关闭', 6, NULL, NULL, NULL, '2022-08-11 09:57:39', '2022-08-11 09:57:39', NULL);
INSERT INTO `sys_dic` VALUES ('450f3408a7119aae9937267115ca9aa0', '6ae30ff9e88080ef1005f2dd90639245', 'goods_order_status', 1, 0, '未支付', 0, NULL, NULL, NULL, '2022-08-11 09:58:51', '2022-08-11 09:58:51', NULL);
INSERT INTO `sys_dic` VALUES ('49ccb1b81bc6a746df932b496fbd9905', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 7, '订单退款', 7, NULL, NULL, NULL, '2022-08-11 09:57:57', '2022-08-11 09:57:57', NULL);
INSERT INTO `sys_dic` VALUES ('4f7b184abb36ffa0c1fa375944c137fb', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 2, '配货完成', 2, NULL, NULL, NULL, '2022-08-11 09:56:02', '2022-08-11 09:56:02', NULL);
INSERT INTO `sys_dic` VALUES ('523476688fc4ab0b35d7a514f1cfa420', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 0, '待支付', 0, NULL, NULL, NULL, '2022-08-11 09:55:23', '2022-08-11 09:55:23', NULL);
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a6', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 0, '系统', 1, NULL, NULL, NULL, NULL, '2021-07-06 16:18:05', '');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a7', '723702a73f1d432092d10140c8b789a5', 'sys_dic_type', 0, -1, '系统数据字典类型', 0, NULL, NULL, NULL, '2021-07-06 17:08:04', '2021-07-06 12:44:36', '系统数据字典类型');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a8', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 1, '业务', 2, NULL, NULL, NULL, NULL, '2021-07-06 16:18:19', '');
INSERT INTO `sys_dic` VALUES ('6ae30ff9e88080ef1005f2dd90639245', '723702a73f1d432092d10140c8b789a5', 'goods_order_status', 1, -1, '商品订单状态', 2, NULL, NULL, NULL, '2022-08-11 09:58:32', '2022-08-11 09:58:32', '商品订单状态');
INSERT INTO `sys_dic` VALUES ('701bee7c63ba1f159627cc002242db91', '6ae30ff9e88080ef1005f2dd90639245', 'goods_order_status', 1, 2, '支付失败', 2, NULL, NULL, NULL, '2022-08-11 09:59:24', '2022-08-11 09:59:24', NULL);
INSERT INTO `sys_dic` VALUES ('723702a73f1d432092d10140c8b789a5', '0', 'sys_dic', 0, 0, '数据字典', 0, NULL, NULL, NULL, '2021-07-06 14:40:33', '2021-07-06 11:38:58', NULL);
INSERT INTO `sys_dic` VALUES ('b73bfdf3a5227adbc0703a1d9157f721', '322fd63dcad01e0af57878065cee901f', 'order_status', 1, 1, '已支付', 1, NULL, NULL, NULL, '2022-08-11 09:55:39', '2022-08-11 09:55:39', NULL);
INSERT INTO `sys_dic` VALUES ('dd15aa90f9f83e6617bbfef37eeaca1b', '6ae30ff9e88080ef1005f2dd90639245', 'goods_order_status', 1, 1, '支付成功', 1, NULL, NULL, NULL, '2022-08-11 09:59:08', '2022-08-11 09:59:08', NULL);

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `os` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('2c7906a23818cc7ccb47f53e0ab13724', 'bc3ac26e69731b617eb80274453f6dba', 'admin', 'Windows 10', 'Chrome', '127.0.0.1', '2022-08-11 09:01:10', '2022-08-11 09:01:10');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `parent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单路由',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `type` int(1) NOT NULL COMMENT '类型：0、按钮，1、后台菜单，2、前台菜单',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、未上架，1、正常，2、下架',
  `chinese_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `english_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `level` int(1) NULL DEFAULT NULL COMMENT '级别',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('05ed0ac751eaf99859b75b4a5a6a9a7d', '2b5f7fd4d42b56f4eb03a744fa59f0d6', 'order', '/admin/order/Order', 'SetUp', 1, 1, '订单管理', 'Order', 2, 1, '2022-07-23 12:59:23', '2022-07-23 12:59:23', '');
INSERT INTO `sys_menu` VALUES ('2b5f7fd4d42b56f4eb03a744fa59f0d6', '0', 'order', '/order', 'SetUp', 1, 1, '订单管理', 'Order', 1, 3, '2022-07-23 12:58:34', '2022-07-23 12:58:34', '');
INSERT INTO `sys_menu` VALUES ('52be034c743ff0ce55b4dc2627a750d8', '856eabdd6f15324d3c51560e0d82ac7f', 'loginLog', '/admin/system/LoginLog', 'Cpu', 1, 1, '登录日志', 'LoginLog', 2, 5, '2022-07-17 22:37:32', '2022-07-17 22:37:32', '');
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7d093f0534', '856eabdd6f15324d3c51560e0d82ac7f', 'dic', '/admin/system/Dic', 'SetUp', 1, 1, '字典管理', 'DicInfo', 2, 4, '2022-07-17 21:51:04', '2022-07-17 21:37:25', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7d2545fsds', '856eabdd6f15324d3c51560e0d82ac7f', 'role', '/admin/system/Role', 'Guide', 1, 1, '角色管理', 'Role', 2, 3, '2022-07-17 21:50:55', '2021-07-22 17:09:46', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7ddsbvdsbh', '856eabdd6f15324d3c51560e0d82ac7f', 'user', '/admin/system/User', 'Help', 1, 1, '用户管理', 'Role', 2, 1, '2022-07-17 21:50:27', '2021-07-23 16:09:30', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7120721e7dndjshgus', '856eabdd6f15324d3c51560e0d82ac7f', 'menu', '/admin/system/Menu', 'Grid', 1, 1, '菜单管理', 'Menu', 2, 2, '2022-07-17 21:50:39', '2021-07-22 17:11:10', NULL);
INSERT INTO `sys_menu` VALUES ('69c502ebb2ff3a7snjfhsd1545dsgds45', '0', 'home', '/admin/Home', 'HomeFilled', 1, 1, '工作台', 'Home', 1, 1, '2022-07-17 21:47:13', '2021-07-20 22:02:18', NULL);
INSERT INTO `sys_menu` VALUES ('707eaca13c7c9de7442b7960f005797d', 'b47050cbb8e2d3bd87d470a15c6a788c', 'goodsLabel', '/admin/goods/GoodsLabel', 'PriceTag', 1, 1, '商品标签', 'GoodsLabel', 2, 3, '2022-07-23 12:57:08', '2022-07-23 12:57:08', '');
INSERT INTO `sys_menu` VALUES ('832178b32fafec3168f139b760d37d30', 'b47050cbb8e2d3bd87d470a15c6a788c', 'goods', '/admin/goods/Goods', 'Fries', 1, 1, '商品管理', 'goods', 2, 1, '2022-07-23 12:54:12', '2022-07-23 12:54:12', '');
INSERT INTO `sys_menu` VALUES ('856eabdd6f15324d3c51560e0d82ac7f', '0', 'system', '/system', 'CopyDocument', 1, 1, '系统管理', 'System', 1, 1, '2022-07-17 21:49:14', '2022-07-17 21:49:14', '');
INSERT INTO `sys_menu` VALUES ('b47050cbb8e2d3bd87d470a15c6a788c', '0', 'goods', '/goods', 'Present', 1, 1, '商品管理', 'Goods', 1, 3, '2022-07-23 12:52:48', '2022-07-23 12:52:48', '');
INSERT INTO `sys_menu` VALUES ('df785cb43e428403dbfac6d4ece0189d', '2b5f7fd4d42b56f4eb03a744fa59f0d6', 'goodsOrder', '/admin/order/GoodsOrder', 'Operation', 1, 1, '商品订单', 'GoodsOrder', 2, 2, '2022-07-23 13:00:34', '2022-07-23 13:00:34', '');
INSERT INTO `sys_menu` VALUES ('eded3ad528e3ab905b773d236d4bad7a', 'b47050cbb8e2d3bd87d470a15c6a788c', 'goodsClassify', '/admin/goods/GoodsClassify', 'ScaleToOriginal', 1, 1, '商品分类', 'GoodsClassify', 2, 2, '2022-07-23 12:55:45', '2022-07-23 12:55:45', '');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单角色' ROW_FORMAT = DYNAMIC;

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
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

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
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `type` int(1) NULL DEFAULT 2 COMMENT '用户类型：1、管理员，2、普通用户',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别：0、男，1、女',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信唯一标识',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件',
  `integral` int(10) NULL DEFAULT NULL COMMENT '积分',
  `member` int(10) NULL DEFAULT NULL COMMENT '会员等级',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('22f60277c18a0f185cfa794af0d58f2a', 'user2', '$2a$10$P.RRnq7Ys.6.19XW/ju61O9dtO41AMS06d4ua5M9wjkwno4pzG9bm', '用户2', 2, 1, '2022-06-26', '', '18812563214', NULL, NULL, '', NULL, NULL, '2022-06-30 14:33:47', '2022-06-30 14:33:47', NULL);
INSERT INTO `sys_user` VALUES ('496347ab137029d80ed8929283d9a48b', 'user1', '$2a$10$NyePi0WfVaT0wzL8dWi7iego7vwENNVCHTUXNtaUzVf7bnh0v6f7y', '用户1', 2, 0, '2022-06-21', '', '18893845632', NULL, NULL, '', NULL, NULL, '2022-06-30 14:33:15', '2022-06-30 14:33:15', NULL);
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6dba', 'admin', '$2a$10$tlWWfjTObqLsC6ONrhNL/.GIpAoFu205TXPK6xUPHHr1kA/paK4lq', '管理员', 1, 0, '2021-07-09', '甘肃兰州', '18893817562', NULL, NULL, '', NULL, NULL, '2022-03-29 14:01:05', '2022-03-29 14:01:07', '');

-- ----------------------------
-- Table structure for sys_user_address
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_address`;
CREATE TABLE `sys_user_address`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人手机号',
  `flag` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否为默认 0-非默认 1-是默认',
  `province` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省份',
  `city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市',
  `region` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域',
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件详细地址(街道/楼宇/单元)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户地址表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_address
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('22f60277c18a0f185cfa794af0d58f2a', '7908f010a012a2c004120adf487e6e66');
INSERT INTO `sys_user_role` VALUES ('496347ab137029d80ed8929283d9a48b', '7908f010a012a2c004120adf487e6e66');
INSERT INTO `sys_user_role` VALUES ('bc3ac26e69731b617eb80274453f6dba', '967e5b1d1fd3a350adf8a1257662459d');

SET FOREIGN_KEY_CHECKS = 1;
