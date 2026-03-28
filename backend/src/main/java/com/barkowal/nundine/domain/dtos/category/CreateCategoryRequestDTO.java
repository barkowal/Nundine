package com.barkowal.nundine.domain.dtos.category;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateCategoryRequestDTO(
        @NotBlank(message = "Category name is required") String name,
        @Nullable UUID parentId
) {
}
