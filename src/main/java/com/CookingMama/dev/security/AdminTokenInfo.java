package com.CookingMama.dev.security;

import io.jsonwebtoken.Claims;
import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminTokenInfo {
    private Long id;
    private String adminEmail;
    private String adminName;
    public AdminTokenInfo tokenToDTO(Claims claims){
        Long id = (Long) claims.get("id");
        String adminEmail = (String) claims.get("adminEmail");
        String adminName = (String) claims.get("adminName");
        return new AdminTokenInfo(id, adminEmail, adminName);
    }
}
