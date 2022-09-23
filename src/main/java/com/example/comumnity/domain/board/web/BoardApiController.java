package com.example.comumnity.domain.board.web;

import com.example.comumnity.domain.board.service.BoardService;
import com.example.comumnity.domain.board.web.dto.req.BoardDeleteReqDto;
import com.example.comumnity.domain.board.web.dto.req.BoardSaveReqDto;
import com.example.comumnity.domain.board.web.dto.req.BoardUpdateReq;
import com.example.comumnity.domain.board.web.dto.res.BoardCommentResDto;
import com.example.comumnity.domain.board.web.dto.res.BoardResDto;
import com.example.comumnity.domain.board.web.dto.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardApiController {

    private final BoardService boardService;

    // C
    @PostMapping("/save")
    public ResponseEntity<Long> boardSave(@RequestBody BoardSaveReqDto reqDto){
        return ResponseEntity.ok().body(boardService.boardSave(reqDto));
    }

    @PostMapping("/file-upload")
    public Long create(FileVo fileVo) throws Exception{
        BoardSaveReqDto reqDto = BoardSaveReqDto
                .builder()
                .contents(fileVo.getContents())
                .title(fileVo.getTitle())
                .userId(fileVo.getUserId())
                .password(fileVo.getPassword())
                .build();
        return boardService.create(reqDto, fileVo.getFiles());
    }

    // R
    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardResDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(boardService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardResDto>> findAll(){
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/comment/{boardId}")
    public ResponseEntity<BoardCommentResDto> commentResponse(@PathVariable Long boardId, @RequestParam Long commentId){
        return ResponseEntity.ok(boardService.boardResponse(boardId, commentId));
    }

    // U
    @PatchMapping("/update/{id}")
    public ResponseEntity<Long> boardUpdate(@PathVariable Long id, @RequestBody BoardUpdateReq req){
        return ResponseEntity.ok(boardService.boardUpdate(id, req));
    }

    @PatchMapping("update-id-pwd/{id}")
    public ResponseEntity<Long> boardUpdateWithIdAndPassword(@PathVariable Long id, @RequestBody BoardUpdateReq req){
        return ResponseEntity.ok(boardService.boardUpdateWithIdAndPassword(id, req));
    }

    // D
    @DeleteMapping("/delete/{id}")
    public void boardDelete(@PathVariable Long id){
        boardService.boardDelete(id);
    }

    @DeleteMapping("/delete-password/{id}")
    public void boardDeleteWithPwd(@PathVariable Long id, @RequestBody BoardDeleteReqDto reqDto){

        boardService.boardDeleteWithPwd(id, reqDto.getPassword());
    }
}
