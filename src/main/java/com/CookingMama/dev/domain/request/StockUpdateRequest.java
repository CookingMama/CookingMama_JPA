package com.CookingMama.dev.domain.request;

import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class StockUpdateRequest {
    private Integer adminId;
    private String itemName;
    private String itemOption;
    private Integer itemCount;
}
