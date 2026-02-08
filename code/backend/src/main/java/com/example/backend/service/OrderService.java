package com.example.backend.service;

import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
