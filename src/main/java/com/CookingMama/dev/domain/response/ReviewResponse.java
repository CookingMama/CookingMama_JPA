package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Review;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private String itemName;
    private String userName;
    private Double grade;
    private String image;
    private String content;

    public ReviewResponse(Review review) {
        this.itemName = review.getItem().getItemName();
        this.userName = review.getUser().getUserName().substring(0, 1) + "**";
        this.grade = review.getGrade();
        this.image = review.getImage();
        this.content = review.getContent();
    }
}
