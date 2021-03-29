#创建数据库
create database chat;

#表如果存在则删除表
drop table if exists friend;
drop table if exists information;
drop table if exists user;

#创建朋友表
CREATE TABLE friend (
  id bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
  user varchar(256)  DEFAULT NULL comment '用户名称',
  linker varchar(256) DEFAULT NULL comment '联系人',
  uid int(20) DEFAULT NULL comment '用户ID',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间，默认为第一次插入的服务器时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 comment '朋友表';

#创建信息表
CREATE TABLE information (
  id bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
  iid int(20) DEFAULT NULL comment '信息ID',
  nickName varchar(256) DEFAULT NULL comment '用户昵称',
  signature varchar(1024) DEFAULT NULL comment '签名',
  uId int(20) DEFAULT NULL comment '用户ID',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间，默认为第一次插入的服务器时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 comment '信息表';

#创建用户表
CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT AUTO_INCREMENT comment '主键',
  user_id int(20) NOT NULL comment '用户ID',
  name varchar(256) DEFAULT NULL comment '用户名称',
  passwd varchar(256) DEFAULT NULL comment '用户密码',
  email varchar(612) DEFAULT NULL comment '用户email',
  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间，默认为第一次插入的服务器时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 comment '用户表';

#初始化语句，数据可自定义
INSERT INTO user (user_id,name,passwd,email) values(1249730427,'戴怀玉','daihuaiyu','1249730427@qq.com');
INSERT INTO user (user_id,name,passwd,email) values(891975477,'张三','zhangsan','891975477@qq.com');
INSERT INTO user (user_id,name,passwd,email) values(892174673,'李四','lisi','892174673@qq.com');
INSERT INTO information(iid,nickName,signature,uId) values (1,'带鱼','你若离去',1249730427);
INSERT INTO information(iid,nickName,signature,uId) values (2,'张三','后会无期',891975477);
INSERT INTO  friend(user,linker,uid) values ('带鱼','张三',1249730427);
INSERT INTO  friend(user,linker,uid) values ('张三','带鱼',891975477);

