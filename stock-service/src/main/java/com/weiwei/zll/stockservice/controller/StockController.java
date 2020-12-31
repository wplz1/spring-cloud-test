package com.weiwei.zll.stockservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @RequestMapping("/reduce")
    public String reduce(@RequestParam("orderId") String orderId) {
        return "订单：" + orderId + "扣减库存成功";
    }
}
