package com.cong.order.controller;

import com.cong.order.pojo.Order;
import com.cong.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 759329342@qq.com
 * @since created  on  2017/11/7.
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "order/{orderId}", produces = "application/json")
    public Order queryOrderById(@PathVariable("orderId")String orderId){
        return this.orderService.queryOrderById(orderId);
    }

}
