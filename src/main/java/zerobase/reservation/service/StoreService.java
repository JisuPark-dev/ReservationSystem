package zerobase.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.StoreDto;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.StoreRepository;
import zerobase.reservation.type.MemberStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static zerobase.reservation.dto.StoreDto.toStoreEntity;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public Store join(StoreDto storeDto) {
        Member member = memberRepository.findById(storeDto.getMemberId())
                .orElseThrow(() -> new NoSuchElementException("Member not found with id: " + storeDto.getMemberId()));

        return storeRepository.save(toStoreEntity(member, storeDto));
    }

    @Transactional(readOnly = true)
    public Store findById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Store found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Store> findAll() {
        return storeRepository.findAll();
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
