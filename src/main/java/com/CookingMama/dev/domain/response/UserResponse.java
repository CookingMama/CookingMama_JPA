package com.CookingMama.dev.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String userEmail;
    private String userName;
    private String token;
}
