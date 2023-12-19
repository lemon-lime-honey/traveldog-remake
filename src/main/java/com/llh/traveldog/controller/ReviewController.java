package com.llh.traveldog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.llh.traveldog.data.dto.ReviewDto;
import com.llh.traveldog.data.dto.ReviewResponseDto;
import com.llh.traveldog.data.dto.UpdateReviewDto;
import com.llh.traveldog.service.ReviewService;

@RestController
@RequestMapping("/review")
@Tag(name = "Review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<ReviewResponseDto> getReview(Long pk) {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(pk);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewResponseDto reviewResponseDto = reviewService.saveReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponseDto);
    }

    @PutMapping
    public ResponseEntity<ReviewResponseDto> updateReview(
        @RequestBody UpdateReviewDto updateReviewDto) throws Exception {
        ReviewResponseDto reviewResponseDto = reviewService.updateReview(updateReviewDto);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReview(Long pk) throws Exception {
        reviewService.deleteReview(pk);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("정상적으로 삭제되었습니다.");
    }
}
