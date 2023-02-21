package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.SignupRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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
    public void setUser(SignupRequest request){
        this.userEmail = request.getUserEmail();
        this.userPw = request.getUserPw();
        this.userName = request.getUserName();
        this.userBirth = request.getUserBirth();
        this.userAddress = request.getUserAddress();
        this.userAddressDetail = request.getUserAddressDetail();
        this.userZipCode = request.getUserZipcode();
        this.userPhoneNumber = request.getUserPhoneNumber();
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<OrderInfo> orderInfoList = new ArrayList<>();
}
