package com.barkowal.nundine.domain.dtos.accountBalance;

import jakarta.validation.constraints.NotNull;

public record DepositAccountBalanceRequestDTO(
        @NotNull(message = "Balance is required") Long balance
) {
}
