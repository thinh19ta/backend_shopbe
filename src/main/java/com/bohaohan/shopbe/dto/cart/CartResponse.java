package com.bohaohan.shopbe.dto.cart;

import com.bohaohan.shopbe.dto.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private Set<ProductResponse> products;
}
