package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.request.AdminLoginRequest;
import com.CookingMama.dev.domain.request.AdminSignUpRequest;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.response.AdminItemListResponse;
import com.CookingMama.dev.domain.response.AdminResponse;
import com.CookingMama.dev.domain.response.ItemListResponse;
import com.CookingMama.dev.exception.EmailCheckException;
import com.CookingMama.dev.security.SecurityService;
import com.CookingMama.dev.service.AdminItemService;
import com.CookingMama.dev.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final AdminItemService adminItemService;
    private final SecurityService securityService;



    @PostMapping("/login")
    public AdminResponse adminLogin(@RequestBody AdminLoginRequest request){
        return adminService.adminLoginService(request);
    }

    @PostMapping("/signup")
    public AdminResponse adminSignUp(@RequestBody AdminSignUpRequest request) throws EmailCheckException {
        return adminService.adminSignUpService(request);
    }

    @PostMapping("/itemregist")
    public Item adminItemRegist(@RequestBody ItemRegistRequest request){
        return adminItemService.itemRegist(request);
    }

    @GetMapping("/itemlist")
    public List<AdminItemListResponse> adminItemList(){
        return adminItemService.adminItemList();
    }
}
