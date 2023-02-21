package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.dto.UserDTO;
import com.CookingMama.dev.domain.entity.Hearts;
import com.CookingMama.dev.domain.request.HeartsRequest;
import com.CookingMama.dev.domain.request.LoginRequest;
import com.CookingMama.dev.domain.request.SignupRequest;
import com.CookingMama.dev.domain.request.UserUpdateRequest;
import com.CookingMama.dev.domain.response.*;
import com.CookingMama.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<ItemListResponse> userItemList(){
        return userService.userItemList();
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody @Valid LoginRequest request){
        return userService.login(request);
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody @Valid SignupRequest request){
        return userService.signup(request);
    }

    @GetMapping("/my-page")
    public UserDetailResponse userInfo() {
        return userService.userInfo();
    }
    @PutMapping("/my-page")
    public String updateUser(@RequestBody SignupRequest request) {
        return userService.userUpdate(request);
    }

    @GetMapping("/itemdetail/{itemId}")
    public UserItemResponse userItemDetail(@PathVariable("itemId") Long itemId){
        return userService.userItemDetail(itemId);
    }

    // Hearts 조회
    @GetMapping("/hearts")
    public List<HeartsResponse> userHeartsList(){
        return userService.userHeartsList();
    }

    // Hearts 수량 수정
    @PutMapping("/hearts")
    public String userHeartsUpdate(@RequestBody List<HeartsRequest> request){
        return userService.userHeartsUpdate(request);
    }
    // Hearts 삭제
    @DeleteMapping("/hearts/{heartsId}")
    public String userHeartsDelete(@PathVariable("heartsId") Long heartsId){
        return userService.userHeartsDelete(heartsId);
    }
}
