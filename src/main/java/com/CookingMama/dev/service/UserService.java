package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Hearts;
import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.entity.User;
import com.CookingMama.dev.domain.request.HeartsRequest;
import com.CookingMama.dev.domain.request.LoginRequest;
import com.CookingMama.dev.domain.request.SignupRequest;
import com.CookingMama.dev.domain.response.*;
import com.CookingMama.dev.exception.LoginException;
import com.CookingMama.dev.repository.HeartsRepository;
import com.CookingMama.dev.repository.UserItemRepository;
import com.CookingMama.dev.repository.UserRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final UserItemRepository userItemRepository;
    private final HeartsRepository heartsRepository;


    public List<ItemListResponse> userItemList(){
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        List<Item> items = userItemRepository.findAll();
        List<ItemListResponse> responses = items.stream()
                .map(ItemListResponse::new)
                .collect(Collectors.toList());
        return responses;
    }
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
        User user = new User();
        user.setUser(request);
        Optional<User> findUserEmail = userRepository.findByUserEmail(request.getUserEmail());
        if(findUserEmail.isPresent()){
            throw new NullPointerException();
        }
        userRepository.save(user);
        LoginRequest loginRequest = new LoginRequest(user.getUserEmail(), user.getUserPw());
        return login(loginRequest);
    }

    public UserDetailResponse userInfo(){
        String token = securityService.getToken();
        User user = securityService.tokenToUser(token);
        UserDetailResponse userDetailResponse = new UserDetailResponse(user);
        return userDetailResponse;
    }

    public String userUpdate(SignupRequest request){
        String token = securityService.getToken();
        User user = securityService.tokenToUser(token);
        try {
            user.setUser(request);
            userRepository.save(user);
            return "회원정보가 수정되었습니다.";
        }catch (Exception e){
            return "회원정보 수정에 실패했습니다.";
        }
    }
    public UserItemResponse userItemDetail(Long itemId){
        Optional<Item> findById = userItemRepository.findById(itemId);
        Item item = findById.orElseThrow(NullPointerException::new);
        UserItemResponse response = new UserItemResponse(item);
        return response;
    }

    // Hearts 조회
    public List<HeartsResponse> userHeartsList(){
        Long userId = securityService.tokenToUser(securityService.getToken()).getId();
        List<Hearts> hearts = heartsRepository.findByUserId(userId);
        List<HeartsResponse> responses = hearts.stream()
                .map(HeartsResponse::new)
                .collect(Collectors.toList());
        return responses;
    }

    // Hearts 수량 조정
    public String userHeartsUpdate(List<HeartsRequest> request){
        Long userId = securityService.tokenToUser(securityService.getToken()).getId();
        List<Hearts> hearts = heartsRepository.findByUserId(userId);
        try {
            for(Hearts hearts1:hearts) {
                for (HeartsRequest request1 : request) {
                    hearts1.userHeartsUpdate(request1);
                    heartsRepository.save(hearts1);
                }
            }
            return "수정이 완료되었습니다.";
        }catch (NullPointerException e){
            return "수정이 취소되었습니다.";
        }
    }
}
