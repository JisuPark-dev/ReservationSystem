package zerobase.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.StoreDto;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.StoreRepository;
import zerobase.reservation.type.MemberStatus;

import java.time.LocalDateTime;
import java.util.Optional;

import static zerobase.reservation.dto.StoreDto.toStoreEntity;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public Store join(StoreDto storeDto) {
        //TODO : 지금은 member를 만들어서 넣고 있긴 한데, 그러면 안된다.

        Optional<Member> memberOptional = memberRepository.findById(storeDto.getMemberId());

        if (!memberOptional.isPresent()) {
            throw new RuntimeException("Member not found with id: " + storeDto.getMemberId());
        }

        Member member = memberOptional.get();
        return storeRepository.save(toStoreEntity(member, storeDto));
    }

    public Store updateStore(StoreDto storeDto, Long id) {
        Store store = storeRepository.findById(id).get();

        if (storeDto.getName() != null && !storeDto.getName().isEmpty()) {
            store.setName(storeDto.getName());
        }
        if (storeDto.getLocation() != null && !storeDto.getLocation().isEmpty()) {
            store.setLocation(storeDto.getLocation());
        }
        if (storeDto.getDescription() != null && !storeDto.getDescription().isEmpty()) {
            store.setDescription(storeDto.getDescription());
        }
        store.setUpdatedAt(LocalDateTime.now());
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
