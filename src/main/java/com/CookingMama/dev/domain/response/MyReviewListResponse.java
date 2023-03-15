package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Review;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyReviewListResponse {
    private Long userId;
    private Long itemId;
    private String itemName;
    private Double grade;
    private String image;
    private String content;
    private LocalDateTime createdAt;

    public MyReviewListResponse(Review review) {
        this.itemName = review.getItem().getItemName();
        this.grade = review.getGrade();
        this.image = review.getImage();
        if(review.getContent().length() < 20)
            this.content = review.getContent();
        else{
            this.content = review.getContent().substring(20) + "...";
        }
        this.createdAt = review.getCreatedAt();
        this.itemId = review.getItem().getId();
        this.userId = review.getUser().getId();
    }
}
