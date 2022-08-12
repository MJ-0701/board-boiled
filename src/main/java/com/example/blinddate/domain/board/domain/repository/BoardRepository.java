package com.example.blinddate.domain.board.domain.repository;

import com.example.blinddate.domain.board.domain.Board;
import com.example.blinddate.domain.board.web.dto.res.BoardListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByIdAndPassword(Long id, String password);

    Board findByUserIdAndPasswordAndId(String userId, String password, Long id);

    @Modifying
    @Query("UPDATE Board B SET B.viewCount = B.viewCount + 1 WHERE B.id = :id")
    int updateViewCount(Long id);

    @Modifying
    @Query("UPDATE Board B SET B.likeCount = B.likeCount + 1 WHERE B.id = :id")
    int updateLikeCount(Long id);

    List<Board> findByTitleContaining(String keyword);


    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% ")
    List<Board> findByTitleLike(String keyword);

    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% ORDER BY b.id DESC ")
    Page<Board> findByTitleLike(String keyword, Pageable pageable);



}
