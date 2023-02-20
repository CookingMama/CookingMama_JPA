package com.CookingMama.dev.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class AdminItemResponse {
    private String categoryName;
    private String itemImage;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemInfo;
    private Integer itemCount;

    public AdminItemResponse(String categoryName, String itemImage, String itemName, String itemOption, Integer itemPrice) {
        this.categoryName = categoryName;
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemOption = itemOption;
        this.itemPrice = itemPrice;
    }
}
