package com.barkowal.nundine.domain.dtos.order;

import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {
    @Override
    public CreateOrderRequest toCreateOrderRequest(CreateOrderRequestDTO dto) {
        return new CreateOrderRequest(dto.inventoryId(), dto.productIds() ,dto.quantities());
    }
}
