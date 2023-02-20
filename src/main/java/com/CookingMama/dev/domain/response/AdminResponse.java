package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.dto.AdminDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class AdminResponse {
    private String adminEmail;
    private String token;
    public AdminResponse(AdminDTO dto){
        this.adminEmail = dto.getAdminEmail();
    }
}
