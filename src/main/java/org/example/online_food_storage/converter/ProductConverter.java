package org.example.online_food_storage.converter;

import lombok.experimental.UtilityClass;
import org.example.online_food_storage.dto.PageResponse;
import org.example.online_food_storage.dto.product.BaseProductRequestDto;
import org.example.online_food_storage.dto.product.ProductResponseDto;
import org.example.online_food_storage.model.Category;
import org.example.online_food_storage.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProductConverter {
    public <T extends BaseProductRequestDto> Product convertToToModel(T request, Category category) {
        return Product.builder()
                .name(request.getName())
                .status(request.getStatus())
                .quantity(request.getQuantity())
                .expiryDate(request.getExpiryDate())
                .category(category)
                .build();
    }

    public ProductResponseDto convertToResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .expiryDate(product.getExpiryDate())
                .status(product.getStatus())
                .categoryResponseDto(CategoryConverter.convertToResponseDto(product.getCategory()))
                .createdAt(product.getCreatedAt())
                .createdBy(product.getCreatedBy())
                .updatedAt(product.getUpdatedAt())
                .lastModifiedBy(product.getLastModifiedBy())
                .build();
    }

    public List<ProductResponseDto> convertToResponseDtos(List<Product> products) {
        return products.stream()
                .map(ProductConverter::convertToResponseDto)
                .sorted()
                .collect(Collectors.toList());
    }

    public PageResponse<ProductResponseDto> convertToPageResponse(Page<Product> products) {
        return new PageResponse<>(
                ProductConverter.convertToResponseDtos(products.getContent()),
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast()
        );
    }

}
