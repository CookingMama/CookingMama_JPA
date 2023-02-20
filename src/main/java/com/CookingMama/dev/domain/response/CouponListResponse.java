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

    public CouponListResponse(CouponsDTO coupons){
        this.couponId = coupons.getCouponId();
        this.couponName = coupons.getCouponName();
        this.couponPercentage = coupons.getCouponPercentage();
        this.status = coupons.getStatus();
    }
}
