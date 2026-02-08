package com.example.backend.dto;

import com.example.backend.entity.Product;
import com.example.backend.entity.Status;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(Long id, List<Product> product, BigDecimal quantity, BigDecimal totalPrice, BigDecimal paidAmount, BigDecimal remainingAmount, String description, Status status) {
}
