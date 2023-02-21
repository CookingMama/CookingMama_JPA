package com.CookingMama.dev.security;

import com.CookingMama.dev.domain.entity.User;
import com.CookingMama.dev.domain.entity.Admin;
import com.CookingMama.dev.repository.AdminRepository;
import com.CookingMama.dev.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Service

public class SecurityService {

    @Value("${jwt.SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${jwt.EXP_TIME}")
    private String EXP_TIME;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    public SecurityService() {
    }

    public String createUserToken(User user){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] secretKeyByte = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(secretKeyByte, signatureAlgorithm.getJcaName());
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("userEmail", user.getUserEmail());
        map.put("userName", user.getUserName());
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
    public String createAdminToken(Admin admin){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] secretKeyByte = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(secretKeyByte, signatureAlgorithm.getJcaName());

        Map<String, Object> map = new HashMap<>();
        map.put("id", admin.getId());
        map.put("adminEmail", admin.getAdminEmail());
        map.put("adminName", admin.getAdminName());
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

    public User tokenToUser(String token){
        UserTokenInfo tokenInfo = tokenToDTO(token);
        Optional<User> optionalMember = userRepository.findById(tokenInfo.getId());
        User user = optionalMember.orElseThrow(() -> new JwtException("유효하지 않음"));
        return user;
    }

    public Admin tokenToAdmin(String token){
        AdminTokenInfo tokenInfo = tokenToAdminDTO(token);
        Optional<Admin> optionalMember = adminRepository.findById(tokenInfo.getId());
        Admin admin = optionalMember.orElseThrow(() -> new JwtException("유효하지 않음"));
        return admin;
    }
}