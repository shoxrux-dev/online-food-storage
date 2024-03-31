package org.example.online_food_storage.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.online_food_storage.constant.StatusEnum;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseCategoryRequestDto {
    @NotBlank(message = "name must not be null!!!")
    String name;

    @NotNull(message = "status must not be null!!!")
    StatusEnum status;
}
