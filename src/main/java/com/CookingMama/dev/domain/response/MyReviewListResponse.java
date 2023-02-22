package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Review;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyReviewListResponse {
    private String itemName;
    private Double grade;
    private String image;
    private String content;

    public MyReviewListResponse(Review review) {
        this.itemName = review.getItem().getItemName();
        this.grade = review.getGrade();
        this.image = review.getImage();
        if(review.getContent().length() < 20)
            this.content = review.getContent();
        else{
            this.content = review.getContent().substring(20) + "...";
        }
    }
}
