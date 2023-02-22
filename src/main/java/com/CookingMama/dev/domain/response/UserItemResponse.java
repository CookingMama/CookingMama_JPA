package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserItemResponse {
    private Long id;
    private String itemImage;
    private String itemName;
    private String adminName;
    private String categoryName;
    private String itemOption;
    private Integer itemPrice;
    private String itemInfo;
    private Integer itemCount;
    private Double grade;
    private Long reviewCount;
    private List<ReviewListResponse> reviews = new ArrayList<>();

    public UserItemResponse(Item item) {
        this.id = item.getId();
        this.itemImage = item.getItemImage();
        this.itemName = item.getItemName();
        this.adminName = item.getAdmin().getAdminName();
        this.categoryName = item.getCategory().getCategory_name();
        this.itemOption = item.getItemOption();
        this.itemPrice = item.getItemPrice();
        this.itemInfo = item.getItemInfo();
        this.itemCount = item.getItemCount();
        this.grade = item.getGrade();
        this.reviewCount = item.getReviewCount();
    }
}