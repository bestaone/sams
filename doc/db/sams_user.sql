
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Records of undo_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `avatar_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `regtime` datetime NOT NULL,
  `updater_id` bigint NOT NULL,
  `creater_id` bigint NOT NULL,
  `create_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `update_time` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `IDX_78a916df40e02a9deb1c4b75ed` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `avatar_url`, `username`, `password`, `phone_num`, `regtime`, `updater_id`, `creater_id`, `create_time`, `update_time`, `status`) VALUES (1, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', 'admin', '{bcrypt}$2a$05$xcxUFhuw0yhCHW9CiWQ2IOfcOvBy2FE9eNHs9s5XpbU9Pt5ruxX/S', '13712345678', '2022-07-11 14:26:40', 1, 1, '2022-07-11 06:26:40.513266', '2023-02-07 13:54:58.628495', 1);
INSERT INTO `user` (`id`, `avatar_url`, `username`, `password`, `phone_num`, `regtime`, `updater_id`, `creater_id`, `create_time`, `update_time`, `status`) VALUES (2, 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png', 'test', '{bcrypt}$2a$05$xcxUFhuw0yhCHW9CiWQ2IOfcOvBy2FE9eNHs9s5XpbU9Pt5ruxX/S', '13712345679', '2022-07-11 14:26:40', 1, 1, '2022-07-11 06:26:40.513266', '2023-02-07 13:56:31.954969', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
