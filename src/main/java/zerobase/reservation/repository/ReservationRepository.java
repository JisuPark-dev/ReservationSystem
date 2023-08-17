package zerobase.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.dao.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
