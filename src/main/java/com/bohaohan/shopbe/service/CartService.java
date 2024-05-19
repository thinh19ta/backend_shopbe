package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.cart.CartRequest;
import com.bohaohan.shopbe.dto.cart.CartResponse;
import com.bohaohan.shopbe.dto.product.ProductResponse;

import java.util.List;

public interface CartService {
    CartResponse addCart(CartRequest cartRequest);

    List<ProductResponse> getCartByAccountId(Long accountId);

    void removeProductFromCart(CartRequest cartRequest);
}
