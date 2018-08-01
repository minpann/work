/*用户表*/
CREATE TABLE IF NOT EXISTS`t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `birth_day` datetime DEFAULT NULL COMMENT '出生日期',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO `work`.`t_user`(`id`, `username`, `address`, `birthday`, `description`,`create_time`,`update_time`) VALUES (null, '王浩', '北京', '2018-07-18 12:59:52', '金融分析师', now(), now());
INSERT INTO `work`.`t_user`(`id`, `username`, `address`, `birthday`, `description`,`create_time`,`update_time`) VALUES (null, '赵亚', '上海', '2018-07-18 12:59:53', 'IT软件工程师', now(), now());