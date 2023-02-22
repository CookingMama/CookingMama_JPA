package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmailAndUserPw(String userEmail, String userPw);

    Optional<User> findByUserEmail(String userEmail);
}