package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.orderData.OrderDataRequest;
import com.bohaohan.shopbe.dto.orderData.OrderDataResponse;

import java.util.List;

public interface OrderService {
    OrderDataResponse addOrderData(OrderDataRequest orderDataRequest);

    OrderDataResponse addOrderDataByAccountId(OrderDataRequest orderDataRequest);

    List<OrderDataResponse> getAllOrderDataByAccountId(Long accountId);
}
