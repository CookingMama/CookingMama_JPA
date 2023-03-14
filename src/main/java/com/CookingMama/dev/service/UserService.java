package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.*;
import com.CookingMama.dev.domain.request.*;
import com.CookingMama.dev.domain.response.*;
import com.CookingMama.dev.exception.EmailCheckException;
import com.CookingMama.dev.exception.LoginException;
import com.CookingMama.dev.repository.*;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Array;
import java.util.ArrayList;
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
    private final ReviewRepository reviewRepository;
    private final CategoryRepository categoryRepository;


    public UserMainResponse userItemList(){
        List<Item> items = userItemRepository.findTop100ByOrderByIdDesc();
        List<ItemListResponse> responses = items.stream()
                .map(ItemListResponse::new)
                .collect(Collectors.toList());
        List<Review> reviews = reviewRepository.findTop6ByOrderByCreatedAtDesc();
        List<Category> findAll = categoryRepository.findAll();
        List<UserCategoryResponse> categories = findAll.stream().map(UserCategoryResponse::new).collect(Collectors.toList());
        ReviewResponse reviewResponse = new ReviewResponse();
        List<ReviewListResponse> reviewListResponses = new ArrayList<>();
        if(!reviews.isEmpty()) {
            reviewResponse = new ReviewResponse(reviews.get(0));
            reviewListResponses = reviews.stream().map(ReviewListResponse::new).collect(Collectors.toList()).subList(1, reviews.size());
        }
        UserMainResponse userMainResponse = new UserMainResponse(responses, reviewResponse, reviewListResponses, categories);
        return userMainResponse;
    }

    public List<ItemListResponse> userItemListByCategory(Integer categoryId) {
        List<Item> items = userItemRepository.findByCategoryId(categoryId);
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

    public UserResponse signup(SignupRequest request) throws EmailCheckException {
        User user = new User();
        user.setUser(request);
        Optional<User> findUserEmail = userRepository.findByUserEmail(request.getUserEmail());
        if(findUserEmail.isPresent()){
            throw new EmailCheckException();
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
        List<Review> reviews = reviewRepository.findByItemId(itemId);
        List<ReviewListResponse> responses = reviews.stream().map(ReviewListResponse::new).collect(Collectors.toList());
        response.setReviews(responses);
        return response;
    }
    // Hearts 등록
    public String userHeartsInsert(List<AddHeartsRequest> request) {
        try{
            Optional<User> findById = userRepository.findById(securityService.tokenToDTO(securityService.getToken()).getId());
            User user = findById.orElseThrow(NullPointerException::new);
            Optional<Item> itemById = userItemRepository.findById(request.get(0).getItemId());
            Item item = itemById.orElseThrow(NullPointerException::new);
            List<Hearts> heartsList = new ArrayList<>();
            for(AddHeartsRequest i : request){
                heartsList.add(new Hearts(i.getOption(), i.getCount(), item, user));
            }
            heartsRepository.saveAll(heartsList);
            return("장바구니에 추가되었습니다.");
        }catch (NullPointerException e){
            return("장바구니 등록에 실패하였습니다.");
        }
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
    // Hearts 삭제
    public String userHeartsDelete(Long heartsId){
        Long userId = securityService.tokenToUser(securityService.getToken()).getId();
        Hearts hearts = heartsRepository.findByUserIdAndId(heartsId, userId);
        try {
            heartsRepository.delete(hearts);
            return "삭제가 완료되었습니다.";
        }catch (NullPointerException e){
            return "다시 시도해주세요.";
        }
    }

    public List<MyReviewListResponse> getMyReviewList() {
        Long userId = securityService.tokenToDTO(securityService.getToken()).getId();
        List<Review> find = reviewRepository.findByUserId(userId);
        List<MyReviewListResponse> responses = find.stream().map(MyReviewListResponse::new).collect(Collectors.toList());
        return responses;
    }



}
