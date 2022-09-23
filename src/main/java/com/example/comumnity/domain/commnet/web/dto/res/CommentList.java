package com.example.comumnity.domain.commnet.web.dto.res;

import com.example.comumnity.domain.commnet.domain.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentList {

    @JsonProperty("nick_name")
    private String nickName;

    private final String contents;

    @JsonProperty("re_comment_list")
    private final List<ReCommentList> reCommentLists;

    public CommentList(Comment entity){
        this.nickName = entity.getNickName();
        this.contents = entity.getContents();
        this.reCommentLists = entity.getReCommentList().stream().map(ReCommentList::new).collect(Collectors.toList());
    }
}
