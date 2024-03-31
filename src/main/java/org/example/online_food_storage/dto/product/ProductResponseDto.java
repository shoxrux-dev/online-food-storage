package org.example.online_food_storage.dto.product;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.online_food_storage.dto.BaseResponseDto;
import org.example.online_food_storage.dto.category.CategoryResponseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto extends BaseResponseDto implements Comparable<ProductResponseDto> {
    String name;
    Integer quantity;
    LocalDateTime expiryDate;
    CategoryResponseDto categoryResponseDto;

    @Override
    public int compareTo(ProductResponseDto o) {
        return this.getId().compareTo(o.getId());
    }
}
