package com.bohaohan.shopbe.repository;

import com.bohaohan.shopbe.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    void deleteByCartAccountId(Long accountId);
}
