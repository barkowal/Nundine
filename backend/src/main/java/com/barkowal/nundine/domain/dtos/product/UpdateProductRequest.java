package com.barkowal.nundine.domain.dtos.product;

import java.util.UUID;

public record UpdateProductRequest(
        UUID id,
        String name,
        String description,
        String image,
        Integer currentPrice,
        UUID category
) {
}
