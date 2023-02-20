package com.CookingMama.dev.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenInfo {
    private Long id;
    private String userEmail;
    private String userName;


    public UserTokenInfo tokenToDTO(Claims claims) {
        Long id = Long.parseLong(String.valueOf(claims.get("id")));
        String email = (String) claims.get("userEmail");
        String name = (String) claims.get("userName");


        return new UserTokenInfo(id, email, name);
    }
}
