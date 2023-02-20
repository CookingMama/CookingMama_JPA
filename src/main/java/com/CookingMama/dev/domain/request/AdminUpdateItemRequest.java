package com.CookingMama.dev.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class AdminUpdateItemRequest {
    private Integer adminId;
    private Integer itemId;
    private Integer category;
    private String itemImage;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemInfo;
    private Integer itemCount;
}
