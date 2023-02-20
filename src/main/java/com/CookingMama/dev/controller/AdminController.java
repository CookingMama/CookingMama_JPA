package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.request.AdminLoginRequest;
import com.CookingMama.dev.domain.request.AdminSignUpRequest;
import com.CookingMama.dev.domain.response.AdminResponse;
import com.CookingMama.dev.exception.EmailCheckException;
import com.CookingMama.dev.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public AdminResponse adminLogin(@RequestBody AdminLoginRequest request){
        return adminService.adminLoginService(request);
    }

    @PostMapping("/signup")
    public AdminResponse adminSignUp(@RequestBody AdminSignUpRequest request) throws EmailCheckException {
        return adminService.adminSignUpService(request);
    }
}
