package com.example.comumnity.domain.board.web.dto.res;

import com.example.comumnity.domain.board.domain.Board;
import com.example.comumnity.domain.board.domain.Tag;
import com.example.comumnity.domain.commnet.web.dto.res.CommentList;
import com.example.comumnity.domain.user.domain.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardCommentResDto {

    private final Long id;
    private final String title;

    private final String contents;

    @JsonProperty("user_id")
    private String userId;

    private final Gender gender;

    private final int viewCount;

    private final Tag tag;


    public BoardCommentResDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.userId = entity.getUserId();
        this.gender = entity.getGender();
        this.viewCount = entity.getViewCount();
        this.tag = entity.getTag();
    }
}