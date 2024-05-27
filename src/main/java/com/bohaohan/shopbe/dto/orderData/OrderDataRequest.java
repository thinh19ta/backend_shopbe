package com.bohaohan.shopbe.dto.orderData;

import com.bohaohan.shopbe.dto.orderProduct.OrderProductRequest;
import com.bohaohan.shopbe.entity.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDataRequest {
    private Long id;
    private Long accountId;
    private List<OrderProductRequest> orderProductRequests;
    private String paymentMethod;
    private String paymentStatus;
    private double totalPrice;
    private String status;
}
