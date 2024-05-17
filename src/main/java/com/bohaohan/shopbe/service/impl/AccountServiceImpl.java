package com.bohaohan.shopbe.service.impl;

import com.bohaohan.shopbe.dto.account.AccountRequest;
import com.bohaohan.shopbe.dto.account.AccountResponse;
import com.bohaohan.shopbe.dto.auth.AuthRequest;
import com.bohaohan.shopbe.dto.auth.AuthResponse;
import com.bohaohan.shopbe.entity.Account;
import com.bohaohan.shopbe.repository.AccountRepository;
import com.bohaohan.shopbe.service.AccountService;
import com.bohaohan.shopbe.service.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Override
    public boolean addNewAccount(AccountRequest accountRequest) {
        boolean isExist = accountRepository.existsByUserName(accountRequest.getUserName());

        if (isExist) {
            return false;
        }

        Account account = new Account();
        account.setFullName(accountRequest.getFullName());
        account.setUserName(accountRequest.getUserName());
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setRoles(accountRequest.getRoles());
        account.setEmail(accountRequest.getEmail());
        account.setPhone(accountRequest.getPhone());
        account.setAddress(accountRequest.getAddress());
        accountRepository.save(account);

        return true;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        String userName = authRequest.getUserName();
        String password = authRequest.getPassword();

        Account account = accountRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            // Authenticate user
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            // If authenticated, generate token and return response
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(userName);
                return new AuthResponse(token, account.getId());
            } else {
                // Authentication failed
                throw new BadCredentialsException("Invalid username or password");
            }
        } catch (BadCredentialsException ex) {
            // Handle incorrect credentials
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find the ID"));
        return new AccountResponse(account.getId(), account.getFullName(), account.getEmail(), account.getPhone(), account.getAddress());
    }
}
