package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.repository.ReservationRepository;
import zerobase.reservation.service.ReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> create(
            @RequestBody ReservationDto reservationDto
    ) {
        Reservation reservation = reservationService.join(reservationDto);
        return ResponseEntity.ok(reservation);
    }


    @GetMapping("/reservations/member/{memberId}")
    public ResponseEntity<List<Reservation>> findAllByMemberId(
            @PathVariable("memberId") Long id
    ) {
        List<Reservation> reservationsByMemberId = reservationService.findAllByMemberId(id);
        return ResponseEntity.ok(reservationsByMemberId);
    }

    @GetMapping("/reservations/store/{storeId}")
    public ResponseEntity<List<Reservation>> findAllByStoreId(
            @PathVariable("storeId") Long id
    ) {
        List<Reservation> reservationsByStoreId = reservationService.findAllByStoreId(id);
        return ResponseEntity.ok(reservationsByStoreId);
    }
    @GetMapping("reservations/without_review/member/{memberId}")
    public ResponseEntity<List<Reservation>> findAllConfirmedReservationWithoutReview(
            @PathVariable("memberId") Long id
    ) {
        List<Reservation> reservationsWithoutReview = reservationService.findAllConfirmedReservationWithoutReview(id);
        return ResponseEntity.ok(reservationsWithoutReview);
    }

    @PutMapping("/reservation/confirm")
    public ResponseEntity<Reservation> confirm(
            @RequestParam Long reservationId
    ) {
        Reservation reservation = reservationService.confirmReservation(reservationId);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/reservation/cancel")
    public ResponseEntity<Reservation> cancel(
            @RequestParam Long reservationId
    ) {
        Reservation reservation = reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok(reservation);
    }


}
