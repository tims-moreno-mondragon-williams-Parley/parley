package com.parley.parley.repositories;

import com.parley.parley.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoriesById(Long id);
    Category findByName(String name);
}
