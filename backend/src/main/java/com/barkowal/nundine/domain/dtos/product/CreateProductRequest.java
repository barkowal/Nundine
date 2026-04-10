package com.barkowal.nundine.domain.dtos.product;

import java.util.UUID;

public record CreateProductRequest(
        String name,
        String description,
        String image,
        Integer currentPrice,
        UUID categoryId
) {
}
