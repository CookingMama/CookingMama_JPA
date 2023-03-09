package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderRequest {
    private Long itemId;
    private Integer itemPrice;
    private String categoryName;
    private Integer itemCount;
    private Integer itemDiscount;
    private Integer itemTotalPrice;
    private Long orderNumber;
}

