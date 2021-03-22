package com.service.order.service.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.service.order.model.ProduceOrder;
import com.service.order.client.ProduceClient;
import com.service.order.service.ProduceOrderService;
import com.service.order.untils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class ProduceOrderServiceImpl implements ProduceOrderService {

    @Autowired
    private ProduceClient produceClient;
//    @Autowired
//    private RestTemplate restTemplate;

    @Override
    public ProduceOrder save(int userId, int produceId) {

        //获取json格式的字符串数据
        String response = produceClient.findById(produceId);
        //Json字符串转换成JsonNode对象
        JsonNode jsonNode = JsonUtils.str2JsonNode(response);
        //product-service是微服务名称（这里指向的商品微服务名称）,api/v1/product/find?id=? 就是商品微服务对外的接口
//        Map<String, Object> productMap = restTemplate.getForObject("http://product-service/api/v1/produce/find?produceId=" + produceId, Map.class);

        //将数据封装到订单实体中
        ProduceOrder produceOrder = new ProduceOrder();
        produceOrder.setCreateTime(new Date());
        produceOrder.setUserId(userId);
        produceOrder.setTradeNo(UUID.randomUUID().toString());
        //因为在商品微服务配置了集群，所以这里打印看下调用了是哪个集群节点，输出端口号。
        System.out.println(jsonNode.get("produceName").toString());
        //获取商品名称和商品价格
        produceOrder.setProduceName(jsonNode.get("produceName").toString());
        produceOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));

        log.info("最终生成的订单信息为 = {}", produceOrder);
        return produceOrder;
    }
}
