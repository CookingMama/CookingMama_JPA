package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter@Setter
@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminLoginRequest {
    private String adminEmail;
    private String adminPw;
}
