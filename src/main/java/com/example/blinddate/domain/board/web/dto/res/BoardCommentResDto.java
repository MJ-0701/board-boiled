package com.example.blinddate.domain.board.web.dto.res;

import com.example.blinddate.domain.board.domain.Board;
import com.example.blinddate.domain.commnet.domain.Comment;
import com.example.blinddate.domain.commnet.web.dto.res.CommentList;
import com.example.blinddate.domain.commnet.web.dto.res.ReCommentList;
import com.example.blinddate.domain.file.domain.Files;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardCommentResDto {

    private final String title;

    private final String contents;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("file_path")
    private List<String> filePath;
    private final CommentList comment;





    public BoardCommentResDto(Board entity, CommentList comments){
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.userId = entity.getUserId();
        this.filePath = entity.getFiles().stream().map(Files::getFilePath).collect(Collectors.toList());
        this.comment = comments;

    }
}
