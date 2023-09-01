package zerobase.reservation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Store;
import zerobase.reservation.dto.StoreDto;
import zerobase.reservation.exception.ReservationException;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.StoreRepository;
import zerobase.reservation.type.MemberStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;
    private final Long MEMBER_ID = 1L;
    private static final String STORE_NAME = "Test Store";

    @Test
    void join_WhenMemberNotFound_ThrowsException(){
        //given
        StoreDto storeDto = new StoreDto();
        storeDto.setMemberId(MEMBER_ID);

        //when
        when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(ReservationException.class, () -> storeService.join(storeDto));
    }
    
    @Test
    void join_WhenStoreAlreadyExist_ThrowException(){
        //given
        StoreDto storeDto = new StoreDto();
        storeDto.setMemberId(MEMBER_ID);
        storeDto.setName("ExistingStore");

        //when
        when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(new Member()));
        when(storeRepository.existsByName(storeDto.getName())).thenReturn(true);
        
        //then
        assertThrows(ReservationException.class, () -> storeService.join(storeDto));
    }
    
    @Test
    void join_WhenMemberIsNotPartner_ThrowException(){
        //given
        StoreDto storeDto = new StoreDto();
        storeDto.setMemberId(MEMBER_ID);

        Member member = new Member();
        member.setMemberStatus(MemberStatus.CLIENT);

        //when
        when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(member));
        
        //then
        assertThrows(ReservationException.class, () -> storeService.join(storeDto));
    }

    @Test
    void join_SuccessfullyCreatesStore() {
        //given
        StoreDto storeDto = StoreDto.builder()
                .memberId(MEMBER_ID)
                .name(STORE_NAME)
                .build();

        Member member = Member.builder()
                .id(MEMBER_ID)
                .memberStatus(MemberStatus.PARTNER)
                .build();

        Store store = Store.builder()
                .member(member)
                .name(storeDto.getName())
                .build();

        when(memberRepository.findById(MEMBER_ID)).thenReturn(Optional.of(member));
        when(storeRepository.existsByName(STORE_NAME)).thenReturn(false);
        when(storeRepository.save(any(Store.class))).thenReturn(store);

        //when
        StoreDto savedStoreDto = storeService.join(storeDto);

        //then
        assertThat(savedStoreDto.getName()).isEqualTo(STORE_NAME);
    }

}
