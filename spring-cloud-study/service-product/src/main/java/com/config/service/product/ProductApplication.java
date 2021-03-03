package com.config.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
  * @ClassName: ProduceApplication
  * @Description: 商品服务启动类
  * @author xub
  * @date 2019/7/12 下午12:29
  */
@SpringBootApplication
@EnableScheduling
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}
