package com.CookingMama.dev.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddCouponRequest {
    @NotEmpty(message = "쿠폰코드를 입력해주세요.")
    private String couponCode;
}
