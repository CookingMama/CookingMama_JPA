package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.entity.User;
import com.CookingMama.dev.domain.request.AddCouponRequest;
import com.CookingMama.dev.domain.response.CouponListResponse;
import com.CookingMama.dev.security.SecurityService;
import com.CookingMama.dev.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupons")
public class CouponController {
    private final CouponService couponService;
    private final SecurityService securityService;

    @GetMapping
    public List<CouponListResponse> getMyCoupons(){
        return couponService.getMyCoupons();
    }
    @PostMapping
    public String addCoupon(@RequestBody AddCouponRequest couponCode){
        User user = securityService.tokenToUser(securityService.getToken());
        return couponService.addCoupon(user, couponCode);
    }
    @PutMapping("/useCoupon")
    public String useCoupon(@RequestBody AddCouponRequest couponId){
        return couponService.useCoupon(couponId.getCouponCode());
    }
}
