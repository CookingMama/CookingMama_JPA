package com.CookingMama.dev.domain.request;

import com.CookingMama.dev.domain.dto.ItemOptionDTO;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
public class ItemRegistRequest {
    private Long adminId;
    private Integer category;
    private String itemName;
    private List<ItemOptionDTO> itemOption;
    private Integer itemPrice;
    private String itemImage;
    private String itemInfo;

}
