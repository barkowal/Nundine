package com.barkowal.nundine.domain.dtos.accountBalance;

import com.barkowal.nundine.domain.entities.AccountBalance;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceMapperImpl implements AccountBalanceMapper{
    @Override
    public GetAccountBalanceResponseDTO toGetAccountBalanceResponseDTO(AccountBalance accountBalance) {
        return new GetAccountBalanceResponseDTO(
                accountBalance.getBalance()
        );
    }
}
