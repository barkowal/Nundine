package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.entities.User;
import com.barkowal.nundine.repositories.UserRepository;
import com.barkowal.nundine.services.AccountBalanceService;
import com.barkowal.nundine.services.InventoryService;
import com.barkowal.nundine.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final InventoryService inventoryService;
    private final AccountBalanceService accountBalanceService;

    @Override
    public boolean userExists(UUID userId) {
        return userRepository.existsById(userId);
    }

    @Override
    @Transactional
    public User createUser(UUID userId, Jwt jwt) {

        User user = new User();
        user.setId(userId);
        user.setName(jwt.getClaimAsString("preferred_username"));
        user.setEmail(jwt.getClaimAsString("email"));

        userRepository.save(user);

        accountBalanceService.createAccountBalance(user);
        inventoryService.createInventory(user);

        return user;
    }
}
