package com.barkowal.nundine.domain.dtos.product;

import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetShopProductResponseDTO(
        UUID productId,
        String name,
        String description,
        String image,
        Integer currentPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        GetCategoryResponseDTO category,
        Integer quantity,
        UUID inventoryId,
        UUID sellerId
) {
}
