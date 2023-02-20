package com.CookingMama.dev.domain.request;

import com.CookingMama.dev.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class SignupRequest {
    private String userEmail;
    private String userPw;
    private String userName;
    private String userPhoneNumber;
    private String userBirth;
    private String userAddress;
    private String userAddressDetail;
    private String userZipcode;

    public User toEntity() {
        return new User(
                this.userEmail,
                this.userPw,
                this.userName,
                this.userPhoneNumber,
                this.userBirth,
                this.userAddress,
                this.userAddressDetail,
                this.userZipcode
        );
    }
}
