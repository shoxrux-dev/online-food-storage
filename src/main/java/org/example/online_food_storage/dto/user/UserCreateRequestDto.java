package org.example.online_food_storage.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.online_food_storage.model.Role;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequestDto extends BaseUserRequestDto {
    @NotEmpty
    List<Integer> rolesId;
}
