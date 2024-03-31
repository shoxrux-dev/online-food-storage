package org.example.online_food_storage.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.online_food_storage.config.SwaggerConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
public class TestController {
    @GetMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String get() {
        return "ishladi";
    }
}
