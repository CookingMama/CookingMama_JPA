package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.entity.ItemOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter@Setter@ToString
@AllArgsConstructor
public class StockManagementResponse {
    private String categoryName;
    private String itemName;
    private List<ItemOption> itemOption;
    private Integer itemPrice;
    private String itemImage;

    public StockManagementResponse(Item item){
        this.categoryName = item.getCategory().getCategory_name();
        this.itemName = item.getItemName();
        this.itemOption = item.getItemOptionList();
        this.itemPrice = item.getItemPrice();
        this.itemImage = item.getItemImage();
    }
}
