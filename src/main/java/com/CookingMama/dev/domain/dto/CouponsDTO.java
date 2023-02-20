package com.CookingMama.dev.domain.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponsDTO {
    private Long couponId;
    private String couponName;
    private String couponCode;
    private Integer couponPercentage;
    private Integer status;
}
