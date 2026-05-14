package com.barkowal.nundine.controllers;

import com.barkowal.nundine.domain.dtos.order.CreateOrderRequest;
import com.barkowal.nundine.domain.dtos.order.CreateOrderRequestDTO;
import com.barkowal.nundine.domain.dtos.order.OrderMapper;
import com.barkowal.nundine.services.OrderService;
import com.barkowal.nundine.utils.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<Void> buyProduct(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateOrderRequestDTO createOrderRequestDTO
            ){
        UUID buyerId =  JWTUtil.parseUserId(jwt);
        CreateOrderRequest createOrderRequest = orderMapper.toCreateOrderRequest(createOrderRequestDTO);
        this.orderService.createOrder(buyerId, createOrderRequest);
        return ResponseEntity.ok(null);
    }
}
