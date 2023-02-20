package com.CookingMama.dev.service;

import com.CookingMama.dev.domain.entity.Item;
import com.CookingMama.dev.domain.request.ItemRegistRequest;
import com.CookingMama.dev.domain.response.ItemListResponse;
import com.CookingMama.dev.repository.AdminItemListRepository;
import com.CookingMama.dev.repository.AdminItemRegistRepository;
import com.CookingMama.dev.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminItemService {
    private final SecurityService securityService;
    private final AdminItemRegistRepository adminItemRegistRepository;
    private final AdminItemListRepository adminItemListRepository;

    public Item itemRegist(ItemRegistRequest request){
        request.setAdminId(securityService.tokenToAdminDTO(securityService.getToken()).getId());
        Item item = new Item(request);
        log.info(item.toString());
        return adminItemRegistRepository.save(item);
    }

    public List<Item> adminItemList(Long adminId){
        return adminItemListRepository.findByAdminId(adminId);
    }
}
