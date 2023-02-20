package com.CookingMama.dev.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private Integer id;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userPhoneNumber;
    private String userBirth;
    private String userAddress;
    private String userAddressDetail;
    private String userZipcode;

    public UserUpdateRequest(Integer id, SignupRequest request){
        this.id = id;
        this.userEmail = request.getUserEmail();
        this.userPw = request.getUserPw();
        this.userName = request.getUserName();
        this.userPhoneNumber = request.getUserPhoneNumber();
        this.userBirth = request.getUserBirth();
        this.userAddress = request.getUserAddress();
        this.userAddressDetail = request.getUserAddressDetail();
        this.userZipcode = request.getUserZipcode();
    }
}
