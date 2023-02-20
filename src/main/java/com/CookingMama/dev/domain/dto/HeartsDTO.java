package com.CookingMama.dev.domain.dto;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class HeartsDTO {
    private Integer id;
    private Integer userId;
    private Integer itemId;
    private Integer itemCount;
    private Integer itemPrice;
    private String itemOption;
}
