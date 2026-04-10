package com.barkowal.nundine.domain.dtos.category;

import java.util.Optional;
import java.util.UUID;

public record GetCategoryResponseDTO(UUID id, String name, Optional<UUID> parentId, String parentName) {
}
