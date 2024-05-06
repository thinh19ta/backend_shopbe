package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.dto.account.AccountRequest;
import com.bohaohan.shopbe.dto.auth.AuthRequest;
import com.bohaohan.shopbe.dto.auth.AuthResponse;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.repository.AccountRepository;
import com.bohaohan.shopbe.service.AccountService;
import com.bohaohan.shopbe.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/shopbe/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "/login")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        return accountService.authenticate(authRequest);
    }

    @PostMapping(path = "/register")
    public String addNewAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.addNewAccount(accountRequest);
    }

}
