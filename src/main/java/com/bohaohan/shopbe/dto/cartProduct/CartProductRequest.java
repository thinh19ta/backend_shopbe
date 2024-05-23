package com.bohaohan.shopbe.dto.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductRequest {
    private Long id;
    private Long accountId;
    private Long productId;
    private int quantity;
}
