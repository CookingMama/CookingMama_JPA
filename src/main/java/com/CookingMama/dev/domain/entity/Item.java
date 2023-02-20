package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.ItemRegistRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Entity;

@Entity
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long adminId;
    private Integer category;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemImage;
    private String itemInfo;
    private Integer itemCount;
    public Item(ItemRegistRequest request){
        this.adminId = request.getAdminId();
        this.category = request.getCategory();
        this.itemName = request.getItemName();
        this.itemOption = request.getItemOption();
        this.itemPrice = request.getItemPrice();
        this.itemImage = request.getItemImage();
        this.itemInfo = request.getItemInfo();
        this.itemCount = request.getItemCount();
    }
}
