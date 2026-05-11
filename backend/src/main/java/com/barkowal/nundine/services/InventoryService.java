package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.entities.Inventory;
import com.barkowal.nundine.domain.entities.User;

import java.util.UUID;

public interface InventoryService {
    public Inventory createInventory(User user);
    public Inventory getInventory(UUID userId);
    public Inventory getInventoryByInventoryId(UUID inventoryId);
}
