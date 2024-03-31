package org.example.online_food_storage.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.example.online_food_storage.dto.BaseResponseDto;
import org.example.online_food_storage.model.Role;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto extends BaseResponseDto {
    String username;
    List<Role> roles;
    String token;
}
