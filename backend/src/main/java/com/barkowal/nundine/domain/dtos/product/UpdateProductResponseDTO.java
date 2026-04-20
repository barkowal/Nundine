package com.barkowal.nundine.domain.dtos.product;

import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.dtos.user.GetUserResponseDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateProductResponseDTO(
        UUID id,
        String name,
        String description,
        String image,
        Integer currentPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        GetCategoryResponseDTO category,
        GetUserResponseDTO user
) {
}
