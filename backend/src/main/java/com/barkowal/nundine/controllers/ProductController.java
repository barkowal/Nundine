package com.barkowal.nundine.controllers;

import com.barkowal.nundine.domain.dtos.category.CategoryMapper;
import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.dtos.product.CreateProductRequest;
import com.barkowal.nundine.domain.dtos.product.CreateProductRequestDTO;
import com.barkowal.nundine.domain.dtos.product.GetProductResponseDTO;
import com.barkowal.nundine.domain.dtos.product.ProductMapper;
import com.barkowal.nundine.domain.dtos.user.GetUserResponseDTO;
import com.barkowal.nundine.domain.dtos.user.UserMapper;
import com.barkowal.nundine.domain.entities.Product;
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
    private final ProductMapper productMapper;
    private final UserMapper userMapper;
    private final CategoryMapper categoryMapper;

    @GetMapping()
    public ResponseEntity<List<GetProductResponseDTO>> getProducts(){
        List<Product> products = productService.getProducts();
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
}
