package com.barkowal.nundine.services;


import com.barkowal.nundine.domain.dtos.category.CreateCategoryRequest;
import com.barkowal.nundine.domain.entities.Category;

public interface CategoryService {
    Category createCategory(CreateCategoryRequest createCategoryRequest);
}