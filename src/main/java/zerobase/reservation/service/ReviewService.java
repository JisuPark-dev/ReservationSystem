package zerobase.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dao.Review;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.ReviewDto;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.ReservationRepository;
import zerobase.reservation.repository.ReviewRepository;
import zerobase.reservation.repository.StoreRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static zerobase.reservation.dto.MemberDto.toMemberEntity;
import static zerobase.reservation.dto.ReviewDto.toReviewEntity;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;

    public Review join(ReviewDto reviewDto) {
        Member member = memberRepository.findById(reviewDto.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("No Member found with ID: " + reviewDto.getMemberId()));

        Store store = storeRepository.findById(reviewDto.getStoreId())
                .orElseThrow(() -> new NoSuchElementException("No Store found with ID: " + reviewDto.getStoreId()));

        Reservation reservation = reservationRepository.findById(reviewDto.getReservationId())
                .orElseThrow(() -> new NoSuchElementException("No Reservation found with ID: " + reviewDto.getReservationId()));

        // Check if reservation already has a review
        if(reservation.getReview() != null) {
            throw new IllegalStateException("This reservation already has a review.");
        }

        Review review = reviewRepository.save(toReviewEntity(member, store, reservation, reviewDto));

        reservation.setReview(review);

        return review;
    }

    @Transactional(readOnly = true)
    public List<Review> findAllByMemberId(Long memberId) {
        return reviewRepository.findAllByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public List<Review> findAllByStoreId(Long StoreId) {
        return reviewRepository.findAllByStoreId(StoreId);
    }

    public Review updateStore(ReviewDto reviewDto, Long id) {
        Review review = reviewRepository.findById(id).get();

        if (reviewDto.getContent() != null && !reviewDto.getContent().isEmpty()) {
            review.setContent(reviewDto.getContent());
        }

        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteStore(Long id) {
        reviewRepository.deleteById(id);
    }
}
