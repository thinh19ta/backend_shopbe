package com.bohaohan.shopbe.service;

import com.bohaohan.shopbe.dto.account.AccountRequest;
import com.bohaohan.shopbe.dto.auth.AuthRequest;
import com.bohaohan.shopbe.dto.auth.AuthResponse;

public interface AccountService {
    String addNewAccount(AccountRequest accountRequest);

    AuthResponse authenticate(AuthRequest authRequest);
}
