package com.barkowal.nundine.controllers;

import com.barkowal.nundine.domain.dtos.accountBalance.AccountBalanceMapper;
import com.barkowal.nundine.domain.dtos.accountBalance.DepositAccountBalanceRequestDTO;
import com.barkowal.nundine.domain.dtos.accountBalance.GetAccountBalanceResponseDTO;
import com.barkowal.nundine.domain.entities.AccountBalance;
import com.barkowal.nundine.services.AccountBalanceService;
import com.barkowal.nundine.utils.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/accountBalance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountBalanceController {
    private final AccountBalanceService accountBalanceService;
    private final AccountBalanceMapper accountBalanceMapper;

    @GetMapping()
    public ResponseEntity<GetAccountBalanceResponseDTO> getAccountBalance(
            @AuthenticationPrincipal Jwt jwt
    ){
        UUID userId = JWTUtil.parseUserId(jwt);
        AccountBalance accountBalance = accountBalanceService.getAccountBalance(userId);

        GetAccountBalanceResponseDTO response = accountBalanceMapper.toGetAccountBalanceResponseDTO(accountBalance);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/deposit")
    public ResponseEntity<GetAccountBalanceResponseDTO> depositAccountBalance(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody DepositAccountBalanceRequestDTO depositAccountBalanceRequestDTO
            ){
        UUID userId = JWTUtil.parseUserId(jwt);
        AccountBalance accountBalance = accountBalanceService
                .depositToAccountBalance(userId, depositAccountBalanceRequestDTO.balance());

        GetAccountBalanceResponseDTO response = accountBalanceMapper.toGetAccountBalanceResponseDTO(accountBalance);
        return ResponseEntity.ok(response);
    }
}
