package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderCancelRequest {
    private Integer itemId;
    private Integer itemCount;
}
