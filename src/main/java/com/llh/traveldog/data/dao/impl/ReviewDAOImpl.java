package com.llh.traveldog.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.llh.traveldog.data.dao.ReviewDAO;
import com.llh.traveldog.data.entity.Place;
import com.llh.traveldog.data.entity.Review;
import com.llh.traveldog.data.repository.PlaceRepository;
import com.llh.traveldog.data.repository.ReviewRepository;

@Component
public class ReviewDAOImpl implements ReviewDAO {
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewDAOImpl(PlaceRepository placeRepository, ReviewRepository reviewRepository) {
        this.placeRepository = placeRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review insertReview(String content, Long placePk) {
        Review review = new Review();
        review.setContent(content);
        review.setPlace(placeRepository.findById(placePk).get());
        Review savedReview = reviewRepository.save(review);

        return savedReview;
    }

    @Override
    public List<Review> selectReviewAll(Long placePk) {
        List<Review> allReviews = reviewRepository.findByPlace_Pk(placePk);

        return allReviews;
    }

    @Override
    public Review selectReview(Long pk) {
        Review selectedReview = reviewRepository.getReferenceById(pk);

        return selectedReview;
    }

    @Override
    public Review updateReview(Long pk, String content, Long placePk) throws Exception {
        if (content == null && (placePk == null || placePk == 0)) {
            throw new Exception();
        }

        Optional<Review> selectedReview = reviewRepository.findById(pk);

        Review updatedReview;

        if (selectedReview.isPresent()) {
            Review review = selectedReview.get();

            if (content != null) {
                review.setContent(content);
            }

            if (!(placePk != null || placePk != 0)) {
                Place place = placeRepository.findById(placePk).get();
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
