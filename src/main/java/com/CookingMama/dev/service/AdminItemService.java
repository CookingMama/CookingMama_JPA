package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.*;
import com.CookingMama.dev.domain.request.AdminUpdateItemRequest;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.request.StockUpdateRequest;
import com.CookingMama.dev.domain.response.AdminItemDetailResponse;
import com.CookingMama.dev.domain.response.ItemListResponse;
import com.CookingMama.dev.domain.response.ReviewListResponse;
import com.CookingMama.dev.domain.response.StockManagementResponse;
import com.CookingMama.dev.repository.*;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminItemService {
    private final SecurityService securityService;
    private final AdminItemRegistRepository adminItemRegistRepository;
    private final AdminItemListRepository adminItemListRepository;
    private final AdminRepository adminRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final ItemOptionRepository itemOptionRepository;

    // 상품 등록
    public List<ItemOption> itemRegist(ItemRegistRequest request){
        Integer categoryId = request.getCategory();
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        Optional<Category> findCategory = categoryRepository.findById(categoryId);
        Category category = findCategory.orElseThrow(NullPointerException::new);
        Optional<Admin> findById = adminRepository.findById(adminId);
        Admin admin = findById.orElseThrow(NullPointerException::new);
        Item item = new Item(request, admin, category);
        Item entity = adminItemRegistRepository.save(item);
        List<ItemOption> itemOption =  request.getItemOption().stream().map(el-> new ItemOption(el, entity)).collect(Collectors.toList());
        return itemOptionRepository.saveAll(itemOption);
    }
    // 상품 리스트 조회
    public List<ItemListResponse> adminItemList(){
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        List<Item> items = adminItemListRepository.findByAdminId(adminId);
        List<ItemListResponse> responses = items.stream()
                .map(ItemListResponse::new)
                .collect(Collectors.toList());
        return responses;
    }
    // 상품 상세보기
    public AdminItemDetailResponse adminItemDetail(Long itemId){
        Optional<Item> item = adminItemListRepository.findById(itemId);
        Item item1 = item.orElseThrow(NullPointerException::new);
        AdminItemDetailResponse adminItemDetailResponse = new AdminItemDetailResponse(item1);
        List<Review> reviews = reviewRepository.findByItemId(itemId);
        List<ReviewListResponse> responses = reviews.stream().map(ReviewListResponse::new).collect(Collectors.toList());
        adminItemDetailResponse.setReviews(responses);
        return adminItemDetailResponse;
    }
    // 상품 정보 수정
    public String adminItemUpdate(Long itemId, AdminUpdateItemRequest request){
        Optional<Category> category = categoryRepository.findById(request.getCategory());
        Category category1 = category.orElseThrow(NullPointerException::new);
        Optional<Item> item = adminItemListRepository.findById(itemId);
        Item item1 = item.orElseThrow(NullPointerException::new);
        try {
            item1.setItem(request, category1);
            adminItemListRepository.save(item1);
            return "상품 수정이 완료되었습니다.";
        }
        catch (NullPointerException e) {
            return "상품 수정이 실패하였습니다.";
        }
    }
    // 재고 리스트 조회
    public List<StockManagementResponse> adminStockView(){
            Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
            List<Item> item = adminItemListRepository.findByAdminId(adminId);
            List<StockManagementResponse> stockList = item.stream()
                    .map(StockManagementResponse::new)
                    .collect(Collectors.toList());
            return stockList;
        }
    // 재고 수정

    // 아이템 옵션에 대한 id를 받아와야함.
    public String adminStockUpdate(List<StockUpdateRequest> request){
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        try {
            for(StockUpdateRequest request1:request){
                Optional<ItemOption> findById = itemOptionRepository.findById(request1.getId());
                ItemOption itemOption = findById.orElseThrow(NullPointerException::new);
                itemOption.setStockUpdate(request1);
                itemOptionRepository.save(itemOption);
            }
            return "재고가 수정되었습니다.";
        }catch (NullPointerException e){
            return "다시 시도해주세요.";
        }
    }

}

