package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.orderData.OrderDataRequest;
import com.bohaohan.shopbe.dto.orderData.OrderDataResponse;

public interface OrderService {
    OrderDataResponse addOrderData(OrderDataRequest orderDataRequest);
}
