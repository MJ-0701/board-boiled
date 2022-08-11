package com.example.blinddate.domain.commnet.web.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReCommentUpdateDto {

    private String contents;

    @Builder
    public ReCommentUpdateDto(String contents){
        this.contents = contents;
    }
}
