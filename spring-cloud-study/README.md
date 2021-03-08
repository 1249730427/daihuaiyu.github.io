# SpringCloud学习项目

## 一、项目概述

#### 1、技术架构

项目总体技术选型

```
SpringBoot2.0.4 + Maven3.5.4 +spring-cloud（Finchley.RELEASE）+ mysql + Redis3.5.4 + lombok(插件)
```

#### 2、项目整体结构

```makefile
eureka          # 注册中心
config-service  # 配置中心服务
zuul-service    # 网关服务
service-produce # 商品服务
service-order   # 订单服务
```

项目的启动顺序 最好也安装上面的顺序启动。

#### 3、测试

输入：**localhost:7001**  如果上面服务都出现那恭喜你 启动成功！

![](https://img2018.cnblogs.com/blog/1090617/201907/1090617-20190712190945077-771945780.png)

<br>

