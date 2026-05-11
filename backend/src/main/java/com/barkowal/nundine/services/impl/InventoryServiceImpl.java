package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.entities.Inventory;
import com.barkowal.nundine.domain.entities.User;
import com.barkowal.nundine.exceptions.CreateAccountBalanceException;
import com.barkowal.nundine.exceptions.InventoryNotFoundException;
import com.barkowal.nundine.repositories.InventoryRepository;
import com.barkowal.nundine.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory createInventory(User user) {

        if(inventoryRepository.findByOwnerId(user.getId()).isPresent()){
            throw new CreateAccountBalanceException("User already has an inventory.");
        }

        Inventory newInventory = new Inventory();
        newInventory.setId(UUID.randomUUID());
        newInventory.setOwner(user);

        return inventoryRepository.save(newInventory);
    }

    @Override
    public Inventory getInventory(UUID userId) {
        return inventoryRepository.findByOwnerId(userId).orElseThrow(()->
                new InventoryNotFoundException("User's inventory not found."));
    }

    @Override
    public Inventory getInventoryByInventoryId(UUID inventoryId) {
        return inventoryRepository.findById(inventoryId).orElseThrow(()->
                new InventoryNotFoundException("Inventory not found."));
    }
}
