package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminItemListResponse {
    private String adminName;
    private String categoryName;
    private String itemName;
    private Integer itemPrice;
    private String itemImage;

    public AdminItemListResponse(Item item) {
        this.adminName = item.getAdmin().getAdminName();
        this.itemName = item.getItemName();
        this.itemImage = item.getItemImage();
        this.itemPrice = item.getItemPrice();
        this.categoryName = item.getCategory().getCategory_name();
    }
}
