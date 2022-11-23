package com.example.comumnity.domain.board.web.dto;

import com.example.comumnity.domain.board.domain.Tag;
import com.example.comumnity.domain.user.domain.Gender;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private String title;

    @Lob
    private String contents;

    private String userId;

    private String password;

    private Gender gender;

    private Tag tag;

    private int viewCount = 0;

    private int likeCount = 0;

    private boolean declaration = false;

    @QueryProjection
    public BoardDto(String title, String userId, String password, Gender gender, Tag tag, int viewCount, int likeCount, boolean declaration ) {
        this.title = title;
        this.userId = userId;
        this.password = password;
        this.gender = gender;
        this.tag = tag;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.declaration = declaration;
    }
}
