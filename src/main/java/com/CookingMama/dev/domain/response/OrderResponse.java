package com.CookingMama.dev.domain.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String adminName;
    private String itemName;
    private Integer itemPrice;
    private Integer itemCount;
    private Integer itemDiscount;
    private Integer itemTotalPrice;
    private String orderNumber;
    private String trackingNumber;
    private LocalDateTime orderDate;
    private Integer status;
}
