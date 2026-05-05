package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.entities.AccountBalance;
import com.barkowal.nundine.domain.entities.User;
import com.barkowal.nundine.exceptions.AccountBalanceNotFoundException;
import com.barkowal.nundine.exceptions.CreateAccountBalanceException;
import com.barkowal.nundine.repositories.AccountBalanceRepository;
import com.barkowal.nundine.services.AccountBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountBalanceServiceImpl implements AccountBalanceService {
    private final AccountBalanceRepository accountBalanceRepository;

    @Override
    public AccountBalance createAccountBalance(User user) {

        if(accountBalanceRepository.findByUserId(user.getId()).isPresent()){
            throw new CreateAccountBalanceException("User already has an account.");
        }

        AccountBalance newAccount = new AccountBalance();
        newAccount.setId(UUID.randomUUID());
        newAccount.setBalance(0L);
        newAccount.setUser(user);

        return accountBalanceRepository.save(newAccount);
    }

    @Override
    public Long getAccountBalance(UUID userId) {
        return accountBalanceRepository.findByUserId(userId).orElseThrow(() ->
            new AccountBalanceNotFoundException("Account balance not found.")).getBalance();
    }
}
