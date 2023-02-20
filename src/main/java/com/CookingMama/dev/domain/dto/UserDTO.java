package com.CookingMama.dev.domain.dto;

import lombok.*;

@Getter @ToString
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userPhoneNumber;
    private String userBirth;
    private String userAddress;
    private String userAddressDetail;
    private String userZipcode;


}
