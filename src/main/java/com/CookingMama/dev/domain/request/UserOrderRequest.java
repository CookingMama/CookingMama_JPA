package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderRequest {
    private Long adminId;
    private Long itemId;
    private Long userId;
    private Integer itemPrice;
    private Integer categoryId;
    private Integer itemCount;
    private Integer itemDiscount;
    private Integer itemTotalPrice;
    private Long orderNumber;
}

