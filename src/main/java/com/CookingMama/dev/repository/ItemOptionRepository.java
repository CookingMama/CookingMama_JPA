package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.entity.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
    ItemOption findByItem(Item item);
}
