package zerobase.reservation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.ReservationRepository;
import zerobase.reservation.type.MemberStatus;
import zerobase.reservation.type.ReservationStatus;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService; // 가정: 여러분의 서비스 클래스 이름이 ReservationService라고 합니다.

    private static final String STORE_NAME = "Test Store";

    @Test
    void confirmReservation_validReservation_returnsDto() {
        // Given
        Member member = Member.builder()
                .id(1L)
                .memberStatus(MemberStatus.PARTNER)
                .build();

        Store store = Store.builder()
                .member(member)
                .name(STORE_NAME)
                .description("store Description")
                .location("seoul")
                .x(123.123)
                .y(234.234)
                .build();

        Long reservationId = 1L;
        LocalDateTime mockNow = LocalDateTime.of(2023, 9, 1, 20, 42);
        Reservation mockReservation = Reservation.builder()
                .member(member)
                .store(store)
                .reservationStatus(ReservationStatus.REQUESTED)
                .time(mockNow) // 9분 후의 예약, 여전히 유효
                .createdAt(LocalDateTime.now())
                .build();



        when(memberRepository.findById(any())).thenReturn(Optional.of(member));
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
