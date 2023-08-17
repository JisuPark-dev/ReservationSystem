package zerobase.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.dao.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

}
