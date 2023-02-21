package com.CookingMama.dev.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponID implements Serializable{
    private Long user;

    private Long coupon;
}
