<<<<<<< HEAD:src/main/java/com/example/blinddate/domain/commnet/service/CommentService.java
package com.example.blinddate.domain.commnet.service;

import com.example.blinddate.domain.board.domain.Board;
import com.example.blinddate.domain.board.domain.repository.BoardRepository;
import com.example.blinddate.domain.commnet.domain.Comment;
import com.example.blinddate.domain.commnet.domain.ReComment;
import com.example.blinddate.domain.commnet.domain.repository.CommentRepository;
import com.example.blinddate.domain.commnet.domain.repository.ReCommentRepository;
import com.example.blinddate.domain.commnet.web.dto.req.CommentSaveReqDto;
import com.example.blinddate.domain.commnet.web.dto.req.CommentUpdateReqDto;
import com.example.blinddate.domain.commnet.web.dto.req.ReCommentSaveReqDto;
import com.example.blinddate.domain.commnet.web.dto.req.ReCommentUpdateDto;
import com.example.blinddate.domain.commnet.web.dto.res.CommentList;
import com.example.blinddate.domain.commnet.web.dto.res.ReCommentList;
import com.example.blinddate.domain.notice.domain.repository.NoticeRepository;
=======
package com.example.comumnity.domain.commnet.service;

import com.example.comumnity.domain.board.domain.repository.BoardRepository;
import com.example.comumnity.domain.commnet.domain.Comment;
import com.example.comumnity.domain.commnet.domain.ReComment;
import com.example.comumnity.domain.commnet.domain.repository.CommentRepository;
import com.example.comumnity.domain.commnet.domain.repository.ReCommentRepository;
import com.example.comumnity.domain.commnet.web.dto.req.CommentSaveReqDto;
import com.example.comumnity.domain.commnet.web.dto.req.CommentUpdateReqDto;
import com.example.comumnity.domain.commnet.web.dto.req.ReCommentSaveReqDto;
import com.example.comumnity.domain.commnet.web.dto.req.ReCommentUpdateDto;
import com.example.comumnity.domain.commnet.web.dto.res.CommentList;
import com.example.comumnity.domain.commnet.web.dto.res.ReCommentList;
>>>>>>> master:src/main/java/com/example/comumnity/domain/commnet/service/CommentService.java
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final ReCommentRepository reCommentRepository;

    private final NoticeRepository noticeRepository;

    @Transactional
    public Long commentSave(Long boardId, CommentSaveReqDto reqDto){ // todo :: 일반 게시글과 공지사항 댓글 구분 해야됨.
        Board board = boardRepository.findById(boardId).orElseThrow();
        Comment comment = Comment
                .builder()
                .contents(reqDto.getContents())
                .nickName(reqDto.getNickName())
                .password(reqDto.getPassword())
                .board(board)
                .build();
        commentRepository.save(comment);
        return comment.getId();
    }

//    @Transactional
//    public Long saveReComment(Long boardId, Long parentId, CommentSaveReqDto reqDto){ // 게시글의 index 번호 + 댓글의 index 번호로 대댓글 작성 -> 엔티티 분리 x
//        Comment comment = Comment
//                .builder()
//                .contents(reqDto.getContents())
//                .nickName(reqDto.getNickName())
//                .password(reqDto.getPassword())
//                .board(boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")))
//                .parent(commentRepository.findById(parentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다.")))
//                .build();
//
//        commentRepository.save(comment);
//        return comment.getId();
//
//    }

    @Transactional
    public Long saveReComment2(Long commentId, ReCommentSaveReqDto reqDto){ // 대댓글 엔티티 분리
        ReComment reComment = ReComment
                .builder()
                .contents(reqDto.getContents())
                .nickName(reqDto.getNickName())
                .password(reqDto.getPassword())
                .comment(commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다.")))
                .build();
        reCommentRepository.save(reComment);
        return reComment.getId();
    }

    @Transactional(readOnly = true)
    public List<ReCommentList> reCommentLists(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow();
        List<ReComment> reCommentList = reCommentRepository.findByComment(comment);

        return reCommentList.stream().map(ReCommentList::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentList commentList(Long id){ // 댓글 + 대댓글
        Comment comment = commentRepository.findById(id).orElseThrow();
        return new CommentList(comment);
    }

    @Transactional
    public Long commentUpdate(Long id, CommentUpdateReqDto reqDto){
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.update(reqDto.getContents());
        commentRepository.save(comment);
        return comment.getId();

    }

    @Transactional
    public Long reCommentUpdate(Long id, ReCommentUpdateDto reqDto){
        ReComment reComment = reCommentRepository.findById(id).orElseThrow();
        reComment.update(reqDto.getContents());
        reCommentRepository.save(reComment);
        return reComment.getId();
    }

    @Transactional
    public void commentDelete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(comment);
    }

    @Transactional
    public void reCommentDelete(Long id){
        ReComment reComment = reCommentRepository.findById(id).orElseThrow();
        reCommentRepository.delete(reComment);
    }


}
