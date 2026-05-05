package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.entities.Inventory;
import com.barkowal.nundine.domain.entities.User;

public interface InventoryService {
    public Inventory createInventory(User user);
}
