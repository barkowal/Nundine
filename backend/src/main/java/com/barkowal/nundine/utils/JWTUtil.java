package com.barkowal.nundine.utils;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class JWTUtil {
    private JWTUtil(){
    }

    public static UUID parseUserId(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }
}
