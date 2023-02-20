package com.CookingMama.dev.domain.dto;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class AdminDTO {
    private Integer id;
    private String adminEmail;
    private String adminPw;
    private String adminName;
    private String adminBirth;
    private String adminPhoneNumber;
}
