package com.barkowal.nundine.domain.dtos.order;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequestDTO(
        @NotNull(message = "Seller inventory is required") UUID inventoryId,
        @NotNull(message = "Product ids are required") List<UUID> productIds,
        @NotNull(message = "Product quantities are required") List<Integer> quantities
        ) {

}
