package zerobase.reservation.member.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dao.Store;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.repository.StoreRepository;
import zerobase.reservation.service.StoreService;
import zerobase.reservation.type.MemberStatus;

import java.time.LocalDateTime;

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

    @Test
    void createStore(){
        //given
        Member partnerMember = Member.builder()
                .username("testUser")
                .password("testPW")
                .memberStatus(MemberStatus.PARTNER)
                .build();
        Store store = Store.builder()
                .name("Store Name")
                .description("Store Description")
                .location("Seoul")
                .x(123.123)
                .y(321.321)
                .createdAt(LocalDateTime.now())
                .build();

        when(storeRepository.save(any(Store.class))).thenReturn(store);
        //when


        //then

    }
}
