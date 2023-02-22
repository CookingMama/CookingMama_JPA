package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Coupon;
import com.CookingMama.dev.domain.entity.User;
import com.CookingMama.dev.domain.entity.UserCoupon;
import com.CookingMama.dev.domain.entity.UserCouponID;
import com.CookingMama.dev.domain.request.AddCouponRequest;
import com.CookingMama.dev.domain.response.CouponListResponse;
import com.CookingMama.dev.domain.response.ItemListResponse;
import com.CookingMama.dev.repository.CouponRepository;
import com.CookingMama.dev.repository.UserCouponRepository;
import com.CookingMama.dev.repository.UserRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {
    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    private final SecurityService securityService;

    public String addCoupon(User user, AddCouponRequest couponCode){
        Coupon checkCoupon = couponRepository.findByCouponCode(couponCode.getCouponCode());
        if(checkCoupon==null)return "유효하지 않은 쿠폰코드 입니다!";
        UserCoupon checkUserCoupon = userCouponRepository.findByUserIdAndCouponId(user.getId(), checkCoupon.getId());
        if(checkUserCoupon != null)return "이미 등록된 쿠폰입니다!";
        UserCoupon userCoupon = new UserCoupon(user, checkCoupon);
        userCouponRepository.save(userCoupon);
        return "쿠폰 등록이 완료되었습니다!";
    }
    public List<CouponListResponse> getMyCoupons(){
        Long userId = securityService.tokenToDTO(securityService.getToken()).getId();
        List<UserCoupon> userCoupons = userCouponRepository.findByUserId(userId);
        List<CouponListResponse> responses = userCoupons.stream()
                .map(CouponListResponse::new)
                .collect(Collectors.toList());
        return responses;
    }
    public String useCoupon(String couponCode){
        Long userId = securityService.tokenToDTO(securityService.getToken()).getId();
        Coupon coupon = couponRepository.findByCouponCode(couponCode);
        try {
            UserCouponID request = new UserCouponID(userId, coupon.getId());
            Optional<UserCoupon> findById = userCouponRepository.findById(request);
            UserCoupon userCoupon = findById.orElseThrow(NullPointerException::new);
            if(userCoupon.getStatus() == 1) return "이미 사용하신 쿠폰입니다.";
            userCoupon.setStatus(1);
            userCouponRepository.save(userCoupon);
            return "쿠폰이 사용되었습니다.";
        }catch (Exception e){
            return "존재하지 않는 쿠폰 코드 입니다.";
        }
    }
}
