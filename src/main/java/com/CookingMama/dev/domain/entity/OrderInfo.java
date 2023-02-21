package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.AdminOrderRequest;
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
    private LocalDateTime orderDate;
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

    public void adminOrderRequest(AdminOrderRequest request){
        this.status = request.getStatus();
        this.trackingNumber = request.getTrackingNumber();
    }
}
