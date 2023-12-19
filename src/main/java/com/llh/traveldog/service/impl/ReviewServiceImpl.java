package com.llh.traveldog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.llh.traveldog.data.dao.ReviewDAO;
import com.llh.traveldog.data.dto.ReviewDto;
import com.llh.traveldog.data.dto.ReviewResponseDto;
import com.llh.traveldog.data.dto.UpdateReviewDto;
import com.llh.traveldog.data.entity.Review;
import com.llh.traveldog.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDAO reviewDAO;

    @Autowired
    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    public ReviewResponseDto getReview(Long pk) {
        Review review = reviewDAO.selectReview(pk);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setPk(review.getPk());
        reviewResponseDto.setContent(review.getContent());
        reviewResponseDto.setPlace(review.getPlace());

        return reviewResponseDto;
    }

    @Override
    public ReviewResponseDto saveReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setPlace(review.getPlace());

        Review savedReview = reviewDAO.insertReview(review);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setPk(savedReview.getPk());
        reviewResponseDto.setContent(savedReview.getContent());
        reviewResponseDto.setPlace(savedReview.getPlace());

        return reviewResponseDto;
    }

    @Override
    public ReviewResponseDto updateReview(UpdateReviewDto updateReviewDto) throws Exception {
        Review updatedReview = reviewDAO.updateReview(
            updateReviewDto.getPk(),
            updateReviewDto.getContent(),
            updateReviewDto.getPlace()
        );

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setPk(updatedReview.getPk());
        reviewResponseDto.setContent(updatedReview.getContent());
        reviewResponseDto.setPlace(updatedReview.getPlace());

        return reviewResponseDto;
    }

    @Override
    public void deleteReview(Long pk) throws Exception {
        reviewDAO.deleteReview(pk);
    }
}