package com.bohaohan.shopbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany(mappedBy = "orderData", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    private Date orderDate;
    private String paymentMethod;
    private String paymentStatus;
    private double totalPrice;
    private String status;
}
