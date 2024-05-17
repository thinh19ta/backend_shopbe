package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.account.AccountRequest;
import com.bohaohan.shopbe.dto.account.AccountResponse;
import com.bohaohan.shopbe.dto.auth.AuthRequest;
import com.bohaohan.shopbe.dto.auth.AuthResponse;

public interface AccountService {
    boolean addNewAccount(AccountRequest accountRequest);
    AuthResponse authenticate(AuthRequest authRequest);
    AccountResponse getAccountById(Long id);
}
