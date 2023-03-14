package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.ItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
//    ItemOption findByItem(Item item);

    List<ItemOption> findByItemId(Long id);
//    @Query("select ItemOption from ItemOption where item_id = :itemId and item_option = :itemOption")
    ItemOption findByItemIdAndOption(Long itemId, String itemOption);

}
