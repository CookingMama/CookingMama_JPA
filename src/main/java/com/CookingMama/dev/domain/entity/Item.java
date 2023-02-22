package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.AdminUpdateItemRequest;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.request.StockUpdateRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

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
    private Double grade = 0.0;
    private Long reviewCount = 0L;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<OrderInfo> orderInfoList = new ArrayList<>();

    @OneToOne
    private Hearts hearts;

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

    public void setItem(AdminUpdateItemRequest request, Category category) {
        this.itemName = request.getItemName();
        this.itemOption = request.getItemOption();
        this.itemPrice = request.getItemPrice();
        this.itemImage = request.getItemImage();
        this.itemInfo = request.getItemInfo();
        this.itemCount = request.getItemCount();
        this.category = category;
    }

    public void setStock(StockUpdateRequest request){
        this.itemCount = request.getItemCount();
    }

}
