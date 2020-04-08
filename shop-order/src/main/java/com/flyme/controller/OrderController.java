package com.flyme.controller;


import com.alibaba.fastjson.JSON;
import com.flyme.domain.Order;
import com.flyme.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/prod/{pid}")
    public Order getOrder(@PathVariable("pid") Integer pid) {
        log.info("接收到商品pid{}，调用下单服务", pid);

        Order order = orderService.createOrder(pid);
        log.info("订单创建成功：{}", JSON.toJSONString(order));

        return order;
    }

    @GetMapping("/order/message")
    public String message() {

        return "测试高并发";
    }

}
