package com.CookingMama.dev.domain.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminOrderListResponse {
    private String orderNumber;
    private LocalDateTime orderDate;
    private String categoryName;
    private Integer adminId;
    private String itemName;
    private String itemOption;
    private Integer itemPrice;
    private Integer itemCount;
    private Integer userId;
    private Integer itemDiscount;
    private Integer itemTotalPrice;
    private Integer status;
    private String trackingNumber;
}
