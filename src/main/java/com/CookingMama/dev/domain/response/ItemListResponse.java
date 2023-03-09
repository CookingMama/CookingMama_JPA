package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemListResponse {
    private Long id;
    private String adminName;
    private String categoryName;
    private String itemName;
    private Integer itemPrice;
    private String itemImage;
    private Double grade;
    private Long reviewCount;

    public ItemListResponse(Item item) {
        this.id = item.getId();
        this.adminName = item.getAdmin().getAdminName();
        this.itemName = item.getItemName();
        this.itemImage = item.getItemImage();
        this.itemPrice = item.getItemPrice();
        this.categoryName = item.getCategory().getCategoryName();
        this.reviewCount = item.getReviewCount();
        this.grade = item.getGrade();
    }
}