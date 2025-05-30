package com.commerce.repository;

import com.commerce.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Long> {
    Category findByCategoryId(String categoryId);

}
