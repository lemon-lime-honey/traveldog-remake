package com.llh.traveldog.service.impl;

import java.util.ArrayList;
import java.util.List;

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
    public List<ReviewResponseDto> getReviewList(Long placePk) {
        List<Review> reviews = reviewDAO.selectReviewAll(placePk);
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();

        for (Review review: reviews) {
            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            reviewResponseDto.setPk(review.getPk());
            reviewResponseDto.setContent(review.getContent());
            reviewResponseDto.setPlacePk(placePk);

            reviewResponseDtos.add(reviewResponseDto);
        }

        return reviewResponseDtos;
    }

    @Override
    public ReviewResponseDto getReview(Long pk) {
        Review review = reviewDAO.selectReview(pk);

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setPk(review.getPk());
        reviewResponseDto.setContent(review.getContent());
        reviewResponseDto.setPlacePk(review.getPlace().getPk());

        return reviewResponseDto;
    }

    @Override
    public ReviewResponseDto saveReview(ReviewDto reviewDto) {
        Review savedReview = reviewDAO.insertReview(reviewDto.getContent(), reviewDto.getPlacePk());

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setPk(savedReview.getPk());
        reviewResponseDto.setContent(savedReview.getContent());
        reviewResponseDto.setPlacePk(savedReview.getPlace().getPk());

        return reviewResponseDto;
    }

    @Override
    public ReviewResponseDto updateReview(UpdateReviewDto updateReviewDto) throws Exception {
        Review updatedReview = reviewDAO.updateReview(
            updateReviewDto.getPk(),
            updateReviewDto.getContent(),
            updateReviewDto.getPlacePk()
        );

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setPk(updatedReview.getPk());
        reviewResponseDto.setContent(updatedReview.getContent());
        reviewResponseDto.setPlacePk(updatedReview.getPlace().getPk());

        return reviewResponseDto;
    }

    @Override
    public void deleteReview(Long pk) throws Exception {
        reviewDAO.deleteReview(pk);
    }
}