package com.example.backend.DTOMapper;

import org.mapstruct.Mapper;
import com.example.backend.dto.OrderDTO;
import com.example.backend.entity.Order;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface OrderDTOMapper {
    Order toEntity(OrderDTO dto);
    OrderDTO toDto(Order order);
}
