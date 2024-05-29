package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/shopbe/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById((long) id));
    }

    @GetMapping("/random/{length}")
    public ResponseEntity<?> getRandomProducts(@PathVariable int length) {
        return ResponseEntity.ok(productService.getRandomProductsWithLength(length));
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductsByCategory((long) id));
    }
}
