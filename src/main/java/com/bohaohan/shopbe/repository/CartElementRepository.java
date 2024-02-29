package com.bohaohan.shopbe.repository;

import com.bohaohan.shopbe.entity.CartElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartElementRepository extends JpaRepository<CartElement, Long> {
}
