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
public class ReviewListResponse {
    private Long itemId;
    private Long userId;
    private String itemName;
    private String userName;
    private Double grade;
    private String image;
    private String content;
    private LocalDateTime createdAt;

    public ReviewListResponse(Review review) {
        this.itemId = review.getItem().getId();
        this.userId = review.getUser().getId();
        this.itemName = review.getItem().getItemName();
        this.userName = review.getUser().getUserName().substring(0,1) + "**";
        this.grade = review.getGrade();
        this.image = review.getImage();
        if(review.getContent().length() < 20)
            this.content = review.getContent();
        else{
            this.content = review.getContent().substring(20) + "...";
        }
        this.createdAt = review.getCreatedAt();
    }
}
