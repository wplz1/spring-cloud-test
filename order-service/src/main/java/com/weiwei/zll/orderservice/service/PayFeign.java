package com.weiwei.zll.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("pay-service")
public interface PayFeign {

    @RequestMapping("/pay")
    String pay(@RequestParam("orderId") String orderId);
}
