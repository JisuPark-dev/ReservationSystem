package zerobase.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.reservation.dao.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

}
