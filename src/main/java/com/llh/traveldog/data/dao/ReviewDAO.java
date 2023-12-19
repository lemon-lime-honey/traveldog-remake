package com.llh.traveldog.data.dao;

import com.llh.traveldog.data.entity.Place;
import com.llh.traveldog.data.entity.Review;

public interface ReviewDAO {
    Review insertReview(Review review);

    Review selectReview(Long pk);

    Review updateReview(Long pk, String content, Place place) throws Exception;

    void deleteReview(Long pk) throws Exception;
}
