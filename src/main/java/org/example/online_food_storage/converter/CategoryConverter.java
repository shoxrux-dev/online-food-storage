package org.example.online_food_storage.converter;

import lombok.experimental.UtilityClass;
import org.example.online_food_storage.dto.PageResponse;
import org.example.online_food_storage.dto.category.BaseCategoryRequestDto;
import org.example.online_food_storage.dto.category.CategoryResponseDto;
import org.example.online_food_storage.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CategoryConverter {
    public <T extends BaseCategoryRequestDto> Category convertToModel(T request) {
        return Category.builder()
                .name(request.getName())
                .status(request.getStatus())
                .build();
    }

    public CategoryResponseDto convertToResponseDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus())
                .productCount(category.getProducts() != null ? category.getProducts().size() : 0)
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .createdBy(category.getCreatedBy())
                .lastModifiedBy(category.getLastModifiedBy())
                .build();
    }

    public List<CategoryResponseDto> convertToResponseDtos(List<Category> categories) {
        return categories.stream()
                .map(CategoryConverter::convertToResponseDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public PageResponse<CategoryResponseDto> convertToPageResponse(Page<Category> categories) {
        return new PageResponse<>(
                CategoryConverter.convertToResponseDtos(categories.getContent()),
                categories.getNumber(),
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages(),
                categories.isLast()
        );
    }

}
