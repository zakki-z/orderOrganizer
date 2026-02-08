package com.example.backend.dto;


import jakarta.validation.constraints.NotBlank;

public record OrderDTO(Long id, @NotBlank String name, Long productId, Integer quantity) {
}
