package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Admin;
import com.CookingMama.dev.domain.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAdminEmailAndAdminPw(String adminEmail, String adminPw);
    Optional<Admin> findByAdminEmail(String adminEmail);
}
