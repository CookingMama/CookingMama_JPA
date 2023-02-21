package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminOrderRequest {
    private Long id;
    private Integer status;
    private String trackingNumber;
}
