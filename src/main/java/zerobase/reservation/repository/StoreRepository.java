package zerobase.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.dao.Store;

import javax.transaction.Transactional;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Transactional
    void deleteById(Long id);

    List<Store> findByMemberId(Long id);
}
