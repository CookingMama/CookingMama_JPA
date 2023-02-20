package com.CookingMama.dev.domain.request;

import com.CookingMama.dev.domain.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Getter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminSignUpRequest {
    private String adminEmail;
    private String adminPw;
    private String adminName;
    private String adminBirth;
    private String adminPhoneNumber;

    public Admin toEntity(){
        return new Admin(this.adminEmail, this.adminPw, this.adminName, this.adminBirth, this.adminPhoneNumber);
    }
}
