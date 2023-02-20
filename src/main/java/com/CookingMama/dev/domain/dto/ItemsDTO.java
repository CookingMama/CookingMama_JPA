package com.CookingMama.dev.domain.dto;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
@Builder
public class ItemsDTO {
    private Integer id;
    private Integer adminId;
    private Integer category;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemImage;
    private String itemInfo;
    private Integer itemCount;
}
