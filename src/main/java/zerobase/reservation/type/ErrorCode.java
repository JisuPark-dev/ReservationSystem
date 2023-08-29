package zerobase.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다"),
    INVALID_REQUEST("잘못된 요청입니다."),
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다."),
    INVALID_MEMBER_STATUS_ERROR("PARTNER로 등록된 회원만이 매장을 등록할 수 있습니다."),
    ALREADY_EXIST_STORE("이미 존재하는 매장 이름입니다."),
    STORE_NOT_FOUND("존재하지 않는 매장입니다."),
    RESERVATION_NOT_FOUND("존재하지 않는 예약입니다."),
    ALREADY_EXIST_REVIEW("이 예약은 이미 리뷰가 존재합니다."),
    RESERVATION_NOT_CONFIRMED("예약이 확정되고 나서 사용 가능합니다.");
     private final String description;
}
