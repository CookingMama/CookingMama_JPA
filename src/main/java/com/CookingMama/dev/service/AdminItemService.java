package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Admin;
import com.CookingMama.dev.domain.entity.Category;
import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.request.AdminUpdateItemRequest;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.response.AdminItemDetailResponse;
import com.CookingMama.dev.domain.response.ItemListResponse;
import com.CookingMama.dev.repository.*;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    // 상품 등록
    public Item itemRegist(ItemRegistRequest request){
        Integer categoryId = request.getCategory();
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        Optional<Category> findCategory = categoryRepository.findById(categoryId);
        Category category = findCategory.orElseThrow(NullPointerException::new);
        Optional<Admin> findById = adminRepository.findById(adminId);
        Admin admin = findById.orElseThrow(NullPointerException::new);
        Item item = new Item(request, admin, category);
        return adminItemRegistRepository.save(item);
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
        catch (NullPointerException e){
            return "상품 수정이 실패하였습니다.";
        }

    }
}
