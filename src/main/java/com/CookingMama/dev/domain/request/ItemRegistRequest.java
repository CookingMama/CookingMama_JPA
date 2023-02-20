package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class ItemRegistRequest {
    private Integer adminId;
    private Integer category;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemImage;
    private String itemInfo;
    private Integer itemCount;
}
