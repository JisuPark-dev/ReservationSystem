package zerobase.reservation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.exception.ReservationException;
import zerobase.reservation.repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static zerobase.reservation.type.ErrorCode.TOO_EARLY_FOR_CONFIRMATION;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService; // 가정: 여러분의 서비스 클래스 이름이 ReservationService라고 합니다.

    @Test
    void confirmReservation_validReservation_returnsDto() {
        // Given
        Long reservationId = 1L;
        LocalDateTime mockNow = LocalDateTime.of(2023, 1, 1, 12, 0);
        Reservation mockReservation = new Reservation();
        mockReservation.setTime(mockNow.plusMinutes(9)); // 9분 후의 예약, 여전히 유효

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(mockReservation));

        // When
        ReservationDto result = reservationService.confirmReservation(reservationId);

        // Then
        assertThat(result).isNotNull();
        verify(reservationRepository).save(any(Reservation.class)); // 확인: save 메서드가 호출되었는지
    }

//    @Test
//    void confirmReservation_tooEarlyForConfirmation_throwsException() {
//        // Given
//        Long reservationId = 1L;
//        LocalDateTime mockNow = LocalDateTime.of(2023, 1, 1, 12, 0);
//        Reservation mockReservation = new Reservation();
//        mockReservation.setTime(mockNow.plusMinutes(11)); // 11분 후의 예약, 아직 확인할 수 없음
//
//        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(mockReservation));
//
//        // When & Then
//        assertThatThrownBy(() -> reservationService.confirmReservation(reservationId))
//                .isInstanceOf(ReservationException.class);
//    }


}
