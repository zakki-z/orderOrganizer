package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
}
