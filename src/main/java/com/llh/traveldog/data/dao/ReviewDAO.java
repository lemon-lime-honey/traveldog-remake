package com.llh.traveldog.data.dao;

import java.util.List;

import com.llh.traveldog.data.entity.Review;

public interface ReviewDAO {
    Review insertReview(String content, Long placePk);

    List<Review> selectReviewAll(Long placePk);

    Review selectReview(Long pk);

    Review updateReview(Long pk, String content, Long placePk) throws Exception;

    void deleteReview(Long pk) throws Exception;
}
