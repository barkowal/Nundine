package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    Optional<Inventory> findByOwnerId(UUID ownerId);

    @Query(value = "SELECT i.* FROM Inventories i INNER JOIN Users u ON u.id = i.owner_id WHERE :role = ANY(u.roles::text[])",
    nativeQuery = true)
    List<Inventory> findAllByOwnerRoleMember(@Param("role") String role);
}
