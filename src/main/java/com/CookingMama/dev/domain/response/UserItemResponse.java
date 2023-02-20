package com.CookingMama.dev.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserItemResponse {
    private Integer id;
    private String itemImage;
    private String itemName;
    private String adminName;
    private String categoryName;
    private String itemOption;
    private Integer itemPrice;
    private String itemInfo;
    private Integer itemCount;
}
