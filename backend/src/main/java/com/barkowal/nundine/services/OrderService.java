package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.dtos.order.CreateOrderRequest;

import java.util.UUID;

public interface OrderService {
    public void createOrder(UUID buyerId, CreateOrderRequest createOrderRequest);
}
