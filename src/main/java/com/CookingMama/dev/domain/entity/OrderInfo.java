package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.AdminOrderRequest;
import com.CookingMama.dev.domain.request.UserOrderRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer itemDiscount = 0;
    private Integer itemTotalPrice;
    private Long orderNumber;
    private String trackingNumber = "배송준비중";
    private LocalDateTime orderDate = LocalDateTime.now();
    private Integer status = 0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;


    public OrderInfo(UserOrderRequest request, Admin admin, Item item, User user, Category category) {
        this.itemDiscount = request.getItemDiscount();
        this.itemTotalPrice = request.getItemTotalPrice();
        this.orderNumber = request.getOrderNumber();
        this.admin = admin;
        this.item = item;
        this.user = user;
        this.category = category;
    }

    public void adminOrderRequest(AdminOrderRequest request){
        this.status = request.getStatus();
        this.trackingNumber = request.getTrackingNumber();
    }
}
