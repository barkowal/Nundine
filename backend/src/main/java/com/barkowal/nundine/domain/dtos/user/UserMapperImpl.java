package com.barkowal.nundine.domain.dtos.user;

import com.barkowal.nundine.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public GetUserResponseDTO toGetUserResponseDTO(User user) {
        return new GetUserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
