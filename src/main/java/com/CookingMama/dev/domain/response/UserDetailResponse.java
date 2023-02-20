package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class UserDetailResponse {
    private String userEmail;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userAddress;
    private String userAddressDetail;
    private String userZipCode;
    private String userPhoneNumber;

    public UserDetailResponse(User user){
        this.userEmail = user.getUserEmail();
        this.userPw = user.getUserPw();
        this.userName = user.getUserName();
        this.userBirth = user.getUserBirth();
        this.userAddress = user.getUserAddress();
        this.userAddressDetail = user.getUserAddressDetail();
        this.userZipCode = user.getUserZipCode();
        this.userPhoneNumber = user.getUserPhoneNumber();
    }
}
