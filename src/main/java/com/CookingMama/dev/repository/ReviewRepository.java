package com.CookingMama.dev.repository;

import com.CookingMama.dev.domain.entity.Review;
import com.CookingMama.dev.domain.entity.ReviewID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, ReviewID> {
    List<Review> findByItemId(Long itemId);

    Long countByItemId(Long itemId);

    List<Review> findTop6ByOrderByCreatedAt();

    Review findByItemIdAndUserId(Long itemId, Long userId);

    List<Review> findByUserId(Long userId);
}
