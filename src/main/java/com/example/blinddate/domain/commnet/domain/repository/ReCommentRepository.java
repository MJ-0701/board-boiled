package com.example.blinddate.domain.commnet.domain.repository;

import com.example.blinddate.domain.commnet.domain.Comment;
import com.example.blinddate.domain.commnet.domain.ReComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReCommentRepository extends JpaRepository<ReComment, Long> {

    List<ReComment> findByComment(Comment comment);
}
