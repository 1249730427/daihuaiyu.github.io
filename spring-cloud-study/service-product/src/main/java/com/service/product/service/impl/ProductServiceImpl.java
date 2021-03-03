package com.service.product.service.impl;



import com.service.product.model.Product;
import com.service.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> daoMap = new HashMap<>();

    /**
     * 模拟数据库商品数据
     */
    static {
        Product p1 = new Product(1, "苹果X", 9999, 10);
        Product p2 = new Product(2, "冰箱", 5342, 19);
        Product p3 = new Product(3, "洗衣机", 523, 90);
        Product p4 = new Product(4, "电话", 64345, 150);
        daoMap.put(p1.getProductId(), p1);
        daoMap.put(p2.getProductId(), p2);
        daoMap.put(p3.getProductId(), p3);
        daoMap.put(p4.getProductId(), p4);
    }


    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = daoMap.values();
        List<Product> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product findById(int id) {
        return daoMap.get(id);
    }
}
