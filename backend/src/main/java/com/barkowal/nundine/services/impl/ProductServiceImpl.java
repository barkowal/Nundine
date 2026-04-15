package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.dtos.product.CreateProductRequest;
import com.barkowal.nundine.domain.entities.Category;
import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.User;
import com.barkowal.nundine.domain.specifications.ProductSpecs;
import com.barkowal.nundine.exceptions.CategoryNotFoundException;
import com.barkowal.nundine.exceptions.UserNotFoundException;
import com.barkowal.nundine.repositories.CategoryRepository;
import com.barkowal.nundine.repositories.ProductRepository;
import com.barkowal.nundine.repositories.UserRepository;
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
    public void deleteProduct(UUID productId, UUID userId){
        this.productRepository.findByIdAndSupplierId(productId, userId).ifPresent(this.productRepository::delete);
    }
}
