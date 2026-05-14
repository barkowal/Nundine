package com.barkowal.nundine.domain.dtos.order;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        UUID inventoryId,
        List<UUID> productIds,
        List<Integer> quantities
) {
}
