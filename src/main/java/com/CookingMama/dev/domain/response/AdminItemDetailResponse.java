package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminItemDetailResponse {
    private String categoryName;
    private String itemImage;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemInfo;
    private Integer itemCount;
    private List<ReviewListResponse> reviews = new ArrayList<>();
    public AdminItemDetailResponse(Item item) {
        this.categoryName = item.getCategory().getCategory_name();
        this.itemImage = item.getItemImage();
        this.itemName = item.getItemName();
        this.itemOption = item.getItemOption();
        this.itemPrice = item.getItemPrice();
        this.itemInfo = item.getItemInfo();
        this.itemCount = item.getItemCount();
    }
}
