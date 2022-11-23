package com.example.comumnity.domain.board.domain;


import com.example.comumnity.domain.user.domain.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import com.example.comumnity.domain.commnet.domain.Comment;
import com.example.comumnity.domain.file.domain.Files;
import com.example.comumnity.global.entity.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    @Lob
    private String contents;

    private String userId;

    private String password;

    private Gender gender;

    private Tag tag;

    private int viewCount = 0;

    private int likeCount = 0;

    private boolean declaration = false;

    @OneToMany(mappedBy = "board", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Files> files = new ArrayList<>();

    @Builder
    public Board(String title, String contents, String userId, String password, Gender gender, int likeCount, Tag tag, boolean declaration){ // 클래스에 @Builder 를 먹여서 처리하면 현재 서비스 로직에서 file을 먼저 저장하므로 board id 값이 null -> nullpoint
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.password = password;
        this.gender = gender;
        this.likeCount = likeCount;
        this.tag = tag;
        this.declaration = declaration;
    }

    @Builder
    public Board(String title, String userId) {
        this.title = title;
        this.userId = userId;
    }

    public void addComment(Comment comment){
        commentList.add(comment);
    }

    // Board에서 파일 처리 위함.
    public void addFiles(Files files){
        this.files.add(files);

        // 게시글에 파일이 저장되어있지 않은 경우
        if(files.getBoard() != this){
            // 파일 저장
            files.setBoard(this);
        }
    }

    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public void likeCountUpdate(int likeCount){
        this.likeCount = likeCount;
    }

    public void viewCountUpdate(int viewCount){
        this.viewCount = viewCount;
    }

    public void declarationUpdate(boolean declaration){
        this.declaration = declaration;
    }



}
