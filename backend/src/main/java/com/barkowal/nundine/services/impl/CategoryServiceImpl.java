package com.barkowal.nundine.services.impl;

import com.barkowal.nundine.domain.dtos.category.CreateCategoryRequest;
import com.barkowal.nundine.domain.entities.Category;
import com.barkowal.nundine.exceptions.CategoryNotFoundException;
import com.barkowal.nundine.repositories.CategoryRepository;
import com.barkowal.nundine.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CreateCategoryRequest createCategoryRequest) {
        Category parentCategory = null;

        if (createCategoryRequest.parentId() != null) {
            parentCategory = categoryRepository.findById(createCategoryRequest.parentId())
                    .orElseThrow(
                            () -> new CategoryNotFoundException(
                                    String.format(
                                            "Parent category with id: %s not found",
                                            createCategoryRequest.parentId()))
                    );
        }

        Category newCategory = new Category();
        newCategory.setId(UUID.randomUUID());
        newCategory.setName(createCategoryRequest.name());
        newCategory.setParent(parentCategory);

        return categoryRepository.save(newCategory);
    }
}
