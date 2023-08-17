package zerobase.reservation.dto;

import lombok.*;
import org.springframework.stereotype.Component;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Store;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.type.MemberStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class StoreDto {
    private Member member;
    private String name;
    private String location;
    private String description;
    
    public static Store toStoreEntity(Member member, StoreDto storeDto) {

        return new Store().builder()
                .member(member)
                .name(storeDto.getName())
                .location(storeDto.getLocation())
                .description(storeDto.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
