package zerobase.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.dao.Store;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Transactional
    void deleteById(Long id);

    List<Store> findByMemberId(Long id);

    Optional<Store> findByName(String name);

    default boolean existsByName(String name) {
        return findByName(name).isPresent();
    }
}
