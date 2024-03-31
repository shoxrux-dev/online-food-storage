package org.example.online_food_storage.dto.category;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.online_food_storage.dto.BaseResponseDto;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponseDto extends BaseResponseDto implements Comparable<CategoryResponseDto> {
    String name;
    Integer productCount;

    @Override
    public int compareTo(CategoryResponseDto o) {
        return this.getId().compareTo(o.getId());
    }

}
