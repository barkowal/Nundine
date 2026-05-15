package com.barkowal.nundine.domain.dtos.product;

import com.barkowal.nundine.domain.dtos.category.GetCategoryResponseDTO;
import com.barkowal.nundine.domain.dtos.user.GetUserResponseDTO;
import com.barkowal.nundine.domain.entities.Product;
import com.barkowal.nundine.domain.entities.ProductStock;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    @Override
    public UpdateProductRequest toUpdateProductRequest(UpdateProductRequestDTO dto) {
        return new UpdateProductRequest(
                dto.productId(),
                dto.name(),
                dto.description(),
                dto.image(),
                dto.currentPrice(),
                dto.category()
        );
    }

    @Override
    public UpdateProductResponseDTO toUpdateProductResponseDTO(
            Product product,
            GetCategoryResponseDTO category,
            GetUserResponseDTO user
    ) {
        return new UpdateProductResponseDTO(
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

    // TODO: if there is no inventory, throw an exception?
    @Override
    public GetProductsStockResponseDTO toGetProductsStockResponseDTO(ProductStock productStock, GetCategoryResponseDTO category ) {

        if(productStock == null){
            throw new NullPointerException("Product stock cannot be null");
        }

        Product product = productStock.getProductId();
        Long quantity = productStock.getQuantity() != null? productStock.getQuantity() : 0L;
        UUID inventoryId = productStock.getInventoryId() != null? productStock.getInventoryId().getId() : null;

        return new GetProductsStockResponseDTO(
                product.getId(),
                inventoryId,
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCurrentPrice(),
                category,
                quantity
        );
    }

    @Override
    public UpdateProductStockRequest toUpdateProductStockRequest(UpdateProductStockRequestDTO dto) {
        return new UpdateProductStockRequest(dto.quantity());
    }

    @Override
    public GetShopProductResponseDTO toGetShopProductResponseDTO(ProductStock productStock, GetCategoryResponseDTO category) {
        Product product = productStock.getProductId();
        return new GetShopProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCurrentPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                category,
                productStock.getQuantity(),
                productStock.getInventoryId().getId(),
                productStock.getInventoryId().getOwner().getId()
        );
    }
}
