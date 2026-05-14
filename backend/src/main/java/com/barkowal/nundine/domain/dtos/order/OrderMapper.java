package com.barkowal.nundine.domain.dtos.order;

public interface OrderMapper {
    CreateOrderRequest toCreateOrderRequest(CreateOrderRequestDTO dto);
}
