package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.dtos.product.CreateProductRequest;
import com.barkowal.nundine.domain.dtos.product.UpdateProductRequest;
import com.barkowal.nundine.domain.dtos.product.UpdateProductStockRequest;
import com.barkowal.nundine.domain.entities.*;
import com.barkowal.nundine.domain.specifications.ProductSpecs;
import com.barkowal.nundine.exceptions.CategoryNotFoundException;
import com.barkowal.nundine.exceptions.ProductNotFoundException;
import com.barkowal.nundine.exceptions.UpdateProductException;
import com.barkowal.nundine.exceptions.UserNotFoundException;
import com.barkowal.nundine.repositories.CategoryRepository;
import com.barkowal.nundine.repositories.ProductRepository;
import com.barkowal.nundine.repositories.ProductStockRepository;
import com.barkowal.nundine.repositories.UserRepository;
import com.barkowal.nundine.services.InventoryService;
import com.barkowal.nundine.services.ProductPriceHistoryService;
import com.barkowal.nundine.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ProductStockRepository productStockRepository;
    private final ProductPriceHistoryService productPriceHistoryService;
    private final InventoryService inventoryService;

    @Override
    @Transactional
    public Product createProduct(UUID supplierID, CreateProductRequest createProductRequest) {

        User supplier = userRepository.findById(supplierID)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID '%s' not found", supplierID))
                );

        Category category = categoryRepository.findById(createProductRequest.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with ID '%s' not found", createProductRequest.categoryId()))
                );

        Product product = new Product();
        product.setName(createProductRequest.name());
        product.setDescription(createProductRequest.description());
        product.setImage(createProductRequest.image());
        product.setCurrentPrice(createProductRequest.currentPrice());
        product.setCategory(category);
        product.setSupplier(supplier);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts(UUID userId) {
        Specification<Product> spec = (root, query, cb) -> null;
        spec = spec.and(ProductSpecs.hasUserId(userId));
        return productRepository.findAll(spec);
    }

    @Override
    @Transactional
    public Product updateProduct(UUID supplierID, UpdateProductRequest updateProductRequest) {
        Category category = categoryRepository.findById(updateProductRequest.category())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with ID '%s' not found", updateProductRequest.category()))
                );

        UUID productId = updateProductRequest.id();
        if(productId == null){
            throw new UpdateProductException("Product id cannot be null.");
        }

        Product existingProduct = productRepository
                .findByIdAndSupplierId(productId, supplierID)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with ID '%s' does not exist", productId))
                );

        // if price changes it should create a new record in price history
        if(!existingProduct.getCurrentPrice().equals(updateProductRequest.currentPrice())){
            productPriceHistoryService.addNewProductPriceRecord(existingProduct);
        }

        existingProduct.setName(updateProductRequest.name());
        existingProduct.setDescription(updateProductRequest.description());
        existingProduct.setImage(updateProductRequest.image());
        existingProduct.setCurrentPrice(updateProductRequest.currentPrice());
        existingProduct.setCategory(category);

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId, UUID userId){
        this.productRepository.findByIdAndSupplierId(productId, userId).ifPresent(this.productRepository::delete);
    }

    @Override
    public ProductStock getProductStock(UUID productId, UUID inventoryId) {
        return productStockRepository.findByIdProductIdAndIdInventoryId(productId, inventoryId).orElse(null);
    }

    // NOTE: On first update, if there is no product stock,
    // it should generate a new row
    @Override
    @Transactional
    public void updateProductStock(UUID productId, UUID inventoryId, UpdateProductStockRequest updateProductStockRequest) {
        ProductStock productStock = productStockRepository.findByIdProductIdAndIdInventoryId(productId, inventoryId).orElse(null);

        if(productStock == null){
            productStock = createProductStock(productId, inventoryId);
        }

        productStock.setQuantity(updateProductStockRequest.quantity());
        productStockRepository.save(productStock);

    }

    @Transactional
    private ProductStock createProductStock(UUID productId, UUID inventoryId){
        Product product = productRepository.findById(productId).orElseThrow(()->
            new ProductNotFoundException("Product not found!"));
        Inventory inventory = inventoryService.getInventoryByInventoryId(inventoryId);

        ProductStockKey key = new ProductStockKey();
        key.setProductId(productId);
        key.setInventoryId(inventoryId);

        ProductStock productStock = new ProductStock();
        productStock.setId(key);
        productStock.setQuantity(0);
        productStock.setProductId(product);
        productStock.setInventoryId(inventory);

        productStockRepository.save(productStock);

        return productStock;
    }


}
