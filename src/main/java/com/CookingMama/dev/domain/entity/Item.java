package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private String itemImage;
    private String itemInfo;
    private Integer itemCount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;
    public Item(ItemRegistRequest request, Admin admin, Category category){
        this.itemName = request.getItemName();
        this.itemOption = request.getItemOption();
        this.itemPrice = request.getItemPrice();
        this.itemImage = request.getItemImage();
        this.itemInfo = request.getItemInfo();
        this.itemCount = request.getItemCount();
        this.admin = admin;
        this.category = category;
    }
}
