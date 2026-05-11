package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductStockRepository extends JpaRepository<ProductStock, UUID> {
    Optional<ProductStock> findByIdProductIdAndIdInventoryId(UUID productId, UUID inventoryId);
}
