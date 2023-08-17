package zerobase.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dao.Review;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.ReservationRepository;
import zerobase.reservation.repository.ReviewRepository;
import zerobase.reservation.repository.StoreRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static zerobase.reservation.dto.ReservationDto.toReservationEntity;

@Service
@RequiredArgsConstructor
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
}
