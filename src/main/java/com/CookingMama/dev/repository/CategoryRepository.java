package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
