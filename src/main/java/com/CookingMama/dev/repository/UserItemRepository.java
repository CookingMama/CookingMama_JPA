package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserItemRepository extends JpaRepository<Item, Long> {

    List<Item> findTop100ByOrderById();
}
