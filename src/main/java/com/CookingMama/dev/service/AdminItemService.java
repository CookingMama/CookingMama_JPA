package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Admin;
import com.CookingMama.dev.domain.entity.Category;
import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.response.AdminItemListResponse;
import com.CookingMama.dev.domain.response.ItemListResponse;
import com.CookingMama.dev.repository.AdminItemListRepository;
import com.CookingMama.dev.repository.AdminItemRegistRepository;
import com.CookingMama.dev.repository.AdminRepository;
import com.CookingMama.dev.repository.CategoryRepository;
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

    public List<ItemListResponse> adminItemList(){
        Long adminId = securityService.tokenToAdminDTO(securityService.getToken()).getId();
        List<Item> items = adminItemListRepository.findByAdminId(adminId);
        List<ItemListResponse> responses = items.stream()
                .map(ItemListResponse::new)
                .collect(Collectors.toList());
        return responses;
    }
}
