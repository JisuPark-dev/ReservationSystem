package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.dto.Result;
import zerobase.reservation.dto.ReviewDto;
import zerobase.reservation.service.ReviewService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<ReviewDto> create(
            @RequestBody ReviewDto reviewDto
    ) {
        reviewDto = reviewService.join(reviewDto);
        return ResponseEntity.ok(reviewDto);
    }

    @GetMapping("/reviews/member/{memberId}")
    public ResponseEntity<Map<String, Object>> findAllByMemberId(
            @PathVariable("memberId") Long id,
            Pageable pageable
    ) {
        Page<ReviewDto> reservationPage = reviewService.findAllByMemberId(id, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", reservationPage.getContent());
        response.put("currentPage", reservationPage.getNumber());
        response.put("totalItems", reservationPage.getTotalElements());
        response.put("totalPages", reservationPage.getTotalPages());

        return ResponseEntity.ok(response);
    }


    @GetMapping("/reviews/store/{storeId}")
    public ResponseEntity<Map<String, Object>> findAllByStoreId(
            @PathVariable("storeId") Long id,
            Pageable pageable
    ) {
        Page<ReviewDto> reviewDtosByMemberId = reviewService.findAllByStoreId(id, pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("data", reviewDtosByMemberId.getContent());
        response.put("currentPage", reviewDtosByMemberId.getNumber());
        response.put("totalItems", reviewDtosByMemberId.getTotalElements());
        response.put("totalPages", reviewDtosByMemberId.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<ReviewDto> update(
            @RequestBody ReviewDto reviewDto,
            @PathVariable("reviewId") Long id
    ) {
        reviewDto = reviewService.updateStore(reviewDto, id);
        return ResponseEntity.ok(reviewDto);
    }

    @DeleteMapping("/review/{reviewId}")
    public void delete(
            @PathVariable("reviewId") Long id
    ) {
        reviewService.deleteStore(id);
    }
}
