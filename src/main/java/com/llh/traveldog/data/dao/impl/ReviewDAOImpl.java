package com.llh.traveldog.data.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.llh.traveldog.data.dao.ReviewDAO;
import com.llh.traveldog.data.entity.Place;
import com.llh.traveldog.data.entity.Review;
import com.llh.traveldog.data.repository.ReviewRepository;

@Component
public class ReviewDAOImpl implements ReviewDAO {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewDAOImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review insertReview(Review review) {
        Review savedReview = reviewRepository.save(review);

        return savedReview;
    }

    @Override
    public Review selectReview(Long pk) {
        Review selectedReview = reviewRepository.getReferenceById(pk);

        return selectedReview;
    }

    @Override
    public Review updateReview(Long pk, String content, Place place) throws Exception {
        if (content == null && place == null) {
            throw new Exception();
        }

        Optional<Review> selectedReview = reviewRepository.findById(pk);

        Review updatedReview;

        if (selectedReview.isPresent()) {
            Review review = selectedReview.get();

            if (content != null) {
                review.setContent(content);
            }

            if (place != null) {
                review.setPlace(place);
            }

            updatedReview = reviewRepository.save(review);
        } else {
            throw new Exception();
        }

        return updatedReview;
    }

    @Override
    public void deleteReview(Long pk) throws Exception {
        Optional<Review> selectedReview = reviewRepository.findById(pk);

        if (selectedReview.isPresent()) {
            Review review = selectedReview.get();
            reviewRepository.delete(review);
        } else {
            throw new Exception();
        }
    }
}
