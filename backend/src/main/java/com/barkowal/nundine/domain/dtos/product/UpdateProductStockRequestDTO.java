package com.barkowal.nundine.domain.dtos.product;

import jakarta.validation.constraints.NotNull;

public record UpdateProductStockRequestDTO(
        @NotNull(message = "Quantity is required") Integer quantity
) {
}
