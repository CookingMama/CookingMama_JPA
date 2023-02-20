package com.CookingMama.dev.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String userPw;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userBirth;
    private String userAddress;
    private String userAddressDetail;
    private String userZipCode;
    @Column(nullable = false)
    private String userPhoneNumber;

    public User(String userEmail, String userPw, String userName, String userBirth, String userAddress, String userAddressDetail, String userZipCode, String userPhoneNumber) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userAddress = userAddress;
        this.userAddressDetail = userAddressDetail;
        this.userZipCode = userZipCode;
        this.userPhoneNumber = userPhoneNumber;
    }
}
