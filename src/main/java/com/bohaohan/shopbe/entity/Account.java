package com.bohaohan.shopbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String fullName;

    private String userName;

    private String password;

    private String roles;

    private String email;

    private String phone;

    private String address;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<OrderData> orderData;

}