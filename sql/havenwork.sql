/*用户表*/
CREATE TABLE IF NOT EXISTS hw_user(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `address` varchar(64) NOT NULL COMMENT '地址',
  `description` varchar(64) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO hw_user(username, address, description)
    VALUES ("王涛", "北京朝阳", "IT软件开发工程师"),
      ("李晓", "上海虹桥", "金融财务分析师"),
      ("李清水", "深圳南山", "风投专员");