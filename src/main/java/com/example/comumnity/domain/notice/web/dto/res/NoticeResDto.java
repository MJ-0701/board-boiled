package com.example.comumnity.domain.notice.web.dto.res;

import com.example.comumnity.domain.notice.domain.Notice;
import lombok.Getter;

@Getter
public class NoticeResDto {

    private final String title;

    private final String contents;

    private final boolean declaration;

    private final int likeCount;

    public NoticeResDto(Notice entity){
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.declaration = entity.isDeclaration();
        this.likeCount = entity.getLikeCount();
    }
}
