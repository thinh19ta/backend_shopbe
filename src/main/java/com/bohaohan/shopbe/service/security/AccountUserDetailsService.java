package com.bohaohan.shopbe.service.security;

import com.bohaohan.shopbe.config.AccountUserDetail;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUserName(username);
        return account.map(AccountUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found + user"));
    }
}
