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
    private int id;
    private String userEmail;
    private String userName;


    public UserTokenInfo tokenToDTO(Claims claims) {
        Integer id = (Integer) claims.get("id");
        String email = (String) claims.get("userEmail");
        String name = (String) claims.get("userName");


        return new UserTokenInfo(id, email, name);
    }
}
