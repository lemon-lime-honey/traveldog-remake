package com.llh.traveldog.data.repository;

import org.junit.jupiter.api.Test;

import org.locationtech.jts.geom.Coordinate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.llh.traveldog.data.entity.Place;
import com.llh.traveldog.data.entity.Review;

@SpringBootTest
class ReviewRepositoryTest {
    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void addReview() {
        Coordinate coordinate = new Coordinate(37.6572, 126.7643);
        Place place = new Place();
        place.setName("일산 호수공원");
        place.setCoordinate(coordinate);

        placeRepository.save(place);

        Review review = new Review();
        review.setContent("고양시 중에서도 일산을 대표할 만한 곳");
        review.setPlace(placeRepository.getReferenceById(1L));
        reviewRepository.save(review);

        System.out.println(review.toString());
    }
}
