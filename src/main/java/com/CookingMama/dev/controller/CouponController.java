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
@RequestMapping("/user")
public class CouponController {
    private final CouponService couponService;
    private final SecurityService securityService;

    @GetMapping("/coupons")
    public List<CouponListResponse> getMyCoupons(){
        return couponService.getMyCoupons();
    }
    @PostMapping("/coupons")
    public String addCoupon(@RequestBody AddCouponRequest couponCode){
        User user = securityService.tokenToUser(securityService.getToken());
        return couponService.addCoupon(user, couponCode);
    }
//    @PutMapping("/useCoupon")
//    public Integer useCoupon(@RequestBody AddCouponRequest couponCode){
//        String token = securityService.getToken();
//        AddCouponRequest request = new AddCouponRequest(securityService.tokenToDTO(token).getId(), couponCode.getCouponCode());
//        return couponService.useCoupon(request);
//    }
}
