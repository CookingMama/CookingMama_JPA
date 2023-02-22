package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.entity.Review;
import com.CookingMama.dev.domain.entity.ReviewID;
import com.CookingMama.dev.domain.entity.User;
import com.CookingMama.dev.domain.request.ReviewRequest;
import com.CookingMama.dev.domain.response.ReviewResponse;
import com.CookingMama.dev.repository.ReviewRepository;
import com.CookingMama.dev.repository.UserItemRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final SecurityService securityService;
    private final UserItemRepository userItemRepository;
    private final ReviewRepository reviewRepository;
    public String insertReview(ReviewRequest request) {
        try {
            User user = securityService.tokenToUser(securityService.getToken());
            // 이미 리뷰를 작성한 아이템인지 확인
            ReviewID forCheck = new ReviewID(user.getId(), request.getItemId());
            if(reviewRepository.existsById(forCheck)) return "이미 리뷰를 등록하신 상품입니다!";
            Optional<Item> findOneById = userItemRepository.findById(request.getItemId());
            Item item = findOneById.orElseThrow(NullPointerException::new);
            // 평점 구하기
            Long count = reviewRepository.countByItemId(request.getItemId());
            Double newGrade = request.getGrade();
            if (count != 0) newGrade = (item.getGrade() * count + request.getGrade()) / (count + 1);
            item.setGrade(newGrade);
            item.setReviewCount(count + 1);
            userItemRepository.save(item);
            // 리뷰 저장
            Review review = new Review(request, user, item);
            reviewRepository.save(review);
            return "리뷰가 등록되었습니다.";
        }catch(Exception e){
            return "리뷰 등록에 실패하였습니다!";
        }
    }

    public ReviewResponse reviewDetail(Long itemId, Long userId) {
        Review find = reviewRepository.findByItemIdAndUserId(itemId, userId);
        ReviewResponse response = new ReviewResponse(find);
        return response;
    }
    public String updateReview(ReviewRequest request, Long itemId, Long userId) {
        Long myId = securityService.tokenToDTO(securityService.getToken()).getId();
        if(myId != userId) return "접근할 수 없는 기능입니다!";
        try{
            // 평점 수정
            Optional<Item> findOneById = userItemRepository.findById(itemId);
            Item item = findOneById.orElseThrow(NullPointerException::new);
            Long count = reviewRepository.countByItemId(request.getItemId());
            Double newGrade = request.getGrade();
            if (count != 0) newGrade = (item.getGrade() * (count - 1) + request.getGrade()) / count;
            item.setGrade(newGrade);
            item.setReviewCount(count + 1);
            userItemRepository.save(item);
            // 리뷰 수정
            Review find = reviewRepository.findByItemIdAndUserId(itemId, userId);
            find.setReview(request);
            reviewRepository.save(find);
            
            return "리뷰가 수정되었습니다.";
        } catch (Exception e){
            return "리뷰 수정에 실패하였습니다.";
        }
    }


    public String deleteReview(Long itemId, Long userId) {
        Long myId = securityService.tokenToDTO(securityService.getToken()).getId();
        if(myId != userId) return "접근할 수 없는 기능입니다!";
        try{
            Review find = reviewRepository.findByItemIdAndUserId(itemId, userId);
            Optional<Item> findOneById = userItemRepository.findById(itemId);
            Item item = findOneById.orElseThrow(NullPointerException::new);
            Long count = reviewRepository.countByItemId(itemId);
            Double newGrade = 0.0;
            if (count > 1) newGrade = (item.getGrade() * count - find.getGrade())/ count - 1;
            item.setGrade(newGrade);
            item.setReviewCount(count + 1);
            userItemRepository.save(item);
            reviewRepository.delete(find);
            return "리뷰가 삭제되었습니다.";
        }catch(Exception e){
            return "존재하지 않는 리뷰입니다!";
        }
    }
}
