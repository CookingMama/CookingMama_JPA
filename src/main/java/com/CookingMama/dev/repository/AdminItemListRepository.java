package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.response.ItemListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminItemListRepository extends JpaRepository<Item, Long> {
    List<Item> findByAdminId(Long adminId);

    Item findByAdminIdAndId(Long adminId, Long id);
}
