package com.config.service.product.service;


import com.config.service.product.model.Product;

import java.util.List;

/**
  * @ClassName: ProductService
  * @Description:  获取商品信息相关接口
  * @author xub
  * @date 2019/7/12 下午12:37
  */
public interface ProductService {

    /**
      * @Description: 查找所有商品
      * @author xub
      */
    List<Product> listProduct();


    /**
     *  根据商品ID查找商品
     */
    Product findById(int productId);
}
