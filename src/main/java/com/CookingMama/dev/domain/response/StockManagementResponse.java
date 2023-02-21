package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@AllArgsConstructor
public class StockManagementResponse {
    private String categoryName;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemImage;
    private Integer itemCount;

    public StockManagementResponse(Item item){
        this.categoryName = item.getCategory().getCategory_name();
        this.itemName = item.getItemName();
        this.itemOption = item.getItemOption();
        this.itemPrice = item.getItemPrice();
        this.itemImage = item.getItemImage();
        this.itemCount = item.getItemCount();
    }
}
