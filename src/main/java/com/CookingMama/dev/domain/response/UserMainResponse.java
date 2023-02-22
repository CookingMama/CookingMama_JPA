package com.CookingMama.dev.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class UserMainResponse {
    private List<ItemListResponse> items;
    private ReviewResponse bestReview;
    private List<ReviewListResponse> reviews;
}
