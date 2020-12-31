package com.weiwei.zll.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("stock-service")
public interface StockFeign {

    @RequestMapping("/stock/reduce")
    String reduce(@RequestParam("orderId") String orderId);
}
