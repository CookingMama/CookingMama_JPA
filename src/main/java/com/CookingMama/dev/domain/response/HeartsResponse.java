package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.dto.HeartsDTO;
import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class HeartsResponse {
    private Long itemId;
    private Integer itemCount;
    private Integer itemPrice;
    private String itemOption;
    public HeartsResponse(HeartsDTO heartsDTO){
        this.itemId = heartsDTO.getItemId();
        this.itemCount = heartsDTO.getItemCount();
        this.itemPrice = heartsDTO.getItemPrice();
        this.itemOption = heartsDTO.getItemOption();
    }
}
