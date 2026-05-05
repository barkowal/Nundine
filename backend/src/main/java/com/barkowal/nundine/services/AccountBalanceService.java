package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.entities.AccountBalance;
import com.barkowal.nundine.domain.entities.User;

import java.util.UUID;

public interface AccountBalanceService {
    public AccountBalance createAccountBalance(User user);
    public Long getAccountBalance(UUID userId);
}
