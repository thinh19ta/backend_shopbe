package com.bohaohan.shopbe.repository;

import com.bohaohan.shopbe.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataRepository extends JpaRepository<OrderData, Long> {
}
