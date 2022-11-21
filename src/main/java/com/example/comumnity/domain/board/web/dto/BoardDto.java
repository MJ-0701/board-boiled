package com.example.comumnity.domain.board.web.dto;

import com.example.comumnity.domain.board.domain.Tag;
import com.example.comumnity.domain.user.domain.Gender;
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
}
