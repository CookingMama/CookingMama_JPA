package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.User;
import com.CookingMama.dev.domain.request.LoginRequest;
import com.CookingMama.dev.domain.request.SignupRequest;
import com.CookingMama.dev.domain.response.UserResponse;
import com.CookingMama.dev.exception.LoginException;
import com.CookingMama.dev.repository.UserRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;

    public UserResponse login(LoginRequest request){
        Optional<User> findByUserEmailAndUserPw =
                userRepository.findByUserEmailAndUserPw(request.getUserEmail(), request.getUserPw());
        User user = findByUserEmailAndUserPw.orElseThrow(LoginException::new);
        String token = securityService.createUserToken(user);
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getUserEmail(),
                user.getUserName(),
                token
        );
        return userResponse;
    }

    public UserResponse signup(SignupRequest request){
        User user = request.toEntity();
        Optional<User> findUserEmail = userRepository.findByUserEmail(request.getUserEmail());
        if(findUserEmail.isPresent()){
            throw new NullPointerException();
        }
        userRepository.save(user);
        LoginRequest loginRequest = new LoginRequest(user.getUserEmail(), user.getUserPw());
        return login(loginRequest);
    }
}
