package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.Category;
import com.CookingMama.dev.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCategoryResponse {
    private Integer id;
    private String name;

    public UserCategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getCategory_name();
    }
}

