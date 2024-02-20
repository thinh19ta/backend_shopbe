package com.bohaohan.shopbe.repository;

import com.bohaohan.shopbe.dto.product.ProductResponse;
import com.bohaohan.shopbe.entity.Category;
import com.bohaohan.shopbe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
