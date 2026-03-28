package com.barkowal.nundine.domain.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateProductRequestDTO(
        @NotBlank(message = "Product name is required") String name,
        @NotBlank(message = "Product description is required") String description,
        @NotBlank(message = "Product image is required") String image,
        @NotNull(message = "Current price is required") Integer currentPrice,
        @NotNull(message = "Category id is required") UUID category
) {
}
