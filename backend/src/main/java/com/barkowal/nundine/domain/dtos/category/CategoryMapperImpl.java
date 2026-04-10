package com.barkowal.nundine.domain.dtos.category;

import com.barkowal.nundine.domain.entities.Category;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CreateCategoryRequestDTO toDTO(CreateCategoryRequest createCategoryRequest) {
        return new CreateCategoryRequestDTO(createCategoryRequest.name(), createCategoryRequest.parentId());
    }

    @Override
    public CreateCategoryRequest fromDTO(CreateCategoryRequestDTO createCategoryRequestDTO) {
        return new CreateCategoryRequest(createCategoryRequestDTO.name(), createCategoryRequestDTO.parentId());
    }

    @Override
    public CreateCategoryResponseDTO toCreateCategoryResponseDTO(Category category) {
        Optional<UUID> parentID = getParentId(category);
        return new CreateCategoryResponseDTO(category.getId(), category.getName(), parentID);
    }

    @Override
    public GetCategoryResponseDTO toGetCategoryResponseDTO(Category category) {
        Optional<UUID> parentID = getParentId(category);
        String parentName = category.getParent() != null? category.getName() : "";
        return new GetCategoryResponseDTO(
                category.getId(),
                category.getName(),
                parentID,
                parentName
        );
    }

    private Optional<UUID> getParentId(Category category){
        Optional<UUID> parentID = Optional.empty();
        if (category.getParent() != null) {
            parentID = Optional.of(category.getParent().getId());
        }
        return parentID;
    }
}
