package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.product.ProductResponse;
import com.bohaohan.shopbe.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProductsByCategory(Long categoryId);
    List<ProductResponse> getRandomProductsWithLength(int length);
}
