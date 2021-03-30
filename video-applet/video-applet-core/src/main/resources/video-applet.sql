#创建数据库
create database video_applet;
#如果表存在则删除表
DROP TABLE IF EXISTS `bgm`;
DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `hot`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `users_fans`;
DROP TABLE IF EXISTS `users_like_videos`;
DROP TABLE IF EXISTS `videos`;
DROP TABLE IF EXISTS `users_report`;
#背景音乐表
CREATE TABLE `bgm`
(
    `id`     varchar(64)  NOT NULL comment 'ID',
    `author` varchar(255) NOT NULL comment '作者',
    `name`   varchar(255) NOT NULL comment '名称',
    `path`   varchar(255) NOT NULL COMMENT '播放地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
#用户评论表
CREATE TABLE `comments`
(
    `id`                varchar(20) NOT NULL comment 'ID',
    `father_comment_id` varchar(20) DEFAULT NULL comment '父级评论ID',
    `to_user_id`        varchar(20) DEFAULT NULL comment '指向用户ID',
    `video_id`          varchar(20) NOT NULL COMMENT '视频id',
    `from_user_id`      varchar(20) NOT NULL COMMENT '留言者，评论的用户id',
    `comment`           text        NOT NULL COMMENT '评论内容',
    `create_time`       datetime    NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='评论表';
#热搜词表
CREATE TABLE `hot`
(
    `id`      varchar(64) NOT NULL comment 'ID',
    `content` varchar(128) DEFAULT NULL COMMENT '搜索的内容',
    `num`     int(11)     NOT NULL COMMENT '搜索的次数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='视频搜索的记录表';

#用户表
CREATE TABLE `users`
(
    `id`                  varchar(64) NOT NULL comment 'ID',
    `username`            varchar(20) NOT NULL COMMENT '用户名',
    `password`            varchar(64) NOT NULL COMMENT '密码',
    `face_image`          varchar(255) DEFAULT NULL COMMENT '我的头像，如果没有默认给一张',
    `nickname`            varchar(20) NOT NULL COMMENT '昵称',
    `fans_counts`         int(11)      DEFAULT '0' COMMENT '我的粉丝数量',
    `follow_counts`       int(11)      DEFAULT '0' COMMENT '我关注的人总数',
    `receive_like_counts` int(11)      DEFAULT '0' COMMENT '我接受到的赞美/收藏 的数量',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

#用户粉丝关系表
CREATE TABLE `users_fans`
(
    `id`      varchar(64) NOT NULL COMMENT 'ID',
    `user_id` varchar(64) NOT NULL COMMENT '用户',
    `fan_id`  varchar(64) NOT NULL COMMENT '粉丝',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`, `fan_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户粉丝关联关系表';

#用户喜欢的/赞过的视频
CREATE TABLE `users_like_videos`
(
    `id`       varchar(64) NOT NULL COMMENT 'ID',
    `user_id`  varchar(64) NOT NULL COMMENT '用户',
    `video_id` varchar(64) NOT NULL COMMENT '视频',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_video_rel` (`user_id`, `video_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户喜欢的/赞过的视频';

#视频信息表
CREATE TABLE `videos`
(
    `id`            varchar(64)  NOT NULL COMMENT 'ID',
    `user_id`       varchar(64)  NOT NULL COMMENT '发布者id',
    `audio_id`      varchar(64)           DEFAULT NULL COMMENT '用户使用音频的信息',
    `video_desc`    varchar(128)          DEFAULT NULL COMMENT '视频描述',
    `video_path`    varchar(255) NOT NULL COMMENT '视频存放的路径',
    `video_seconds` float(6, 2)           DEFAULT NULL COMMENT '视频秒数',
    `video_width`   int(6)                DEFAULT NULL COMMENT '视频宽度',
    `video_height`  int(6)                DEFAULT NULL COMMENT '视频高度',
    `cover_path`    varchar(255)          DEFAULT NULL COMMENT '视频封面图',
    `like_counts`   bigint(20)   NOT NULL DEFAULT '0' COMMENT '喜欢/赞美的数量',
    `status`        int(1)       NOT NULL COMMENT '视频状态：1、发布成功 2、禁止播放，管理员操作',
    `create_time`   datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='视频信息表';

#用户举报表
CREATE TABLE `users_report`
(
    `id`            varchar(64)  NOT NULL COMMENT 'ID',
    `deal_user_id`  varchar(64)  NOT NULL COMMENT '被举报用户id',
    `deal_video_id` varchar(64)  NOT NULL COMMENT '被举报视频ID',
    `title`         varchar(128) NOT NULL COMMENT '类型标题，让用户选择，详情见 枚举',
    `content`       varchar(255) DEFAULT NULL COMMENT '内容',
    `userid`        varchar(64)  NOT NULL COMMENT '举报人的id',
    `create_date`   datetime     NOT NULL COMMENT '举报时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='举报用户表';
