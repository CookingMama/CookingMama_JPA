package com.CookingMama.dev.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDTO {
    private Long id;
    private Long adminId;
    private Long itemId;
    private Long userId;
    private Integer itemPrice;
    private Integer itemCount;
    private Integer itemDiscount;
    private Integer itemTotalPrice;
    private String orderNumber;
    private String trackingNumber;
    private LocalDateTime orderDate;
    private Integer status;
}
