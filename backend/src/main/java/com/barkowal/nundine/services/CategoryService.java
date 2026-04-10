package com.barkowal.nundine.services;


import com.barkowal.nundine.domain.dtos.category.CreateCategoryRequest;
import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CreateCategoryRequest createCategoryRequest);
    List<Category> getCategories();
}