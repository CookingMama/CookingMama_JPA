package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCouponCode(String couponCode);
}
