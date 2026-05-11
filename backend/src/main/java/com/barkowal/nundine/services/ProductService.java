package com.barkowal.nundine.services;

import com.barkowal.nundine.domain.dtos.product.CreateProductRequest;
import com.barkowal.nundine.domain.dtos.product.UpdateProductRequest;
import com.barkowal.nundine.domain.dtos.product.UpdateProductStockRequest;
import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductStock;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public Product createProduct(UUID supplier, CreateProductRequest createProductRequest);
    public List<Product> getProducts(UUID userId);
    public Product updateProduct(UUID supplier, UpdateProductRequest updateProductRequest);
    public void deleteProduct(UUID productId, UUID userId);
    public ProductStock getProductStock(UUID productId, UUID inventoryId);
    public void updateProductStock(UUID productId, UUID inventoryId, UpdateProductStockRequest updateProductStockRequest);
}
