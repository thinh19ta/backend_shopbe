package com.bohaohan.shopbe.repository;

import com.bohaohan.shopbe.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
