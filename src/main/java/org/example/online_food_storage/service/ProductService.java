package org.example.online_food_storage.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.online_food_storage.exception.RecordNotFoundException;
import org.example.online_food_storage.model.Product;
import org.example.online_food_storage.repository.ProductRepository;
import org.example.online_food_storage.validation.CommonSchemaValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    CommonSchemaValidator commonSchemaValidator;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Page<Product> getAll(int pageNum, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNum, pageSize);
        return productRepository.findAll(pageRequest);
    }

    public Product getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id));
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Product updateById(Integer id, Product product) {
        Product productById = getById(id);
        productById.setName(product.getName());
        productById.setQuantity(product.getQuantity());
        productById.setExpiryDate(product.getExpiryDate());
        productById.setStatus(product.getStatus());
        productById.setCategory(product.getCategory());
        return productRepository.save(productById);
    }

    public List<Product> filterProducts(String name, Integer quantity, LocalDateTime expiryDate) {
        if(name != null) {
            return productRepository.findByNameContaining(name);
        } else if(quantity != null) {
            return productRepository.findByQuantity(quantity);
        } else if(expiryDate != null) {
            return productRepository.findByExpiryDate(expiryDate);
        }
        return null;
    }

}
