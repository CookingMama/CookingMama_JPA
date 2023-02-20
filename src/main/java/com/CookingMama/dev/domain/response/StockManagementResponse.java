package com.CookingMama.dev.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@AllArgsConstructor
public class StockManagementResponse {
    private String categoryName;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemImage;
    private String itemInfo;
    private Integer itemCount;
}
