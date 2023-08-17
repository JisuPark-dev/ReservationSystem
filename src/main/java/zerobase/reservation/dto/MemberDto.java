package zerobase.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import zerobase.reservation.dao.Member;
import zerobase.reservation.type.MemberStatus;

@Getter
@Setter
@Component
public class MemberDto {
    private String username;
    private String password;
    private MemberStatus memberStatus;

    public static Member toMemberEntity(MemberDto memberDto) {
        return new Member().builder()
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .memberStatus(memberDto.getMemberStatus())
                .build();
    }
}
