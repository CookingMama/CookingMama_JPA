package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Admin;
import com.CookingMama.dev.domain.entity.OrderInfo;
import com.CookingMama.dev.domain.request.AdminLoginRequest;
import com.CookingMama.dev.domain.request.AdminOrderRequest;
import com.CookingMama.dev.domain.request.AdminSignUpRequest;
import com.CookingMama.dev.domain.response.AdminOrderListResponse;
import com.CookingMama.dev.domain.response.AdminResponse;
import com.CookingMama.dev.exception.EmailCheckException;
import com.CookingMama.dev.repository.AdminOrderRepository;
import com.CookingMama.dev.repository.AdminRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final SecurityService securityService;
    private final AdminOrderRepository adminOrderRepository;

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
        List<OrderInfo> orderInfo = adminOrderRepository.findByAdminId(adminId);
        List<AdminOrderListResponse> adminOrderList = orderInfo.stream()
                .map(AdminOrderListResponse::new)
                .collect(Collectors.toList());
        return adminOrderList;
    }
    // 주문내역 수정(배송처리 & 송장번호 입력)
    public String adminOrderUpdate(Long orderId, AdminOrderRequest request){
        Optional<OrderInfo> orderInfo = adminOrderRepository.findById(orderId);
        OrderInfo orderInfo1 = orderInfo.orElseThrow(NullPointerException::new);
        try {
            orderInfo1.adminOrderRequest(request);
            adminOrderRepository.save(orderInfo1);
            return "주문 내역이 변경되었습니다.";
        }catch (NullPointerException e){
            return "주문 내역 변경이 실패하였습니다.";
        }
    }
}
