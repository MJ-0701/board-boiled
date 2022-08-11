package com.example.blinddate.domain.commnet.web.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateReqDto {

    private String contents;

    @Builder
    public CommentUpdateReqDto(String contents){
        this.contents = contents;
    }
}
