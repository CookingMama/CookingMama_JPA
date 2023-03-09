package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.entity.ItemOption;
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
    private List<ItemOption> itemOption;
    private Integer itemPrice;
    private String itemInfo;
    private List<ReviewListResponse> reviews = new ArrayList<>();
    public AdminItemDetailResponse(Item item) {
        this.categoryName = item.getCategory().getCategoryName();
        this.itemImage = item.getItemImage();
        this.itemName = item.getItemName();
        this.itemOption = item.getItemOptionList();
        this.itemPrice = item.getItemPrice();
        this.itemInfo = item.getItemInfo();
    }
}
