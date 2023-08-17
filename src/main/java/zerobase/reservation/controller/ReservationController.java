package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.dao.Reservation;
import zerobase.reservation.dto.ReservationDto;
import zerobase.reservation.service.ReservationService;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> create(
            @RequestBody ReservationDto reservationDto
    ) {
        Reservation reservation = reservationService.join(reservationDto);
        return ResponseEntity.ok(reservation);
    }
}
