package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Hearts;
import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.entity.ItemOption;
import lombok.*;

import java.util.List;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class HeartsResponse {
    private Long heartsId;
    private String itemOption;
    private Integer count;
    private String itemName;
    private Integer itemPrice;

    public HeartsResponse(Hearts hearts){
        this.heartsId = hearts.getId();
        this.itemOption = hearts.getOption();
        this.count = hearts.getCount();
        this.itemPrice = hearts.getItem().getItemPrice();
        this.itemName = hearts.getItem().getItemName();
    }
}
