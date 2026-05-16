package com.barkowal.nundine.repositories;

import com.barkowal.nundine.domain.entities.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProducts, UUID> {
}
