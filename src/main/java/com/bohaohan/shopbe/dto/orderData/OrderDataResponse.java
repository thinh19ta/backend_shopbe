package com.bohaohan.shopbe.dto.orderData;

import com.bohaohan.shopbe.dto.orderProduct.OrderProductResponse;
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
public class OrderDataResponse {
    private Long id;
    private List<OrderProductResponse> orderProductResponses;
    private String paymentMethod;
    private String paymentStatus;
    private double totalPrice;
    private String orderDate;
    private String status;
}
