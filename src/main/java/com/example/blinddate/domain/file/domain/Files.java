package com.example.blinddate.domain.file.domain;

import com.example.blinddate.domain.board.domain.Board;
import com.example.blinddate.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Files extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "files_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String originalFileName;

    private String filePath;

    private Long fileSize;

    @Builder
    public Files(String originalFileName, String filePath, Long fileSize){
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // board 정보 저장
    public void setBoard(Board board){ // 연관관계 매핑
        this.board = board;

        // 게시글에 현재 파일이 존재하지 않는다면.
        if(!board.getFiles().contains(this)){
            board.getFiles().add(this);
        }
    }
}
