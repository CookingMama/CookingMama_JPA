package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminUpdateOrderListRequest {
    private Integer status;
    private String trackingNumber;
    private Integer adminId;
    private String orderNumber;
    private Integer itemId;
}
