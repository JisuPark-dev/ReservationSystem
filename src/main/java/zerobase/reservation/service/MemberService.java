package zerobase.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dto.MemberDto;
import zerobase.reservation.repository.MemberRepository;

import static zerobase.reservation.dto.MemberDto.toMemberEntity;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(MemberDto memberDto) {
        return memberRepository.save(toMemberEntity(memberDto));
    }


}
