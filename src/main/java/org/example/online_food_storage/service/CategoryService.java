package org.example.online_food_storage.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.online_food_storage.constant.StatusEnum;
import org.example.online_food_storage.exception.RecordNotFoundException;
import org.example.online_food_storage.model.Category;
import org.example.online_food_storage.repository.CategoryRepository;
import org.example.online_food_storage.validation.CommonSchemaValidator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CommonSchemaValidator commonSchemaValidator;

    public Category create(Category category) {
        commonSchemaValidator.categoryNotExists(category.getName());
        return categoryRepository.save(category);
    }

    public Page<Category> getAll(int pageNum, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNum, pageSize);
        return categoryRepository.findAll(pageRequest);
    }

    public Category getById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category not found with id: " + id));
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public Category updateById(Integer id, Category category) {
        Category categoryById = getById(id);
        categoryById.setName(category.getName());
        categoryById.setStatus(category.getStatus());
        return categoryRepository.save(categoryById);
    }

}
