package com.llh.traveldog.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.llh.traveldog.data.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlace_Pk(Long placePk);
}
