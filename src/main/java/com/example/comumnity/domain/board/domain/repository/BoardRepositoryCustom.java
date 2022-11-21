package com.example.comumnity.domain.board.domain.repository;

import com.example.comumnity.domain.board.web.dto.BoardDto;
import com.example.comumnity.domain.board.web.dto.search.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    List<BoardDto> search(BoardSearchCondition condition);

    Page<BoardDto> searchPageSimple(BoardSearchCondition condition, Pageable pageable);

    Page<BoardDto> searchPageComplex(BoardSearchCondition condition, Pageable pageable);

}
