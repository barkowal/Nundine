package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.entities.User;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public interface UserService {
    public boolean userExists(UUID userId);
    public User createUser(UUID userId, Jwt jwt);

}
