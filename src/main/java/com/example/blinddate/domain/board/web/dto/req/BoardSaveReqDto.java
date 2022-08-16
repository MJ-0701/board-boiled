package com.example.blinddate.domain.board.web.dto.req;

import com.example.blinddate.domain.board.domain.Tag;
import com.example.blinddate.domain.user.domain.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BoardSaveReqDto {

    private String title;

    private String contents;

    @JsonProperty("user_id")
    private String userId;

    private String password;

    private Gender gender;

    @JsonProperty("like_count")
    private int likeCount;

    private Tag tag;

}
