package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
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
    }
}
