package com.barkowal.nundine.domain.dtos.category;

import java.util.UUID;

public record CreateCategoryRequest(
        String name,
        UUID parentId
){
}
