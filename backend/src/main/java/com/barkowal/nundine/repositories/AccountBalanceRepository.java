package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, UUID> {
    Optional<AccountBalance> findByUserId(UUID userId);
}
