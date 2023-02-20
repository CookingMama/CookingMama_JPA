package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Admin;
import com.CookingMama.dev.domain.request.AdminLoginRequest;
import com.CookingMama.dev.domain.request.AdminSignUpRequest;
import com.CookingMama.dev.domain.response.AdminResponse;
import com.CookingMama.dev.exception.EmailCheckException;
import com.CookingMama.dev.repository.AdminRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final SecurityService securityService;

    public AdminResponse adminLoginService(AdminLoginRequest request){
        Optional<Admin> findByAdminEmailAndAdminPw = adminRepository.findByAdminEmailAndAdminPw(request.getAdminEmail(), request.getAdminPw());
        Admin admin = findByAdminEmailAndAdminPw.orElseThrow(NullPointerException::new);
        String token = securityService.createAdminToken(admin);
        AdminResponse adminResponse = new AdminResponse(admin.getAdminEmail(), token);
        return adminResponse;
    }
    public AdminResponse adminSignUpService(AdminSignUpRequest request) throws EmailCheckException {
        Admin admin = request.toEntity();
        Optional<Admin> findAdminEmail = adminRepository.findByAdminEmail(request.getAdminEmail());
        if(findAdminEmail.isPresent()){
            throw new EmailCheckException();
        }
        adminRepository.save(admin);
        AdminLoginRequest adminLoginRequest = new AdminLoginRequest(admin.getAdminEmail(), admin.getAdminPw());
        return adminLoginService(adminLoginRequest);
    }
}
