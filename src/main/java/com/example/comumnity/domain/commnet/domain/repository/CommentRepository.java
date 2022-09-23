package com.example.comumnity.domain.commnet.domain.repository;

import com.example.comumnity.domain.commnet.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
