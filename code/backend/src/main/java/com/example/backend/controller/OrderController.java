package com.example.backend.controller;

import com.example.backend.dto.OrderDTO;
import com.example.backend.entity.Order;
import com.example.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public OrderDTO updateOrder(@RequestBody @Valid OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO);
    }
    @DeleteMapping("/{orderId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }

}
