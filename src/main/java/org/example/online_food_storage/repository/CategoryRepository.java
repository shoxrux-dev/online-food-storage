package org.example.online_food_storage.repository;

import org.example.online_food_storage.constant.StatusEnum;
import org.example.online_food_storage.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryByName(String categoryName);
    List<Category> findCategoriesByStatus(StatusEnum status);
}
