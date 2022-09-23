package com.example.comumnity.domain.commnet.web.dto.res;

import com.example.comumnity.domain.commnet.domain.ReComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReCommentList {

    private final String contents;

    @JsonProperty("nick_name")
    private String nickName;

    public ReCommentList(ReComment entity){
        this.contents = entity.getContents();
        this.nickName = entity.getNickName();
    }
}
