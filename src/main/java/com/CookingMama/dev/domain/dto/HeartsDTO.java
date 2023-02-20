package com.CookingMama.dev.domain.dto;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class HeartsDTO {
    private Long id;
    private Long userId;
    private Long itemId;
    private Integer itemCount;
    private Integer itemPrice;
    private String itemOption;
}
