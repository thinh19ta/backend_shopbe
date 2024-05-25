package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.dto.orderData.OrderDataRequest;
import com.bohaohan.shopbe.entity.OrderData;
import com.bohaohan.shopbe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/shopbe/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    private String hello() {
        return "Hello";
    }

    @PostMapping
    private void addOrderData(@RequestBody  OrderDataRequest orderDataRequest) {
        orderService.addOrderData(orderDataRequest);
        System.out.println("ID " + orderDataRequest.getId());
        System.out.println("Account ID " + orderDataRequest.getAccountId());
        System.out.println("Status " + orderDataRequest.getStatus());
        System.out.println("Payment status "  + orderDataRequest.getPaymentStatus());
        System.out.println("Payment method " + orderDataRequest.getPaymentMethod());
        System.out.println("List order prodduct" + orderDataRequest.getOrderProductRequests().get(0).getProductId());
    }

}
