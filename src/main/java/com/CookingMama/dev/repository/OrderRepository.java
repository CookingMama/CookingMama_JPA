package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
    List<OrderInfo> findByUserId(Long userId);
    OrderInfo findOneByUserId(Long userId);
    List<OrderInfo> findByAdminId(Long adminId);

    List<OrderInfo> findByOrderNumberAndUserId(Long orderNumber, Long userId);
}
