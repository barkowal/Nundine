package com.barkowal.nundine.domain.dtos.accountBalance;

import com.barkowal.nundine.domain.entities.AccountBalance;

public interface AccountBalanceMapper {
    GetAccountBalanceResponseDTO toGetAccountBalanceResponseDTO(AccountBalance accountBalance);
}
