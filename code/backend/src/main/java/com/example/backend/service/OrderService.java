package com.example.backend.service;

import com.example.backend.DTOMapper.OrderDTOMapper;
import com.example.backend.dto.OrderDTO;
import com.example.backend.entity.Order;
import com.example.backend.repository.OrderRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    public static final String ORDER_CACHE = "orderCache";
    private final OrderRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;
    public OrderService(OrderRepository orderRepository, OrderDTOMapper orderDTOMapper) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    @CachePut(value = ORDER_CACHE, key = "result.id()")
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return orderDTOMapper
                .toDto(orderRepository
                        .save(orderDTOMapper.toEntity(orderDTO)));
    }
    @Cacheable(value = ORDER_CACHE, key ="#orderId")
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find order with id " + orderId));
        return orderDTOMapper.toDto(order);
    }
    @CachePut(value = ORDER_CACHE, key = "result.id()")
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        return orderDTOMapper.toDto(orderRepository.save(orderDTOMapper.toEntity(orderDTO)));
    }
    @CacheEvict(value = ORDER_CACHE, key = "#orderId")
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
