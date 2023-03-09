package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.dto.ItemOptionDTO;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.request.StockUpdateRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
    name="keywords",
    uniqueConstraints={
        @UniqueConstraint(
            name="constraintName",
            columnNames={"itemId", "itemOption"}
        )
    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="itemId")
    private Item item;

    @Column(name="itemOption")
    private String option;

    private Integer count;

    public ItemOption(ItemOptionDTO itemOptionDTO, Item item) {
        this.item = item;
        this.option = itemOptionDTO.getOption();
        this.count = itemOptionDTO.getCount();
    }

    public void setStockUpdate(StockUpdateRequest request1) {
        this.count = request1.getItemCount();
    }


//    public void setStockUpdate(StockUpdateRequest request){
//        this.itemCount = request.getItemCount();
//    }
}
