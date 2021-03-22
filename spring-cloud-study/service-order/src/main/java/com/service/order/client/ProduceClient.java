package com.service.order.client;

import com.service.order.fallbcak.ProduceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author daihuaiyu
 * @Description: 商品服务客户端
 * name = "product-service"是你调用服务端名称
 * fallback = ProductClientFallback.class，后面是你自定义的降级处理类
 * @date 2019/7/12 下午1:01
 */
@FeignClient(name = "product-service", fallback = ProduceClientFallback.class)
public interface ProduceClient {

    /**
     * @throws
     * @Title:
     * @Description: 这样组合就相当于http://product-service/api/v1/product/find
     * @author xub
     */
    @GetMapping("/api/v1/produce/find")
    String findById(@RequestParam(value = "produceId") int produceId);

}
