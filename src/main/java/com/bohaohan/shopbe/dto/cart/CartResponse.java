package com.bohaohan.shopbe.dto.cart;

import com.bohaohan.shopbe.dto.cartProduct.CartProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private List<CartProductResponse> products;
}
