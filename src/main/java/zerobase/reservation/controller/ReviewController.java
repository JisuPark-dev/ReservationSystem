package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dao.Review;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.ReviewDto;
import zerobase.reservation.dto.StoreDto;
import zerobase.reservation.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Review> create(
            @RequestBody ReviewDto reviewDto
    ) {
        Review review = reviewService.join(reviewDto);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/reviews/member/{memberId}")
    public ResponseEntity<List<Review>> findAllByMemberId(
            @PathVariable("memberId") Long id
    ) {
        List<Review> reviewsByMemberId = reviewService.findAllByMemberId(id);
        return ResponseEntity.ok(reviewsByMemberId);
    }

    @GetMapping("/reviews/store/{storeId}")
    public ResponseEntity<List<Review>> findAllByStoreId(
            @PathVariable("storeId") Long id
    ) {
        List<Review> reviewsByMemberId = reviewService.findAllByStoreId(id);
        return ResponseEntity.ok(reviewsByMemberId);
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<Review> update(
            @RequestBody ReviewDto reviewDto,
            @PathVariable("reviewId") Long id
    ) {
        Review review = reviewService.updateStore(reviewDto, id);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/review/{reviewId}")
    public void delete(
            @PathVariable("reviewId") Long id
    ) {
        reviewService.deleteStore(id);
    }
}
