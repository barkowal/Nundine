package com.barkowal.nundine.domain.dtos.user;

import java.util.UUID;

public record GetUserResponseDTO(
        UUID id,
        String name,
        String email
) {
}
