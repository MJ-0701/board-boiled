package com.example.comumnity.domain.board.domain.repository;
import com.example.comumnity.domain.board.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.comumnity.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom, QuerydslPredicateExecutor<Board> {

    Board findByIdAndPassword(Long id, String password);

    Board findByUserIdAndPasswordAndId(String userId, String password, Long id);

    @Modifying
    @Query("UPDATE Board B SET B.viewCount = B.viewCount + 1 WHERE B.id = :id")
    void updateViewCount(Long id);

    @Modifying
    @Query("UPDATE Board B SET B.likeCount = B.likeCount + 1 WHERE B.id = :id")
    int updatePlusLikeCount(Long id);

    @Modifying
    @Query("UPDATE Board B SET B.likeCount = B.likeCount - 1 WHERE B.id = :id")
    int updateMinusLikeCount(Long id);

    List<Board> findByTitleContaining(String keyword);


    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% ")
    List<Board> findByTitleLike(String keyword);

    @Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% ORDER BY b.id DESC ")
    Page<Board> findByTitleLike(String keyword, Pageable pageable);

    List<Board> findByTag(Tag tag);

    @Query("SELECT b FROM Board b WHERE b.tag IS NULL ")
    List<Board> findByTagIsNull(Tag tag);

}
