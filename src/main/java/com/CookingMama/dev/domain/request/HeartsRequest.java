package com.CookingMama.dev.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class HeartsRequest {
    private Long heartsId;
    private Integer count;
}
