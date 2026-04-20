package com.barkowal.nundine.domain.dtos.product;

import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.dtos.user.GetUserResponseDTO;
import com.barkowal.nundine.domain.entities.Product;

public interface ProductMapper {
    CreateProductRequest toCreateProductRequest(CreateProductRequestDTO dto);

    GetProductResponseDTO toGetProductResponseDTO(
            Product product,
            GetCategoryResponseDTO category,
            GetUserResponseDTO user
    );

    UpdateProductRequest toUpdateProductRequest(UpdateProductRequestDTO dto);
    UpdateProductResponseDTO toUpdateProductResponseDTO(
            Product product,
            GetCategoryResponseDTO category,
            GetUserResponseDTO user
    );
}
