package com.weiwei.zll.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("logistics-service")
public interface LogisticFeign {

    @RequestMapping("/logistics/deliver")
    String deliver(@RequestParam("orderId") String orderId);
}
