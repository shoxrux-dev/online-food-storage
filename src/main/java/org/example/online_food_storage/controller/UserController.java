package org.example.online_food_storage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.online_food_storage.config.SwaggerConfig;
import org.example.online_food_storage.converter.UserConverter;
import org.example.online_food_storage.dto.user.UpdateUserRequestDto;
import org.example.online_food_storage.dto.user.UserCreateRequestDto;
import org.example.online_food_storage.dto.user.UserResponseDto;
import org.example.online_food_storage.dto.user.UserResponseDtoForSuperAdmin;
import org.example.online_food_storage.model.User;
import org.example.online_food_storage.service.RoleService;
import org.example.online_food_storage.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    RoleService roleService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Registration a new user")
    public ResponseEntity<?> register(@RequestBody @Validated UserCreateRequestDto userCreateRequestDto) {
        User user = UserConverter.convertToModel(userCreateRequestDto);
        UserResponseDtoForSuperAdmin userResponseDtoForSuperAdmin = UserConverter.convertToResponseDtoForSuperAdmin(userService.register(user, userCreateRequestDto.getRolesId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDtoForSuperAdmin);
    }

    @GetMapping("/get-by-username/{username}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get user by username")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        UserResponseDtoForSuperAdmin userResponseDtoForSuperAdmin = UserConverter.convertToResponseDtoForSuperAdmin(userService.getByUsername(username));
        return ResponseEntity.ok().body(userResponseDtoForSuperAdmin);
    }

    @GetMapping("/get-by-id/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get user by id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        UserResponseDtoForSuperAdmin userResponseDtoForSuperAdmin = UserConverter.convertToResponseDtoForSuperAdmin(userService.getById(id));
        return ResponseEntity.ok().body(userResponseDtoForSuperAdmin);
    }

    @GetMapping("/get-roles")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "Get all roles")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(roleService.getAll());
    }

    @PutMapping
    @Operation(summary = "Update user(me)")
    public ResponseEntity<?> update(@RequestBody @Validated UpdateUserRequestDto updateUserRequestDto) {
        User user = UserConverter.convertToModel(updateUserRequestDto);
        UserResponseDto userResponseDto = UserConverter.convertToResponseDto(userService.update(user));
        return ResponseEntity.ok().body(userResponseDto);
    }

}
