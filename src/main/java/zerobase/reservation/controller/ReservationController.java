package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.service.ReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> create(
            @RequestBody ReservationDto reservationDto
    ) {
        reservationDto = reservationService.join(reservationDto);
        return ResponseEntity.ok(reservationDto);
    }


    @GetMapping("/reservations/member/{memberId}")
    public ResponseEntity<List<ReservationDto>> findAllByMemberId(
            @PathVariable("memberId") Long id
    ) {
        List<ReservationDto> reservationDtos = reservationService.findAllByMemberId(id);
        return ResponseEntity.ok(reservationDtos);
    }

    @GetMapping("/reservations/store/{storeId}")
    public ResponseEntity<List<ReservationDto>> findAllByStoreId(
            @PathVariable("storeId") Long id
    ) {
        List<ReservationDto> reservationDtos = reservationService.findAllByStoreId(id);
        return ResponseEntity.ok(reservationDtos);
    }

    @GetMapping("reservations/without_review/member/{memberId}")
    public ResponseEntity<List<ReservationDto>> findAllConfirmedReservationWithoutReview(
            @PathVariable("memberId") Long id
    ) {
        List<ReservationDto> reservationDtosWithoutReview = reservationService.findAllConfirmedReservationWithoutReview(id);
        return ResponseEntity.ok(reservationDtosWithoutReview);
    }

    @PutMapping("/reservation/confirm")
    public ResponseEntity<ReservationDto> confirm(
            @RequestParam Long reservationId
    ) {
        ReservationDto reservation = reservationService.confirmReservation(reservationId);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/reservation/cancel")
    public ResponseEntity<ReservationDto> cancel(
            @RequestParam Long reservationId
    ) {
        ReservationDto reservation = reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok(reservation);
    }
}
