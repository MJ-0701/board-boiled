package com.example.blinddate.domain.board.web;

import com.example.blinddate.domain.board.domain.Board;
import com.example.blinddate.domain.board.service.BoardService;
import com.example.blinddate.domain.board.web.dto.req.BoardDeleteReqDto;
import com.example.blinddate.domain.board.web.dto.req.BoardSaveReqDto;
import com.example.blinddate.domain.board.web.dto.req.BoardUpdateReq;
import com.example.blinddate.domain.board.web.dto.res.BoardListDto;
import com.example.blinddate.domain.board.web.dto.res.BoardResDto;
import com.example.blinddate.domain.board.web.dto.FileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
                .gender(fileVo.getGender())
                .build();
        return boardService.create(reqDto, fileVo.getFiles());
    }

    // R
    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardResDto> findById(@PathVariable Long id){
        boardService.updateViewCount(id);
        return ResponseEntity.ok(boardService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardListDto>> findAll(){
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/comment/{boardId}")
    public ResponseEntity<BoardListDto> commentResponse(@PathVariable Long boardId, @RequestParam Long commentId){
        return ResponseEntity.ok(boardService.boardResponse(boardId, commentId));
    }

    @GetMapping("/search/contain")
    public ResponseEntity<List<BoardListDto>> searchList(@RequestParam String keyword){
        return ResponseEntity.ok(boardService.searchBoard(keyword));
    }

    @GetMapping("/search/like")
    public ResponseEntity<List<BoardListDto>> searchLikeList(@RequestParam String keyword){
        return ResponseEntity.ok(boardService.searchLikeBoard(keyword));
    }

    @GetMapping("/paging")
    public ResponseEntity<List<BoardListDto>> boardPaging(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.ok(boardService.boardPaging(pageable).getContent());
    }

    @GetMapping("/search/paging")
    public ResponseEntity<List<BoardListDto>> searchPaging(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)Pageable pageable,
            @RequestParam String keyword){
        Page<BoardListDto> paging = boardService.searchPaging(keyword, pageable); // 요소 6개 기준
        System.out.println("페이지 수 :" + paging.getTotalPages()); // 2
        System.out.println("previous :" + pageable.previousOrFirst().getPageNumber()); // 0
        System.out.println("next :" + pageable.next().getPageNumber()); // 1
        System.out.println("hasNext :" + paging.hasNext()); // true
        System.out.println("hasPrev :" + paging.hasPrevious()); // false

        return ResponseEntity.ok(paging.getContent());
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
