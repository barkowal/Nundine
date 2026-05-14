package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.Inventory;
import com.barkowal.nundine.domain.entities.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, UUID> {
    Optional<ProductStock> findByIdProductIdAndIdInventoryId(UUID productId, UUID inventoryId);
    List<ProductStock> findAllByInventoryId(Inventory inventory);
}
