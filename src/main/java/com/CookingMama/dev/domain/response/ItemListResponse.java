package com.CookingMama.dev.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class ItemListResponse {
    private Long id;
    private String itemImage;
    private String itemName;
    private Long adminId;
    private String adminName;
    private String categoryName;
    private Integer itemPrice;
}
