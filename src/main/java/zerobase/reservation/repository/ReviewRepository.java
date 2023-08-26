package zerobase.reservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.dao.Review;
import zerobase.reservation.dto.ReviewDto;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByMemberIdAndContentIsNotNull(Long memberId, Pageable pageable);
    Page<Review> findAllByStoreIdAndContentIsNotNull(Long storeId, Pageable pageable);
}
