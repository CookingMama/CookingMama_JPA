package com.CookingMama.dev.domain.request;

import com.CookingMama.dev.domain.entity.ItemOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class AddHeartsRequest {
    private String option;
    private Integer count;
    private Long itemId;
}
