package com.bohaohan.shopbe.controller;

import com.bohaohan.shopbe.dto.account.AccountRequest;
import com.bohaohan.shopbe.dto.auth.AuthRequest;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping(path = "/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @PostMapping(path = "/register")
    public String addNewAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.addNewAccount(accountRequest);
    }

}
