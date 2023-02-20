package com.CookingMama.dev.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminSignUpRequest {
    private String adminEmail;
    private String adminPw;
    private String adminName;
    private String adminBirth;
    private String adminPhoneNumber;
}
