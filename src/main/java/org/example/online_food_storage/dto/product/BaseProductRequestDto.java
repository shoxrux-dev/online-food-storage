package org.example.online_food_storage.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.online_food_storage.constant.StatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseProductRequestDto {
    @NotBlank(message = "name must not be null!!!")
    String name;

    @NotNull(message = "status must not be null!!!")
    StatusEnum status;

    @NotNull(message = "product quantity must not be null!!!")
    Integer quantity;

    @NotNull(message = "product expiry date must not be null!!!")
    LocalDateTime expiryDate;

    @NotNull(message = "categoryId must not be null!!!")
    Integer categoryId;
}
