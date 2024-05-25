package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.dto.cartProduct.CartProductRequest;
import com.bohaohan.shopbe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/shopbe/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getCartByAccountId(@PathVariable int id) {
        return ResponseEntity.ok(cartService.getCartByAccountId((long) id));
    }

    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody CartProductRequest cartProductRequest) {
        return ResponseEntity.ok(cartService.addToCart(cartProductRequest));
    }

    @PutMapping
    public ResponseEntity<?> updateQuantity(@RequestBody CartProductRequest cartProductRequest) {
        return ResponseEntity.ok(cartService.updateQuantity(cartProductRequest));
    }

    @DeleteMapping
    public String removeProductFromCart(@RequestBody CartProductRequest cartProductRequest) {
        cartService.removeCartProductFromCart(cartProductRequest);
        return "removed successfully!";
    }

    @DeleteMapping("/{id}")
    public String removeCartByAccountId(@PathVariable int id) {
        cartService.removeAllProductFromCart((long) id);
        return "removed successfully!";
    }


}
