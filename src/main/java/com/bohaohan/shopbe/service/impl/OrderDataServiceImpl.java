package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.entity.OrderData;
import com.bohaohan.shopbe.repository.OrderDataRepository;
import com.bohaohan.shopbe.service.OrderDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDataServiceImpl implements OrderDataService {

    @Autowired
    private OrderDataRepository orderDataRepository;

    @Override
    public void addNewOrder(String orderData) {
        OrderData order = new OrderData();
        order.setOrderData(orderData);
        orderDataRepository.save(order);
    }
}
