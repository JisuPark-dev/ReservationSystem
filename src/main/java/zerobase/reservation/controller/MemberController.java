package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dto.MemberDto;
import zerobase.reservation.service.MemberService;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@Transactional
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Member> create(@RequestBody MemberDto memberDto) {
        Member member = memberService.join(memberDto);
        return ResponseEntity.ok(member);
    }
}
