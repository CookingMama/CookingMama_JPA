package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.dto.UserDTO;
import com.CookingMama.dev.domain.request.LoginRequest;
import com.CookingMama.dev.domain.request.SignupRequest;
import com.CookingMama.dev.domain.request.UserUpdateRequest;
import com.CookingMama.dev.domain.response.UserDetailResponse;
import com.CookingMama.dev.domain.response.UserResponse;
import com.CookingMama.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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
}
