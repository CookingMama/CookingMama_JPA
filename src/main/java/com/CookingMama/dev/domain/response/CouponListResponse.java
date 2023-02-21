package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.dto.CouponsDTO;
import com.CookingMama.dev.domain.entity.Coupon;
import com.CookingMama.dev.domain.entity.UserCoupon;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponListResponse {
    private Long couponId;
    private String couponName;
    private Integer couponPercentage;
    private Integer status;


    public CouponListResponse(UserCoupon userCoupon) {
        this.couponId = userCoupon.getCoupon().getId();
        this.couponName = userCoupon.getCoupon().getCouponName();
        this.couponPercentage = userCoupon.getCoupon().getCouponPercentage();
        this.status = userCoupon.getStatus();
    }
}
