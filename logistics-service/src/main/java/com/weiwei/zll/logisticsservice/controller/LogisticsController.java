package com.weiwei.zll.logisticsservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    @RequestMapping("/deliver")
    public String deliver(@RequestParam("orderId") String orderId) {
        return "订单：" + orderId + " 开始发货";
    }
}
