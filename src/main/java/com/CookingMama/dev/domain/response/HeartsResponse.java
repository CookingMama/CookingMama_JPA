package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.dto.HeartsDTO;
import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class HeartsResponse {
    private Integer itemId;
    private Integer itemCount;
    private Integer itemPrice;
    private String itemOption;
    public HeartsResponse(HeartsDTO hearts){
        this.itemId = hearts.getItemId();
        this.itemCount = hearts.getItemCount();
        this.itemPrice = hearts.getItemPrice();
        this.itemOption = hearts.getItemOption();
    }
}
