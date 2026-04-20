package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.ProductPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory, UUID>, JpaSpecificationExecutor<ProductPriceHistory> {
    Optional<ProductPriceHistory> findFirstByProductIdOrderByToDateDesc(UUID productId);
}
