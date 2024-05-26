package com.bohaohan.shopbe.dto.cartProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductResponse {
    private Long id;
    private Long productId;
    private String name;
    private double price;
    private String description;
    private String imageURL;
    private int quantity;
}
