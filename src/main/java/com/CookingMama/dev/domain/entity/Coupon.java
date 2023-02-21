package com.CookingMama.dev.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Coupon{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String couponName;
    @Column(nullable = false)
    private String couponCode;
    @Column(nullable = false)
    private Integer couponPercentage;

    @OneToMany(mappedBy = "coupon", fetch = FetchType.LAZY)
    List<UserCoupon> userCoupons = new ArrayList<>();

}
