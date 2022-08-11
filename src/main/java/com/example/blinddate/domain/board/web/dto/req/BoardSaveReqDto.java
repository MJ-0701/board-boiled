package com.example.blinddate.domain.board.web.dto.req;

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
}
