package zerobase.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dao.Review;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.ReservationRepository;
import zerobase.reservation.repository.ReviewRepository;
import zerobase.reservation.repository.StoreRepository;
import zerobase.reservation.type.ReservationStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static zerobase.reservation.dto.ReservationDto.toReservationEntity;
import static zerobase.reservation.type.ReservationStatus.CANCELED;
import static zerobase.reservation.type.ReservationStatus.CONFIRMED;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private static Review review;
    public Reservation join(ReservationDto reservationDto) {
        Member member = memberRepository.findById(reservationDto.getMemberId()).get();
        Store store = storeRepository.findById(reservationDto.getStoreId()).get();
        Review review = null;

        return reservationRepository.save(toReservationEntity(member, store, review, reservationDto));
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAllByMemberId(Long memberId) {
        return reservationRepository.findAllByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAllByStoreId(Long StoreId) {
        return reservationRepository.findAllByStoreId(StoreId);
    }

    public Reservation confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservation.setReservationStatus(CONFIRMED);
        //TODO : 예약시간 10분 안에 들어온 요청인지 확인 필요.
        return reservation;
    }

    public Reservation cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservation.setReservationStatus(CANCELED);
        //TODO : 시간안에 확정 못할시에도 취소되도록 해야함.
        return reservation;
    }

    public List<Reservation> findAllConfirmedReservationWithoutReview(Long memberId) {
        return reservationRepository.findByMemberIdAndReservationStatusAndReviewIsNull(memberId, CONFIRMED);
    }
}
