package com.barkowal.nundine.controllers;

import com.barkowal.nundine.domain.dtos.category.CategoryMapper;
import com.barkowal.nundine.domain.dtos.category.CreateCategoryRequestDTO;
import com.barkowal.nundine.domain.dtos.category.CreateCategoryResponseDTO;
import com.barkowal.nundine.domain.entities.Category;
import com.barkowal.nundine.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping()
    public ResponseEntity<CreateCategoryResponseDTO> createCategory(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO) {

        Category category = categoryService.createCategory(categoryMapper.fromDTO(createCategoryRequestDTO));
        CreateCategoryResponseDTO res = categoryMapper.toCreateCategoryResponseDTO(category);

        return ResponseEntity.ok(res);

    }
}
