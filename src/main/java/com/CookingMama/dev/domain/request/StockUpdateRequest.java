package com.CookingMama.dev.domain.request;

import lombok.*;

import java.util.List;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class StockUpdateRequest {
    private Long id;
    private Integer itemCount;
}
