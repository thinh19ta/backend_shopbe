package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.dto.orderData.OrderDataRequest;
import com.bohaohan.shopbe.dto.orderData.OrderDataResponse;
import com.bohaohan.shopbe.entity.OrderData;
import com.bohaohan.shopbe.service.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/shopbe/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/{id}")
    private ResponseEntity<List<OrderDataResponse>> getAllOrderDataByAccountId(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getAllOrderDataByAccountId((long) id));
    }

    @PostMapping
    private ResponseEntity<OrderDataResponse> addOrderDataJustByAccountId(@RequestBody OrderDataRequest orderDataRequest) {
        return ResponseEntity.ok(orderService.addOrderDataByAccountId(orderDataRequest));
    }

//
//    @PostMapping(path = "/test")
//    private ResponseEntity<OrderDataResponse> addOrderData(@RequestBody OrderDataRequest orderDataRequest) {
//        return ResponseEntity.ok(orderService.addOrderData(orderDataRequest));
//    }

}
