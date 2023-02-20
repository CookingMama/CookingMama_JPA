package com.CookingMama.dev.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adminEmail;
    private String adminPw;
    private String adminName;
    private String adminBirth;
    private String adminPhoneNumber;

    public Admin(String adminEmail, String adminPw, String adminName, String adminBirth, String adminPhoneNumber) {
        this.adminEmail = adminEmail;
        this.adminPw = adminPw;
        this.adminName = adminName;
        this.adminBirth = adminBirth;
        this.adminPhoneNumber = adminPhoneNumber;
    }
}
