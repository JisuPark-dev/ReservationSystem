package zerobase.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dao.Review;
import zerobase.reservation.dao.Store;
import zerobase.reservation.type.ReservationStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class ReservationDto {
    private Long memberId;
    private Long storeId;
    private Long reviewId;
    private ReservationStatus reservationStatus;
    private LocalDateTime time;
    private LocalDateTime updatedAt;

    public static Reservation toReservationEntity(
            Member member,
            Store store,
            Review review,
            ReservationDto reservationDto
    ) {
        return new Reservation().builder()
                .member(member)
                .store(store)
                .review(review)
                .reservationStatus(reservationDto.getReservationStatus())
                .time(reservationDto.getTime())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
