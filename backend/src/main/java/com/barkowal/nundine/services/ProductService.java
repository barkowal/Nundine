package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.dtos.product.CreateProductRequest;
import com.barkowal.nundine.domain.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public Product createProduct(UUID supplier, CreateProductRequest createProductRequest);
    public List<Product> getProducts(UUID userId);
}
