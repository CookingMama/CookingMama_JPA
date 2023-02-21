package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminOrderRepository extends JpaRepository<OrderInfo, Long> {
    List<OrderInfo> findByAdminId(Long adminId);
}
