package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderRequest {
    private Integer adminId;
    private Integer itemId;
    private Integer userId;
    private Integer itemPrice;
    private Integer itemCount;
    private Integer itemDiscount;
    private Integer itemTotalPrice;
    private String orderNumber;
}

