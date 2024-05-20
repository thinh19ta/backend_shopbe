package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.service.CartService;
import com.bohaohan.shopbe.service.OrderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/shopbe/order")
public class OrderController {

    @Autowired
    private OrderDataService orderDataService;

    @Autowired
    private CartService cartService;

    @PostMapping
    public void addOrder(@RequestBody String orderData) {
        orderDataService.addNewOrder(orderData);
    }

}
