package com.bohaohan.shopbe.repository;

import com.bohaohan.shopbe.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);
    boolean existsByUserName(String userName);

}
