package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.ReviewRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ReviewID.class)
@Entity
public class Review implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private String image;
    private String content;
    private Double grade = 0.0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Review(ReviewRequest request, User user, Item item) {
        this.user = user;
        this.item = item;
        this.image = request.getImage();
        this.content = request.getContent();
        this.grade = request.getGrade();
    }
    public void setReview(ReviewRequest request){
        this.image = request.getImage();
        this.content = request.getContent();
        this.grade = request.getGrade();
    }
}
