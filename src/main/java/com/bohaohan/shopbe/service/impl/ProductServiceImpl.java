package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.product.ProductResponse;
import com.bohaohan.shopbe.entity.Category;
import com.bohaohan.shopbe.entity.Product;
import com.bohaohan.shopbe.repository.CategoryRepository;
import com.bohaohan.shopbe.repository.ProductRepository;
import com.bohaohan.shopbe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream().map(
                product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getImageURL())
        ).toList();
        return productResponses;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product ->
                        new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getImageURL()))
                .orElse(null);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        List<ProductResponse> productResponses = products.stream().map(
                product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getImageURL())
        ).toList();
        return productResponses;
    }

}

