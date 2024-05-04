package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.cart.CartRequest;
import com.bohaohan.shopbe.dto.cart.CartResponse;

public interface CartService {
    CartResponse addCart(CartRequest cartRequest);

    CartResponse getCartByAccountId(Long accountId);

    void removeProductFromCart(CartRequest cartRequest);
}
