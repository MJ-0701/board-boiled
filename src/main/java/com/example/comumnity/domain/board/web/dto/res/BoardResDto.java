package com.example.comumnity.domain.board.web.dto.res;

import com.example.comumnity.domain.board.domain.Board;
import com.example.comumnity.domain.board.domain.Tag;
import com.example.comumnity.domain.commnet.domain.Comment;
import com.example.comumnity.domain.commnet.web.dto.res.CommentList;
import com.example.comumnity.domain.file.domain.Files;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResDto {

    private final String title;

    private final String contents;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("file_path")
    private List<String> filePath;

    private final List<CommentList> comment;

    private final int viewCount;

    private final Tag tag;

    private boolean declaration;




    public BoardResDto(Board entity){
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.userId = entity.getUserId();
        this.filePath = entity.getFiles().stream().map(Files::getFilePath).collect(Collectors.toList());
        this.comment = entity.getCommentList().stream().map(CommentList::new).collect(Collectors.toList());
        this.viewCount = entity.getViewCount();
        this.tag = entity.getTag();
        this.declaration = entity.isDeclaration();

    }
}
