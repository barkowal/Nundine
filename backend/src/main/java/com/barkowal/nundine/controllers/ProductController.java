package com.barkowal.nundine.controllers;

import com.barkowal.nundine.domain.dtos.category.CategoryMapper;
import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.dtos.product.*;
import com.barkowal.nundine.domain.dtos.user.GetUserResponseDTO;
import com.barkowal.nundine.domain.dtos.user.UserMapper;
import com.barkowal.nundine.domain.entities.Inventory;
import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductStock;
import com.barkowal.nundine.services.InventoryService;
import com.barkowal.nundine.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import com.barkowal.nundine.utils.JWTUtil;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final ProductMapper productMapper;
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    @GetMapping()
    public ResponseEntity<List<GetProductResponseDTO>> getProducts(
            @RequestParam(required = false, name = "userId") UUID userId
    ){
        List<Product> products = productService.getProducts(userId);
        List<GetProductResponseDTO> response = products.stream()
                .map(product -> {
                    GetUserResponseDTO userResponse = userMapper.toGetUserResponseDTO(product.getSupplier());
                    GetCategoryResponseDTO categoryResponseDTO = categoryMapper.toGetCategoryResponseDTO(product.getCategory());
                    return productMapper.toGetProductResponseDTO(product, categoryResponseDTO, userResponse);
                }).toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateProductRequestDTO createProductRequestDTO) {

        UUID userId = JWTUtil.parseUserId(jwt);
        CreateProductRequest createProductRequest = productMapper.toCreateProductRequest(createProductRequestDTO);
        Product res = productService.createProduct(userId, createProductRequest);

        return ResponseEntity.ok(res);
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<UpdateProductResponseDTO> updateProduct(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody UpdateProductRequestDTO updateProductRequestDTO
    ){

        UUID supplierId = JWTUtil.parseUserId(jwt);
        UpdateProductRequest updateProductRequest = productMapper.toUpdateProductRequest(updateProductRequestDTO);
        Product updatedProduct = productService.updateProduct(supplierId, updateProductRequest);

        GetUserResponseDTO userResponse = userMapper.toGetUserResponseDTO(updatedProduct.getSupplier());
        GetCategoryResponseDTO categoryResponseDTO = categoryMapper.toGetCategoryResponseDTO(updatedProduct.getCategory());
        UpdateProductResponseDTO res = productMapper.toUpdateProductResponseDTO(updatedProduct, categoryResponseDTO, userResponse);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID productId
    ){
        UUID userId = JWTUtil.parseUserId(jwt);
        this.productService.deleteProduct(productId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/stock")
    public ResponseEntity<List<GetProductsStockResponseDTO>> getProductsStock(
            @AuthenticationPrincipal Jwt jwt
    ){
        UUID userId = JWTUtil.parseUserId(jwt);
        List<Product> products = productService.getProducts(userId);
        Inventory userInventory = inventoryService.getInventory(userId);

        List<GetProductsStockResponseDTO> response = products.stream()
                .map(product -> {
                    GetCategoryResponseDTO categoryResponseDTO = categoryMapper.toGetCategoryResponseDTO(product.getCategory());
                    ProductStock stock = productService.getProductStock(product.getId(), userInventory.getId());
                    return productMapper.toGetProductsStockResponseDTO(product, categoryResponseDTO, stock);
                }).toList();

        return ResponseEntity.ok(response);
    }

    // NOTE: I think it's better to get inventoryId from jwt token,
    // so only the inventory owner can update the product stock
    @PutMapping(path = "/stock/{productId}")
    public ResponseEntity<Void> updateProductStock(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID productId,
            @Valid @RequestBody UpdateProductStockRequestDTO updateProductStockRequestDTO) {

        UUID userId = JWTUtil.parseUserId(jwt);
        Inventory userInventory = inventoryService.getInventory(userId);
        UpdateProductStockRequest updateProductStockRequest =
                productMapper.toUpdateProductStockRequest(updateProductStockRequestDTO);

        productService.updateProductStock(productId, userInventory.getId(), updateProductStockRequest);

        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/shop")
    public ResponseEntity<List<GetShopProductResponseDTO>> getShopProducts(){
        List<Inventory> sellerInventories = inventoryService.getInventoriesByOwnerRole("seller");
        List<ProductStock> stocks = sellerInventories.stream()
                .flatMap(inventory ->
                        productService.getAllProductStocksByInventory(inventory).stream()).toList();

        List<GetShopProductResponseDTO> response = stocks.stream()
                .map(stock -> {
                    GetCategoryResponseDTO categoryResponseDTO = categoryMapper.toGetCategoryResponseDTO(stock.getProductId().getCategory());
                    return productMapper.toGetShopProductResponseDTO(stock, categoryResponseDTO);
                }).toList();

        return ResponseEntity.ok(response);
    }
}
