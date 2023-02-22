package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Admin;
import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.entity.OrderInfo;
import com.CookingMama.dev.domain.entity.Review;
import com.CookingMama.dev.domain.request.AdminLoginRequest;
import com.CookingMama.dev.domain.request.AdminOrderRequest;
import com.CookingMama.dev.domain.request.AdminSignUpRequest;
import com.CookingMama.dev.domain.response.AdminOrderListResponse;
import com.CookingMama.dev.domain.response.AdminResponse;
import com.CookingMama.dev.domain.response.ReviewListResponse;
import com.CookingMama.dev.exception.EmailCheckException;
import com.CookingMama.dev.repository.AdminItemListRepository;
import com.CookingMama.dev.repository.AdminRepository;
import com.CookingMama.dev.repository.OrderRepository;
import com.CookingMama.dev.repository.ReviewRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final SecurityService securityService;
    private final OrderRepository orderRepository;
    private final AdminItemListRepository adminItemListRepository;
    private final ReviewRepository reviewRepository;

    public AdminResponse adminLoginService(AdminLoginRequest request){
        Optional<Admin> findByAdminEmailAndAdminPw = adminRepository.findByAdminEmailAndAdminPw(request.getAdminEmail(), request.getAdminPw());
        Admin admin = findByAdminEmailAndAdminPw.orElseThrow(NullPointerException::new);
        String token = securityService.createAdminToken(admin);
        AdminResponse adminResponse = new AdminResponse(admin.getAdminEmail(), token);
        return adminResponse;
    }
    public AdminResponse adminSignUpService(AdminSignUpRequest request) throws EmailCheckException {
        Admin admin = request.toEntity();
        Optional<Admin> findAdminEmail = adminRepository.findByAdminEmail(request.getAdminEmail());
        if(findAdminEmail.isPresent()){
            throw new EmailCheckException();
        }
        adminRepository.save(admin);
        AdminLoginRequest adminLoginRequest = new AdminLoginRequest(admin.getAdminEmail(), admin.getAdminPw());
        return adminLoginService(adminLoginRequest);
    }
    // 주문내역 조회
    public List<AdminOrderListResponse> adminOrderList(){
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        List<OrderInfo> orderInfo = orderRepository.findByAdminId(adminId);
        List<AdminOrderListResponse> adminOrderList = orderInfo.stream()
                .map(AdminOrderListResponse::new)
                .collect(Collectors.toList());
        return adminOrderList;
    }
    // 주문내역 수정(배송처리 & 송장번호 입력)
    public String adminOrderUpdate(List<AdminOrderRequest> request){
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        try {
            for(AdminOrderRequest request1:request){
                OrderInfo orderInfo = orderRepository.findByAdminIdAndId(adminId, request1.getId());
                orderInfo.orderRequest(request1);
                orderRepository.save(orderInfo);
            }
            return "주문 내역이 변경되었습니다.";
        }catch (NullPointerException e){
            return "주문 내역 변경이 실패하였습니다.";
        }
    }


    public List<ReviewListResponse> getAdminItemReviewList() {
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        List<Item> findItem = adminItemListRepository.findByAdminId(adminId);
        List<Long> itemIds = new ArrayList<>();
        for (Item item : findItem) itemIds.add(item.getId());
        List<ReviewListResponse> responses = new ArrayList<>();
        for (Long itemId : itemIds){
            List<Review> reviewList = reviewRepository.findTop30ByItemId(itemId);
            List<ReviewListResponse> reviewListResponses = reviewList.stream().map(ReviewListResponse::new).collect(Collectors.toList());
            responses.addAll(reviewListResponses);
        }
        return responses;
    }
}
