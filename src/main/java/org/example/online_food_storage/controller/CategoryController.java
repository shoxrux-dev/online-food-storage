package org.example.online_food_storage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.online_food_storage.config.SwaggerConfig;
import org.example.online_food_storage.converter.CategoryConverter;
import org.example.online_food_storage.dto.PageResponse;
import org.example.online_food_storage.dto.category.CategoryCreateRequestDto;
import org.example.online_food_storage.dto.category.CategoryResponseDto;
import org.example.online_food_storage.dto.category.CategoryUpdateRequestDto;
import org.example.online_food_storage.model.Category;
import org.example.online_food_storage.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<?> create(@RequestBody @Validated CategoryCreateRequestDto categoryCreateRequestDto) {
        Category category = CategoryConverter.convertToModel(categoryCreateRequestDto);
        CategoryResponseDto categoryResponseDto = CategoryConverter.convertToResponseDto(categoryService.create(category));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDto);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all categories")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "12", required = false) int pageSize
    ) {
        PageResponse<CategoryResponseDto> categoryResponseDtoPageResponse = CategoryConverter.convertToPageResponse(categoryService.getAll(pageNum, pageSize));
        return ResponseEntity.ok().body(categoryResponseDtoPageResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category by id")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        Category categoryById = categoryService.getById(id);
        categoryService.delete(categoryById);
        return ResponseEntity.ok().body("category deleted successfully");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the category by id")
    public ResponseEntity<?> updateById(
            @PathVariable Integer id,
            @RequestBody @Validated CategoryUpdateRequestDto categoryUpdateRequestDto
    ) {
        Category category = CategoryConverter.convertToModel(categoryUpdateRequestDto);
        CategoryResponseDto categoryResponseDto = CategoryConverter.convertToResponseDto(categoryService.updateById(id, category));
        return  ResponseEntity.ok().body(categoryResponseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    public ResponseEntity<?> getByIdOrName(
            @PathVariable Integer id
    ) {
        Category categoryById = categoryService.getById(id);
        return ResponseEntity.ok().body(CategoryConverter.convertToResponseDto(categoryById));
    }

}
