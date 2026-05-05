package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    Optional<Inventory> findByOwnerId(UUID ownerId);

}
