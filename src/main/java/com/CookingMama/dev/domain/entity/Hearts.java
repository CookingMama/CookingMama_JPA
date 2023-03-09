package com.CookingMama.dev.domain.entity;

import com.CookingMama.dev.domain.request.HeartsRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class Hearts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer count = 1;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "item_option_id")
    private ItemOption itemOption;

    public void userHeartsUpdate(HeartsRequest request){
        this.count = request.getCount();
    }
}
