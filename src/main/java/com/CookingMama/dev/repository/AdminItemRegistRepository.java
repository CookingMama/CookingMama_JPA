package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminItemRegistRepository extends JpaRepository<Item, Long> {
}
