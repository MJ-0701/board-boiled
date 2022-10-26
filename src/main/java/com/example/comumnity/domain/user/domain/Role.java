package com.example.comumnity.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "비회원"), USER("ROLE_USER", "사용자"), ADMIN("ROLE_ADMIN", "관리자");
//    USER

    private final String key;

    private final String value;
}
