package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.request.LoginRequest;
import com.CookingMama.dev.domain.request.SignupRequest;
import com.CookingMama.dev.domain.response.UserResponse;
import com.CookingMama.dev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
