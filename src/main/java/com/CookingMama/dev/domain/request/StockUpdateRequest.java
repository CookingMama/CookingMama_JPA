package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class StockUpdateRequest {
    private Long id;
    private Integer itemCount;
}
