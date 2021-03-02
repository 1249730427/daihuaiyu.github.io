package com.jincou.service.produce.controller;


import com.alibaba.fastjson.JSON;
import com.jincou.service.produce.model.Produce;
import com.jincou.service.produce.service.ProduceService;
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
public class ProduceController {

    //集群情况下，用于订单服务查看到底调用的是哪个商品微服务节点
    @Value("${server.port}")
    private String port;

    @Autowired
    private ProduceService produceService;

    /**
     * 获取所有商品列表
     * @return
     */
    @RequestMapping("list")
    public Object list(){
        return produceService.listProduce();
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
        Produce product = produceService.findById(produceId);
        Produce result = new Produce();
        BeanUtils.copyProperties(product,result);
        result.setProduceName( result.getProduceName()+ " data from port="+port );
        return result;
//        return JSON.toJSONString(produceService.findById(produceId));

    }

}
