package com.bohaohan.shopbe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "cartElement_product", joinColumns = @JoinColumn(name = "cartElementId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;
}
