package com.bohaohan.shopbe.dto.orderProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {
    private Long id;
    private Long productId;
    private int quantity;
}
