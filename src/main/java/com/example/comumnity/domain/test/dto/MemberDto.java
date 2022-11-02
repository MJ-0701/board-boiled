package com.example.comumnity.domain.test.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberDto {

    private String userName;

    private int age;

    public MemberDto() {
    }

    @QueryProjection // -> 어노테이션 추가후 그레이들 -> Tasks -> other -> compileQuerydsl 로 Q파일 생성
    public MemberDto(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}
