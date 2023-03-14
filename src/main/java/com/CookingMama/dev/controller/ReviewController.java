package com.CookingMama.dev.controller;

import com.CookingMama.dev.domain.request.ReviewRequest;
import com.CookingMama.dev.domain.response.ReviewListResponse;
import com.CookingMama.dev.domain.response.ReviewResponse;
import com.CookingMama.dev.service.ReviewService;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    // Review 작성
    @PostMapping("/write")
    public String writeReview(@ModelAttribute ReviewRequest request) {
        return reviewService.insertReview(request);
    }

    @GetMapping("/{itemId}/{userId}")
    public ReviewResponse reviewDetail(
            @PathVariable("itemId") Long itemId,
            @PathVariable("userId") Long userId){
        return reviewService.reviewDetail(itemId, userId);
    }
    @PutMapping("/edit/{itemId}/{userId}")
    public String editReview(@RequestBody ReviewRequest request,
                             @PathVariable("itemId") Long itemId,
                             @PathVariable("userId") Long userId) {
        return reviewService.updateReview(request, itemId, userId);
    }

    @DeleteMapping("/delete/{itemId}/{userId}")
    public String deleteReview(
            @PathVariable("itemId") Long itemId,
            @PathVariable("userId") Long userId
    ){
        return reviewService.deleteReview(itemId, userId);
    }
}
