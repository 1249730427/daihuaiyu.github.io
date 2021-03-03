package com.config.service.product.controller;


import com.config.service.product.model.Produce;
import com.config.service.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 商品服务对外提供接口
 *
 * @author xub
 * @date 2019/7/12 下午12:43
 */
@RestController
@RequestMapping("/api/v1/produce")
public class ProductController {

    //集群情况下，用于订单服务查看到底调用的是哪个商品微服务节点
    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductService productService;

    /**
     * 获取所有商品列表
     * @return
     */
    @RequestMapping("list")
    public Object list(){
        return productService.listProduce();
    }

    /**
     * t
     * @return
     */
//    @GetMapping("/find")
//   public String findById(@RequestParam(value = "produceId") int produceId){
//        return JSON.toJSONString(produceService.findById(produceId));
//
//    }
    @GetMapping("/find")
    public Object findById(@RequestParam(value = "produceId") int produceId){
        Produce product = productService.findById(produceId);
        Produce result = new Produce();
        BeanUtils.copyProperties(product,result);
        result.setProduceName( result.getProduceName()+ " data from port="+port );
        return result;
//        return JSON.toJSONString(produceService.findById(produceId));

    }

}
