package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.request.AdminLoginRequest;
import com.CookingMama.dev.domain.request.AdminSignUpRequest;
import com.CookingMama.dev.domain.request.AdminUpdateItemRequest;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.response.AdminItemDetailResponse;
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


    // 로그인
    @PostMapping("/login")
    public AdminResponse adminLogin(@RequestBody AdminLoginRequest request){
        return adminService.adminLoginService(request);
    }
    // 회원가입
    @PostMapping("/signup")
    public AdminResponse adminSignUp(@RequestBody AdminSignUpRequest request) throws EmailCheckException {
        return adminService.adminSignUpService(request);
    }
    // 상품 등록
    @PostMapping("/itemregist")
    public Item adminItemRegist(@RequestBody ItemRegistRequest request){
        log.info(request.toString());
        return adminItemService.itemRegist(request);
    }
    // 상품리스트 조회
    @GetMapping("/itemlist")
    public List<ItemListResponse> adminItemList(){
        return adminItemService.adminItemList();
    }
    // 상품 디테일
    @GetMapping("/itemlist/{itemId}")
    public AdminItemDetailResponse adminItemDetail(@PathVariable("itemId") Long itemId) {
        return adminItemService.adminItemDetail(itemId);
    }
    // 상품 수정
    @PutMapping("/itemupdate/{itemId}")
    public String  adminItemUpdate(@PathVariable("itemId") Long itemId,
                                  @RequestBody AdminUpdateItemRequest request){
        return adminItemService.adminItemUpdate(itemId, request);
    }
}
