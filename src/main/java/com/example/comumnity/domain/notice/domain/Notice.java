package com.example.comumnity.domain.notice.domain;


import com.example.comumnity.domain.commnet.domain.Comment;
import com.example.comumnity.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Notice extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String title;

    private String contents;

    @OneToMany(mappedBy = "notice", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private final List<Comment> commentList = new ArrayList<>();

    private int likeCount = 0;

    private boolean declaration = false;

    @Builder
    public Notice(String title, String contents, int likeCount, boolean declaration){
        this.title = title;
        this.contents = contents;
        this.likeCount = likeCount;
        this.declaration = declaration;
    }

    public void plusLikeCount(int likeCount){
        this.likeCount = likeCount;
    }

    public void declarationUpdate(boolean declaration){
        this.declaration = declaration;
    }

}
