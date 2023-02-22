package com.CookingMama.dev.domain.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    @NotEmpty(message = "유효하지 않은 상품입니다!")
    private Long itemId;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
    private String image;
    private Double grade;
}