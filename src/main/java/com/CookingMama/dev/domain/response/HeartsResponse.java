package com.CookingMama.dev.domain.response;

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
        this.itemName = hearts.getItemOption().getItem().getItemName();
        this.itemOption = hearts.getItemOption().getOption();
        this.itemPrice = hearts.getItemOption().getItem().getItemPrice();
        this.count = hearts.getCount();
    }
}
