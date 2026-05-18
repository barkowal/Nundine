package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.Inventory;
import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductStock;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, UUID> {
    Optional<ProductStock> findByIdProductIdAndIdInventoryId(UUID productId, UUID inventoryId);
    List<ProductStock> findAllByInventoryId(Inventory inventory);
    List<ProductStock> findAllByProductId(Product product);

    @Modifying
    @Transactional
    @Query("update ProductStock p set p.deletedAt = :localTime where p.id.productId = :productId and p.id.inventoryId= :inventoryId")
    void softDelete(@Param("productId") UUID productId, @Param("inventoryId") UUID inventoryId, @Param("localTime") LocalDateTime ts);
}
