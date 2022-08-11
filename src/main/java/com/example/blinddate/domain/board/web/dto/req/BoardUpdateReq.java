package com.example.blinddate.domain.board.web.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateReq {

    @JsonProperty("user_id")
    private String userId;

    private String password;

    private String contents;

    private String title;
}
