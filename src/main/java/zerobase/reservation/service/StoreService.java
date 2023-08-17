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
        Member member = new Member().builder()
                .username("mockMember")
                .password("mockPassword")
                .memberStatus(MemberStatus.PARTNER)
                .build();
        memberRepository.save(member);
        return storeRepository.save(toStoreEntity(member, storeDto));
    }

    public Store updateStore(StoreDto storeDto, Long id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (!storeOptional.isPresent()) {
            throw new RuntimeException("Store not found with id: " + id);
        }

        Store store = storeOptional.get();
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

}
