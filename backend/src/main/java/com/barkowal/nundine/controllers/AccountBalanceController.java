package com.barkowal.nundine.controllers;

import com.barkowal.nundine.services.AccountBalanceService;
import com.barkowal.nundine.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/accountBalance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountBalanceController {
    private final AccountBalanceService accountBalanceService;

    @GetMapping()
    public ResponseEntity<Long> getAccountBalance(
            @AuthenticationPrincipal Jwt jwt
    ){
        UUID userId = JWTUtil.parseUserId(jwt);
        Long response = accountBalanceService.getAccountBalance(userId);
        return ResponseEntity.ok(response);
    }
}
