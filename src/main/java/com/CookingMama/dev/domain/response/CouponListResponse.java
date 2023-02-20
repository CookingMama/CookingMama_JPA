package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.dto.CouponsDTO;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponListResponse {
    private Long couponId;
    private String couponName;
    private Integer couponPercentage;
    private Integer status;

    public CouponListResponse(CouponsDTO couponsDTO){
        this.couponId = couponsDTO.getCouponId();
        this.couponName = couponsDTO.getCouponName();
        this.couponPercentage = couponsDTO.getCouponPercentage();
        this.status = couponsDTO.getStatus();
    }
}
