package com.example.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> product;
    @Column(nullable = false, updatable = true, precision = 10, scale = 2)
    private BigDecimal quantity;
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal totalPrice;
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal paidAmount;
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal remainingAmount;
    private String description;
    private Status status;
}
