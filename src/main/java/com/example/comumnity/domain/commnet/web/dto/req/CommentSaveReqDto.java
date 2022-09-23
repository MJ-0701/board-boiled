package com.example.comumnity.domain.commnet.web.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentSaveReqDto {

    private String contents;

    @JsonProperty("nick_name")
    private String nickName;

    private String password;
}
