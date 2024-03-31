package org.example.online_food_storage.converter;

import lombok.experimental.UtilityClass;
import org.example.online_food_storage.dto.user.*;
import org.example.online_food_storage.model.User;

@UtilityClass
public class UserConverter {
    public <T extends BaseUserRequestDto> User convertToModel(T request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }

    public  User convertToModel(UserLoginRequestDto userLoginRequestDto) {
        return User.builder()
                .username(userLoginRequestDto.getUsername())
                .password(userLoginRequestDto.getPassword())
                .build();
    }

    public UserResponseDto convertToResponseDto(User user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .roles(user.getRoles())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .createdBy(user.getCreatedBy())
                .lastModifiedBy(user.getLastModifiedBy())
                .build();
    }

    public UserResponseDtoForSuperAdmin convertToResponseDtoForSuperAdmin(User user) {
        return UserResponseDtoForSuperAdmin.builder()
                .username(user.getUsername())
                .roles(user.getRoles())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .createdBy(user.getCreatedBy())
                .lastModifiedBy(user.getLastModifiedBy())
                .build();
    }

}
