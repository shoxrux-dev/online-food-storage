package org.example.online_food_storage.repository;

import org.example.online_food_storage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByName(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByQuantity(Integer quantity);
    List<Product> findByExpiryDate(LocalDateTime expiryDate);
}
