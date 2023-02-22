package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.dto.UserDTO;
import com.CookingMama.dev.domain.entity.Hearts;
import com.CookingMama.dev.domain.request.*;
import com.CookingMama.dev.domain.response.*;
import com.CookingMama.dev.exception.EmailCheckException;
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
    // Item 전체조회
    @GetMapping
    public UserMainResponse userItemList(){
        return userService.userItemList();
    }
    // User 로그인
    @PostMapping("/login")
    public UserResponse login(@RequestBody @Valid LoginRequest request){
        return userService.login(request);
    }
    // User 회원가입
    @PostMapping("/signup")
    public UserResponse signup(@RequestBody @Valid SignupRequest request) throws EmailCheckException {
        return userService.signup(request);
    }
    // User 마이페이지 조회
    @GetMapping("/my-page")
    public UserDetailResponse userInfo() {
        return userService.userInfo();
    }
    // User 정보 수정
    @PutMapping("/my-page")
    public String updateUser(@RequestBody SignupRequest request) {
        return userService.userUpdate(request);
    }
    // Item 상세정보
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

    // 내가 쓴 review 조회
    @GetMapping("/myreview")
    public List<MyReviewListResponse> myReviewList(){
        return userService.getMyReviewList();
    }
}
