package com.bohaohan.shopbe.service;


import com.bohaohan.shopbe.dto.cartProduct.CartProductRequest;
import com.bohaohan.shopbe.dto.cartProduct.CartProductResponse;

import java.util.List;

public interface CartService {
    List<CartProductResponse> addToCart(CartProductRequest cartProductRequest);

    List<CartProductResponse> getCartByAccountId(Long accountId);

    void removeCartProductFromCart(CartProductRequest cartProductRequest);

    void removeAllProductFromCart(Long accountId);

    CartProductResponse updateQuantity(CartProductRequest cartProductRequest);
}
