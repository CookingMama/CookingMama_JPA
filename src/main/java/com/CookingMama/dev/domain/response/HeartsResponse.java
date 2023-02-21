package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.dto.HeartsDTO;
import com.CookingMama.dev.domain.entity.Hearts;
import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class HeartsResponse {
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private Integer count;

    public HeartsResponse(Hearts hearts){
        this.itemName = hearts.getItem().getItemName();
        this.itemOption = hearts.getItem().getItemOption();
        this.itemPrice = hearts.getItem().getItemPrice();
        this.count = hearts.getCount();
    }
}
