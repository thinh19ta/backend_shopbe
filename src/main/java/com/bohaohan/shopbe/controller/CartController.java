package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.dto.cart.CartRequest;
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

    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.addCart(cartRequest));
    }

    @DeleteMapping
    public String removeProductFromCart(@RequestBody CartRequest cartRequest){
        cartService.removeProductFromCart(cartRequest);
        return "removed successfully!";
    }

    @DeleteMapping("/{id}")
    public String removeCart(@PathVariable int id){
        cartService.removeCart((long) id);
        return "removed successfully!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartByAccountId(@PathVariable int id) {
        return ResponseEntity.ok(cartService.getCartByAccountId((long) id));
    }

}
