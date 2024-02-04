package com.llh.traveldog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.llh.traveldog.data.dto.ReviewDto;
import com.llh.traveldog.data.dto.ReviewResponseDto;
import com.llh.traveldog.data.dto.UpdateReviewDto;
import com.llh.traveldog.service.ReviewService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Tag(name = "Review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/place/{placePk}/review")
    public ResponseEntity<CollectionModel<EntityModel<ReviewResponseDto>>> getReviewList(@PathVariable Long placePk) {
        List<EntityModel<ReviewResponseDto>> reviewResponseDtos = reviewService.getReviewList(placePk).stream()
            .map(review -> EntityModel.of(review,
                linkTo(methodOn(ReviewController.class).getReview(placePk, review.getPk())).withSelfRel()))
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(CollectionModel.of(reviewResponseDtos, linkTo(methodOn(ReviewController.class).getReviewList(placePk)).withSelfRel()));
    }

    @GetMapping("/place/{placePk}/review/{pk}")
    public ResponseEntity<EntityModel<ReviewResponseDto>> getReview(@PathVariable Long placePk, @PathVariable Long pk) {
        ReviewResponseDto reviewResponseDto = reviewService.getReview(pk);
        return ResponseEntity.status(HttpStatus.OK).body(EntityModel.of(reviewResponseDto,
            linkTo(methodOn(ReviewController.class).getReview(placePk, pk)).withSelfRel(),
            linkTo(methodOn(ReviewController.class).getReviewList(placePk)).withRel("review")));
    }

    @PostMapping("/place/{placePk}/review")
    public ResponseEntity<String> createReview(@PathVariable Long placePk, @RequestBody ReviewDto reviewDto) {
        ReviewResponseDto reviewResponseDto = reviewService.saveReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("정상적으로 저장되었습니다.");
    }

    @PutMapping("/place/{placePk}/review/{pk}")
    public ResponseEntity<String> updateReview(
        @PathVariable Long placePk,
        @PathVariable Long pk,
        @RequestBody UpdateReviewDto updateReviewDto) throws Exception {
        ReviewResponseDto reviewResponseDto = reviewService.updateReview(updateReviewDto);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 수정되었습니다.");
    }

    @DeleteMapping("/place/{placePk}/review/{pk}")
    public ResponseEntity<String> deleteReview(@PathVariable Long placePk, @PathVariable Long pk) throws Exception {
        reviewService.deleteReview(pk);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("정상적으로 삭제되었습니다.");
    }
}
