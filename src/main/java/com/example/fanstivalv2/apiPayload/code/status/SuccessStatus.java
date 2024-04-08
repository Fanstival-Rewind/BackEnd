package com.example.fanstivalv2.apiPayload.code.status;

import com.example.fanstivalv2.apiPayload.code.BaseCode;
import com.example.fanstivalv2.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    // 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    // 유저 관련 응답
    USER_FOUND(HttpStatus.OK, "USER2001", "회원을 조회했습니다."),
    USER_TERMS_AGREED(HttpStatus.OK, "USER2002", "회원의 이용약관에 동의했습니다."),
    USER_UPDATE(HttpStatus.OK, "USER2003", "회원 정보를 업데이트 했습니다"),
    USER_DELETE(HttpStatus.OK, "USER2004", "회원 탈퇴 성공했습니다."),
    USER_JOIN(HttpStatus.OK, "USER2005", "회원 가입 성공했습니다."),
    USER_LOGIN(HttpStatus.OK, "USER2001", "로그인 성공했습니다."),

    //게시판 관련 응답
    BOARD_CREATED(HttpStatus.OK, "BOARD2001", "게시글을 작성했습니다."),
    BOARD_DELETED(HttpStatus.OK, "BOARD2002", "게시글을 삭제했습니다."),
    BOARD_UPDATED(HttpStatus.OK, "BOARD2003", "게시글을 수정했습니다."),
    BOARD_FOUND(HttpStatus.OK, "BOARD2004", "게시글을 조회했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
