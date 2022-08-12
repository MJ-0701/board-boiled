package com.example.blinddate.domain.board.web.dto.res;

import com.example.blinddate.domain.board.domain.Board;
import com.example.blinddate.domain.commnet.web.dto.res.CommentList;
import com.example.blinddate.domain.file.domain.Files;
import com.example.blinddate.domain.user.domain.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardListDto {

    private final Long id;
    private final String title;

    private final String contents;

    @JsonProperty("user_id")
    private String userId;

    private final Gender gender;

    private final int viewCount;







    public BoardListDto(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.userId = entity.getUserId();
        this.gender = entity.getGender();
        this.viewCount = entity.getViewCount();

    }
}
