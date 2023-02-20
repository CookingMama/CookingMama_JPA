package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AddHeartsRequest {
    private Integer userId;
    private Integer itemId;
    private Integer itemCount;
    private Integer itemPrice;
    private String itemOption;
}
