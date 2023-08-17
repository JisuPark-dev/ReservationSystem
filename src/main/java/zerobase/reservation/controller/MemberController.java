package zerobase.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.reservation.dao.Member;
import zerobase.reservation.dto.MemberDto;
import zerobase.reservation.service.MemberService;

import javax.transaction.Transactional;
import java.util.List;

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
