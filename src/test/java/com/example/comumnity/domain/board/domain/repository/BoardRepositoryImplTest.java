package com.example.comumnity.domain.board.domain.repository;

import com.example.comumnity.domain.board.domain.Board;
import com.example.comumnity.domain.board.web.dto.BoardDto;
import com.example.comumnity.domain.board.web.dto.search.BoardSearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    public void before(){

        Board boardA = new Board("title1", "user1");
        Board boardB = new Board("title2", "user2");

        boardRepository.save(boardA);
        boardRepository.save(boardB);
    }



    @Test
    void test() {
        BoardSearchCondition condition = BoardSearchCondition
                .builder()
                .title("title1")
                .userId("user1")
                .build();
        List<BoardDto> search = boardRepository.search(condition);

//        assertThat(search.size()).isEqualTo(2); // 실패
        assertThat(search.size()).isEqualTo(1); // 성공
    }
}