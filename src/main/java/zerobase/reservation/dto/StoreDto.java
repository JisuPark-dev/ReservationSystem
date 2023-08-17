package zerobase.reservation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Store;

import java.time.LocalDateTime;

@Getter @Setter
public class StoreDto {
    private Long memberId;
    private Member member;
    private String name;
    private String location;
    private String description;
    private LocalDateTime updatedAt;
    
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
