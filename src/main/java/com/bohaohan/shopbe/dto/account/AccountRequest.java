package com.bohaohan.shopbe.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private String fullName;
    private String userName;
    private String password;
    private String roles;
    private String email;
    private String phone;
    private String address;
}
