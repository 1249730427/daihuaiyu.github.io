CREATE TABLE  miaosha_user(
    id bigint(20) NOT NULL COMMENT '用户id,手机号码' ,
    nickname varchar(255) NOT NULL,
    password varchar(32) NOT NULL COMMENT 'MD5(MD5(明文Pass+固定的salt)+salt)',
    salt varchar(10) DEFAULT NULL,
    head varchar(128)  DEFAULT NULL COMMENT '头像，云存储的ID',
    register_date datetime DEFAULT NULL COMMENT '注册时间',
    last_login_data datetime DEFAULT NULL COMMENT '上次登录时间',
    login_count int(11) DEFAULT 0 COMMENT '登录次数',
    primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE goods(
    id bigint(20) NOT NULL COMMENT '商品ID',
    goods_name VARCHAR(255) COMMENT '商品名称',
    goods_title VARCHAR(1024) comment '商品标题',
    goods_img VARCHAR(1024) comment '商品图片',
    goods_detail text comment '商品描述',
    goods_price float(10,2) comment '商品价格',
    goods_stock int(11) comment '商品库存',
    primary key(id)
)ENGINE =InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `miaosha_goods`
(
    id            bigint(20) NOT NULL COMMENT '秒杀商品ID',
    goods_id      bigint(20) NOT NULL COMMENT '商品ID',
    miaosha_price float(10, 2) DEFAULT NULL COMMENT '秒杀价格',
    stock_count   int(11)      DEFAULT NULL COMMENT '库存',
    start_date    datetime     DEFAULT NULL COMMENT '秒杀开始时间',
    end_date      datetime     DEFAULT NULL COMMENT '秒杀结束时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`, `goods_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table order_info(
    id bigint(20) not null comment '订单ID',
    user_id bigint(20) not null comment '用户ID',
    goods_id bigint(20) not null comment '商品ID',
    delivery_addr_id bigint(20) not null comment '收货地址ID',
    goods_name VARCHAR(255) COMMENT '商品名称',
    goods_count int(11) COMMENT '购买的商品数量',
    goods_price float(10,2) comment '商品价格',
    order_channel tinyint comment '购买渠道',
    status tinyint comment '状态',
    create_date datetime comment '创建时间',
    pay_date datetime comment '支付时间',
    primary key(id)
)ENGINE =InnoDB DEFAULT CHARSET=utf8mb4;

create table miaosha_order(
    id bigint(20) comment '秒杀订单ID',
    user_id bigint(20) comment  '用户ID',
    order_id bigint(20) comment '订单ID',
    goods_id bigint(20) comment '商品ID',
    primary key(id),
    unique key(id,user_id,order_id,goods_id)
)ENGINE =InnoDB DEFAULT CHARSET=utf8mb4;
#初始化用户信息
INSERT INTO `miaosha`.`miaosha_user`(`id`, `nickname`, `password`, `salt`, `head`, `register_date`, `last_login_data`, `login_count`)
VALUES (13635607637, '带鱼', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', NULL, '2021-05-11 10:50:34', '2021-05-11 10:50:37', 0);
#初始化产品信息
INSERT INTO `miaosha`.`goods`(`id`, `goods_name`, `goods_title`, `goods_img`, `goods_detail`, `goods_price`, `goods_stock`) VALUES (1, 'iphone-xr', '苹果手机', '/img/iphonex.png', 'Apple苹果iphone XR双卡双待手机 黑色 128GB', 4499.00, 488);
INSERT INTO `miaosha`.`goods`(`id`, `goods_name`, `goods_title`, `goods_img`, `goods_detail`, `goods_price`, `goods_stock`) VALUES (2, 'huawei-mate10', '华为手机', '/img/meta10.png', '【二手95新】华为Mate10二手手机 【现货速发】徕卡双摄 全面屏游戏手机 2K屏 摩卡金 6+128G全网通', 3566.00, 497);
#初始化秒杀产品信息
INSERT INTO `miaosha`.`miaosha_goods`(`id`, `goods_id`, `miaosha_price`, `stock_count`, `start_date`, `end_date`) VALUES (1, 1, 5.00, 5, '2021-05-18 18:22:43', '2021-05-30 15:22:48');
INSERT INTO `miaosha`.`miaosha_goods`(`id`, `goods_id`, `miaosha_price`, `stock_count`, `start_date`, `end_date`) VALUES (2, 2, 4.00, 5, '2021-05-05 15:23:05', '2021-05-26 15:23:09');
