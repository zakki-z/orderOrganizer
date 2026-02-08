package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal totalPrice;
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal paidAmount;
    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal remainingAmount;
    private String description;
    private Status status;
}
