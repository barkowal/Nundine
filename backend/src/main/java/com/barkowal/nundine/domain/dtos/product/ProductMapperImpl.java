package com.barkowal.nundine.domain.dtos.product;

import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.dtos.user.GetUserResponseDTO;
import com.barkowal.nundine.domain.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper{

    @Override
    public CreateProductRequest toCreateProductRequest(CreateProductRequestDTO dto) {
        return new CreateProductRequest(
                dto.name(),
                dto.description(),
                dto.image(),
                dto.currentPrice(),
                dto.category()
        );
    }

    @Override
    public GetProductResponseDTO toGetProductResponseDTO(
            Product product,
            GetCategoryResponseDTO category,
            GetUserResponseDTO user
           ) {
        return new GetProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCurrentPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                category,
                user
        );
    }
}
