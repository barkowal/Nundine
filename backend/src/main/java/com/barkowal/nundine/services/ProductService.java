package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.dtos.product.CreateProductRequestDTO;
import com.barkowal.nundine.domain.entities.Product;

import java.util.UUID;

public interface ProductService {
    public Product createProduct(UUID supplierId, CreateProductRequestDTO createProductRequestDTO);
}
