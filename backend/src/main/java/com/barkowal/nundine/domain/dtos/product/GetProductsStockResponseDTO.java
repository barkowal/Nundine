package com.barkowal.nundine.domain.dtos.product;

import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.dtos.user.GetUserResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetProductsStockResponseDTO(
        UUID productId,
        UUID inventoryId,
        String name,
        String image,
        Integer currentPrice,
        GetCategoryResponseDTO category,
        Long quantity
) {
}
