package com.Sandy.carrotMarket.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserIdAndCategoryId(Long userIdx, Long categoryIdx);
}
