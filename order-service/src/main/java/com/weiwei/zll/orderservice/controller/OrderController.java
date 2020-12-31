package com.weiwei.zll.orderservice.controller;

import com.weiwei.zll.orderservice.service.LogisticFeign;
import com.weiwei.zll.orderservice.service.PayFeign;
import com.weiwei.zll.orderservice.service.StockFeign;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    private PayFeign payFeign;
    private StockFeign stockFeign;
    private LogisticFeign logisticFeign;
    private RestTemplate restTemplate;

    @Autowired
    public OrderController(PayFeign payFeign, StockFeign stockFeign, LogisticFeign logisticFeign, RestTemplate restTemplate) {
        this.payFeign = payFeign;
        this.stockFeign = stockFeign;
        this.logisticFeign = logisticFeign;
        this.restTemplate = restTemplate;
    }

    /*
    feign方式访问
     */
    @RequestMapping("/create")
    public String order() {
        String orderId = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        //1. 下单支付
        String pay = payFeign.pay(orderId);
        sb.append(pay).append("\n");
        //2. 扣减库存
        String reduce = stockFeign.reduce(orderId);
        sb.append(reduce).append("\n");
        //3. 发货
        String deliver = logisticFeign.deliver(orderId);
        sb.append(deliver);
        System.out.println(sb.toString());
        return "success " + sb.toString();
    }

    /*
    restTemplate方式访问
     */
    @RequestMapping("/rest/create")
    public String restOrder() {
        String orderId = UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        //1. 下单支付
        String pay = restTemplate.getForObject("http://pay-service/pay?orderId=" + orderId, String.class);
        sb.append(pay).append("\n");
        //2. 扣减库存
        String reduce = restTemplate.getForObject("http://stock-service/stock/reduce?orderId=" + orderId, String.class);
        sb.append(reduce).append("\n");
        //3. 发货
        String deliver = restTemplate.getForObject("http://logistics-service/logistics/deliver?orderId=" + orderId, String.class);
        sb.append(deliver);
        System.out.println(sb.toString());
        return "success " + sb.toString();
    }

    @RequestMapping("/ribbon")
    public String ribbon() {
        return restTemplate.getForObject("http://pay-service/msg", String.class);
    }
}
