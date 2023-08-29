package zerobase.reservation.member.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dto.MemberDto;
import zerobase.reservation.repository.MemberRepository;
import zerobase.reservation.service.MemberService;
import zerobase.reservation.type.MemberStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void createMember(){
        //given
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername("member1");
        memberDto.setPassword("password1");
        memberDto.setMemberStatus(MemberStatus.PARTNER);

        Member partnerMember = Member.builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .memberStatus(memberDto.getMemberStatus())
                .build();

        when(memberRepository.save(any(Member.class))).thenReturn(partnerMember);
        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        //when
        Member savedMember = memberService.join(memberDto);

        //then
        verify()

        Assertions.assertThat(savedMember.getUsername()).isEqualTo(memberDto.getUsername());
        Assertions.assertThat(savedMember.getPassword()).isEqualTo(memberDto.getPassword());
        Assertions.assertThat(savedMember.getMemberStatus()).isEqualTo(memberDto.getMemberStatus());
    }

}
