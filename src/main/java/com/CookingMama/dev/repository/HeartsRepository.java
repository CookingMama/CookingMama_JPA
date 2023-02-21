package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Hearts;
import com.CookingMama.dev.domain.response.HeartsResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartsRepository extends JpaRepository<Hearts, Long> {
    List<Hearts> findByUserId(Long userId);
}
