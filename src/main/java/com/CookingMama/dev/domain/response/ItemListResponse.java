package com.CookingMama.dev.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemListResponse {
    private Integer id;
    private String itemImage;
    private String itemName;
    private String adminName;
    private String categoryName;
    private Integer itemPrice;
}
