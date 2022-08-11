package com.example.blinddate.domain.board.domain.repository;

import com.example.blinddate.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByIdAndPassword(Long id, String password);

    Board findByUserIdAndPasswordAndId(String userId, String password, Long id);



}
