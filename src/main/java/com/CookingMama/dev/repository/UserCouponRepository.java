package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Coupon;
import com.CookingMama.dev.domain.entity.UserCoupon;
import com.CookingMama.dev.domain.entity.UserCouponID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCouponRepository extends JpaRepository<UserCoupon, UserCouponID> {
    List<UserCoupon> findByUserId(Long userId);

    UserCoupon findByUserIdAndCouponId(Long id, Long CouponId);
}
