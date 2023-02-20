package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<Item, Long> {

}
