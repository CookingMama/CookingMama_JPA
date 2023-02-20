package com.CookingMama.dev.security;

import com.CookingMama.dev.domain.dto.AdminDTO;
import com.CookingMama.dev.domain.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityService {
    @Value("${jwt.SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${jwt.EXP_TIME}")
    private String EXP_TIME;

    public String createUserToken(UserDTO userDTO){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] secretKeyByte = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(secretKeyByte, signatureAlgorithm.getJcaName());
        Map<String, Object> map = new HashMap<>();
        map.put("id", userDTO.getId());
        map.put("userEmail", userDTO.getUserEmail());
        map.put("userName", userDTO.getUserName());
        return Jwts.builder()
                .setClaims(map)
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(EXP_TIME)))
                .compact();
    }
    public UserTokenInfo tokenToDTO(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(DatatypeConverter
                        .parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        UserTokenInfo info = new UserTokenInfo().tokenToDTO(claims);
        return info;
    }
    public String getToken(){
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request.getHeader("authorization");
    }


    // Admin
    public String createAdminToken(AdminDTO dto){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] secretKeyByte = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(secretKeyByte, signatureAlgorithm.getJcaName());

        Map<String, Object> map = new HashMap<>();
        map.put("id", dto.getId());
        map.put("adminEmail", dto.getAdminEmail());
        map.put("adminName", dto.getAdminName());
        return Jwts.builder().setClaims(map)
                .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(EXP_TIME)))
                .compact();
    }

    public AdminTokenInfo tokenToAdminDTO(String token){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        AdminTokenInfo info = new AdminTokenInfo().tokenToDTO(claims);
        return info;
    }
}