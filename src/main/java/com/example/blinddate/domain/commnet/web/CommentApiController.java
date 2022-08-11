package com.example.blinddate.domain.commnet.web;

import com.example.blinddate.domain.commnet.service.CommentService;
import com.example.blinddate.domain.commnet.web.dto.req.CommentSaveReqDto;
import com.example.blinddate.domain.commnet.web.dto.req.CommentUpdateReqDto;
import com.example.blinddate.domain.commnet.web.dto.req.ReCommentSaveReqDto;
import com.example.blinddate.domain.commnet.web.dto.req.ReCommentUpdateDto;
import com.example.blinddate.domain.commnet.web.dto.res.ReCommentList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/save/{boardId}")
    public ResponseEntity<Long> commentSave(@PathVariable Long boardId, @RequestBody CommentSaveReqDto reqDto){ // 댓글
        return ResponseEntity.ok(commentService.commentSave(boardId, reqDto));
    }

//    @PostMapping("/re-comment/{boardId}/{commentId}")
//    public ResponseEntity<Long> reCommentSave(
//            @PathVariable Long boardId,
//            @PathVariable Long commentId,
//            @RequestBody CommentSaveReqDto reqDto
//    ){ // 대댓글
//        return ResponseEntity.ok(commentService.saveReComment(boardId, commentId, reqDto));
//    }

    @PostMapping("/re-comment/{commentId}")
    public ResponseEntity<Long> reComment(
            @PathVariable Long commentId,
            @RequestBody ReCommentSaveReqDto reqDto
        ){ // 대댓글 -> 대댓글 엔티티 분리
        return ResponseEntity.ok(commentService.saveReComment2(commentId, reqDto));
        }

    @GetMapping("/re-comment/list/{id}")
    public ResponseEntity<List<ReCommentList>> reCommentList(@PathVariable Long id){
        return ResponseEntity.ok(commentService.reCommentLists(id));
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<Long> commentUpdate(@PathVariable Long id, @RequestBody CommentUpdateReqDto reqDto){
        return ResponseEntity.ok(commentService.commentUpdate(id, reqDto));
    }

    @PatchMapping("/re-comment/update/{id}")
    public ResponseEntity<Long> reCommentUpdate(@PathVariable Long id, @RequestBody ReCommentUpdateDto reqDto){
        return ResponseEntity.ok(commentService.reCommentUpdate(id, reqDto));
    }

    @DeleteMapping("/delete/{id}")
    public void commentDelete(@PathVariable Long id){
        commentService.commentDelete(id);
    }

    @DeleteMapping("/re-comment/{id}")
    public void reCommentDelete(@PathVariable Long id){
        commentService.reCommentDelete(id);
    }

}
