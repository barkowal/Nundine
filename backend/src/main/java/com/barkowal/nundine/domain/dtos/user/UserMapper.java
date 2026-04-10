package com.barkowal.nundine.domain.dtos.user;

import com.barkowal.nundine.domain.entities.User;

public interface UserMapper {
    GetUserResponseDTO toGetUserResponseDTO(User user);
}
