package com.CookingMama.dev.domain.response;

import com.CookingMama.dev.domain.entity.ItemOption;
import com.CookingMama.dev.domain.entity.OrderInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long orderNumber;
    private LocalDateTime orderDate;
    private String categoryName;
    private String adminName;
    private Long itemId;
    private String itemName;
    private String itemOption;
    private Integer itemCount;
    private Integer itemPrice;
    private Integer itemDiscount;
    private Integer itemTotalPrice;
    private String userName;
    private String userAddress;
    private String userAddressDetail;
    private String userZipCode;
    private String userPhoneNumber;
    private Integer status;
    private String trackingNumber;

    public OrderResponse(OrderInfo orderInfo){
        this.orderNumber = orderInfo.getOrderNumber();
        this.orderDate = orderInfo.getOrderDate();
        this.categoryName = orderInfo.getCategory().getCategoryName();
        this.adminName = orderInfo.getAdmin().getAdminName();
        this.itemId = orderInfo.getItem().getId();
        this.itemName = orderInfo.getItem().getItemName();
        this.itemOption = orderInfo.getItemOption();
        this.itemCount = orderInfo.getItemCount();
        this.itemPrice = orderInfo.getItem().getItemPrice();
        this.itemDiscount = orderInfo.getItemDiscount();
        this.itemTotalPrice = orderInfo.getItemTotalPrice();
        this.userName = orderInfo.getUser().getUserName();
        this.userAddress = orderInfo.getUser().getUserAddress();
        this.userAddressDetail = orderInfo.getUser().getUserAddressDetail();
        this.userZipCode = orderInfo.getUser().getUserZipCode();
        this.userPhoneNumber = orderInfo.getUser().getUserPhoneNumber();
        this.status = orderInfo.getStatus();
        this.trackingNumber = orderInfo.getTrackingNumber();
    }
}
