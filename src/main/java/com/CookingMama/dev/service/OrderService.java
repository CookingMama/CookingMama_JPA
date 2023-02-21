package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.*;
import com.CookingMama.dev.domain.request.UserOrderCancelRequest;
import com.CookingMama.dev.domain.request.UserOrderRequest;
import com.CookingMama.dev.domain.response.AdminOrderListResponse;
import com.CookingMama.dev.domain.response.OrderResponse;
import com.CookingMama.dev.repository.AdminRepository;
import com.CookingMama.dev.repository.CategoryRepository;
import com.CookingMama.dev.repository.OrderRepository;
import com.CookingMama.dev.repository.UserItemRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserItemRepository userItemRepository;
    private final AdminRepository adminRepository;
    private final CategoryRepository categoryRepository;
    private final SecurityService securityService;
    @Transactional
    public String addOrders(List<UserOrderRequest> requests){
        User user = securityService.tokenToUser(securityService.getToken());
        try {
            for (UserOrderRequest request : requests) {
                Admin admin = adminRepository.getById(request.getAdminId());
                Category category = categoryRepository.getById(request.getCategoryId());
                Optional<Item> findById = userItemRepository.findById(request.getItemId());
                Item item = findById.orElseThrow(NullPointerException::new);
                item.setItemCount(item.getItemCount() - request.getItemCount());
                userItemRepository.save(item);
                OrderInfo orderInfo = new OrderInfo(request, admin, item, user, category);
                orderRepository.save(orderInfo);
            }
            return "주문이 성공적으로 완료되었습니다.";
        }catch(Exception e){
            return "주문에 실패하였습니다!";
        }
    }

    public List<OrderResponse> myOrders(){
        Long userId = securityService.tokenToDTO(securityService.getToken()).getId();
        List<OrderInfo> orderInfo = orderRepository.findByUserId(userId);
        List<OrderResponse> userOrderList = orderInfo.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
        return userOrderList;
    }

    @Transactional
    public String cancelOrders(List<UserOrderCancelRequest> requests, Long orderNumber){
        Long userId = securityService.tokenToDTO(securityService.getToken()).getId();

        try{
            List<OrderInfo> changeStatus = orderRepository.findByOrderNumberAndUserId(orderNumber, userId);
            if(changeStatus.isEmpty())return "주문 취소에 실패했습니다.";
            for(OrderInfo orderInfo : changeStatus){
                if(orderInfo.getStatus() == 2)return "이미 취소된 주문입니다.";
                orderInfo.setStatus(2);
            }
            for(UserOrderCancelRequest request : requests){
                Optional<Item> findById = userItemRepository.findById(request.getItemId());
                Item item = findById.orElseThrow(NullPointerException::new);
                item.setItemCount(item.getItemCount() + request.getItemCount());
            }
            return "주문이 취소되었습니다.";
        }catch (Exception e){
            return "주문 취소에 실패했습니다.";
        }

    }
}
