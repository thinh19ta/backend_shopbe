package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.account.AccountRequest;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.repository.AccountRepository;
import com.bohaohan.shopbe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addNewAccount(AccountRequest accountRequest) {
        Account account = new Account();
        account.setFirstName(accountRequest.getFirstName());
        account.setLastName(accountRequest.getLastName());
        account.setUserName(accountRequest.getUserName());
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setRoles(accountRequest.getRoles());
        account.setAvatarUrl(accountRequest.getAvatarUrl());
        accountRepository.save(account);
        return "Add success";
    }
}
