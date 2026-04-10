package com.barkowal.nundine.domain.dtos.category;

import com.barkowal.nundine.domain.entities.Category;

import java.util.List;

public interface CategoryMapper{
    CreateCategoryRequestDTO toDTO(CreateCategoryRequest createCategoryRequest);
    CreateCategoryRequest fromDTO(CreateCategoryRequestDTO createCategoryRequestDTO);

    CreateCategoryResponseDTO toCreateCategoryResponseDTO(Category category);

    GetCategoryResponseDTO toGetCategoryResponseDTO(Category category);

}
