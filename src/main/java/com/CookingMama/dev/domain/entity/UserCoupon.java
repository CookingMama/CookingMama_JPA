package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.AddCouponRequest;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UserCouponID.class)
public class UserCoupon implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private Integer Status = 0;

    public UserCoupon(User user, Coupon coupon) {
        this.user = user;
        this.coupon = coupon;
    }
}
