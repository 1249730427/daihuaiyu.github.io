package com.service.product.controller;


import com.service.product.model.Product;
import com.service.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author daihuaiyu
 * @Description: 商品服务对外提供接口
 * @date 2019/7/12 下午12:43
 */
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    //集群情况下，用于订单服务查看到底调用的是哪个商品微服务节点
    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品列表
     *
     * @return
     */
    @RequestMapping("list")
    public Object list() {
        return productService.listProduct();
    }

    /**
     * t
     *
     * @return
     */
//    @GetMapping("/find")
//   public String findById(@RequestParam(value = "produceId") int produceId){
//        return JSON.toJSONString(produceService.findById(produceId));
//
//    }
    @GetMapping("/find")
    public Object findById(@RequestParam(value = "productId") int produceId) {
        Product product = productService.findById(produceId);
        Product result = new Product();
        BeanUtils.copyProperties(product, result);
        result.setProductName(result.getProductName() + " data from port=" + port);
        return result;
//        return JSON.toJSONString(produceService.findById(produceId));

    }

}
