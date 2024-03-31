package org.example.online_food_storage.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.online_food_storage.config.token.JwtService;
import org.example.online_food_storage.converter.UserConverter;
import org.example.online_food_storage.dto.user.UserLoginRequestDto;
import org.example.online_food_storage.dto.user.UserResponseDto;
import org.example.online_food_storage.model.User;
import org.example.online_food_storage.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;
    JwtService jwtService;

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<?> login(@RequestBody @Validated UserLoginRequestDto userLoginRequestDto) {
        User user = UserConverter.convertToModel(userLoginRequestDto);
        UserResponseDto userResponseDto = UserConverter.convertToResponseDto(authService.login(user));
        userResponseDto.setToken(jwtService.generateToken(user));
        return ResponseEntity.ok().body(userResponseDto);
    }

}
