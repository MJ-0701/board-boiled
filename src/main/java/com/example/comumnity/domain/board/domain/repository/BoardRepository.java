package com.example.comumnity.domain.board.domain.repository;

import com.example.comumnity.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByIdAndPassword(Long id, String password);

    Board findByUserIdAndPasswordAndId(String userId, String password, Long id);



}
