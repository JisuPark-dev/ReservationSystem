package zerobase.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.dao.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
