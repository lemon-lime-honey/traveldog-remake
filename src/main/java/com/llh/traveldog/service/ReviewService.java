package com.llh.traveldog.service;

import com.llh.traveldog.data.dto.ReviewDto;
import com.llh.traveldog.data.dto.ReviewResponseDto;
import com.llh.traveldog.data.dto.UpdateReviewDto;

public interface ReviewService {
    ReviewResponseDto getReview(Long pk);

    ReviewResponseDto saveReview(ReviewDto reviewDto);

    ReviewResponseDto updateReview(UpdateReviewDto updateReviewDto) throws Exception;

    void deleteReview(Long pk) throws Exception;
}
