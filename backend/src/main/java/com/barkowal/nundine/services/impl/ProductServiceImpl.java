package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.dtos.product.CreateProductRequestDTO;
import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.repositories.ProductRepository;
import com.barkowal.nundine.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(UUID supplierId, CreateProductRequestDTO createProductRequestDTO) {
        return null;
    }
}
