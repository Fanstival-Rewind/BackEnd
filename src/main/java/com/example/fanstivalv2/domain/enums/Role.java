package com.example.fanstivalv2.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    SINGER("ROLE_SINGER"),
    FAN("ROLE_FAN"),
    BOTH("ROLE_BOTH"),//처음 로그인 시에는 BOTH로 지정
    GUEST("ROLE_GUEST");//처음 회원 가입 할 때 쓰이는 용도

    private final String key;
}
