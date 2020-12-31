package com.weiweil.zll.payservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/pay")
    public String pay(@RequestParam("orderId") String orderId) {
        return "当前订单：" + orderId + " 已完成支付.";
    }

    @RequestMapping("/msg")
    public String msg() {
        System.out.println(port);
        return "get message from port: " + port;
    }
}
