package org.example.online_food_storage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.online_food_storage.config.SwaggerConfig;
import org.example.online_food_storage.converter.ProductConverter;
import org.example.online_food_storage.dto.PageResponse;
import org.example.online_food_storage.dto.product.ProductCreateRequestDto;
import org.example.online_food_storage.dto.product.ProductResponseDto;
import org.example.online_food_storage.dto.product.ProductUpdateRequestDto;
import org.example.online_food_storage.model.Category;
import org.example.online_food_storage.model.Product;
import org.example.online_food_storage.service.CategoryService;
import org.example.online_food_storage.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@EnableMethodSecurity
@SecurityRequirement(name = SwaggerConfig.BEARER)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<?> create(@RequestBody @Validated ProductCreateRequestDto productCreateRequestDto) {
        Category categoryById = categoryService.getById(productCreateRequestDto.getCategoryId());
        Product product = ProductConverter.convertToToModel(productCreateRequestDto, categoryById);
        ProductResponseDto productResponseDto = ProductConverter.convertToResponseDto(productService.create(product));
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all products")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "12", required = false) int pageSize
    ) {
        PageResponse<ProductResponseDto> productResponseDtoPageResponse = ProductConverter.convertToPageResponse(productService.getAll(pageNum, pageSize));
        return ResponseEntity.ok().body(productResponseDtoPageResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by id")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        Product productById = productService.getById(id);
        productService.delete(productById);
        return ResponseEntity.ok().body("product deleted successfully");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the product by id")
    public ResponseEntity<?> updateById(
            @PathVariable Integer id,
            @RequestBody @Validated ProductUpdateRequestDto productUpdateRequestDto
    ) {
        Category categoryById = categoryService.getById(productUpdateRequestDto.getCategoryId());
        Product product = ProductConverter.convertToToModel(productUpdateRequestDto, categoryById);
        ProductResponseDto productResponseDto = ProductConverter.convertToResponseDto(productService.updateById(id, product));
        return ResponseEntity.ok().body(productResponseDto);
    }

    @GetMapping("/filter")
    @Operation(summary = "Get products by name or quantity or expiry date")
    public ResponseEntity<?> getProductsByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) LocalDateTime expiryDate
    ) {
        List<Product> products = productService.filterProducts(name, quantity, expiryDate);
        return ResponseEntity.ok().body(ProductConverter.convertToResponseDtos(products));
    }

}
