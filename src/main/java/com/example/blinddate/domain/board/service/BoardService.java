package com.example.blinddate.domain.board.service;

import com.example.blinddate.domain.board.domain.Board;
import com.example.blinddate.domain.board.domain.repository.BoardRepository;
import com.example.blinddate.domain.board.web.dto.req.BoardSaveReqDto;
import com.example.blinddate.domain.board.web.dto.req.BoardUpdateReq;
import com.example.blinddate.domain.board.web.dto.res.BoardCommentResDto;
import com.example.blinddate.domain.board.web.dto.res.BoardResDto;
import com.example.blinddate.domain.commnet.domain.repository.CommentRepository;
import com.example.blinddate.domain.commnet.domain.repository.ReCommentRepository;
import com.example.blinddate.domain.commnet.service.CommentService;
import com.example.blinddate.domain.commnet.web.dto.res.CommentList;
import com.example.blinddate.domain.commnet.web.dto.res.ReCommentList;
import com.example.blinddate.domain.file.domain.Files;
import com.example.blinddate.domain.file.domain.repository.FilesRepository;
import com.example.blinddate.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileService fileService;
    private final FilesRepository filesRepository;

    private final CommentService commentService;

    // C
    @Transactional
    public Long boardSave(BoardSaveReqDto reqDto){
        Board board = Board
                .builder()
                .title(reqDto.getTitle())
                .contents(reqDto.getContents())
                .userId(reqDto.getUserId())
                .password(reqDto.getPassword())
                .build();
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public Long create(BoardSaveReqDto reqDto, List<MultipartFile> files) throws Exception{
        Board board = Board
                .builder()
                .title(reqDto.getTitle())
                .contents(reqDto.getContents())
                .userId(reqDto.getUserId())
                .password(reqDto.getPassword())
                .build();
        List<Files> filesList = fileService.fileInfo(files);
        // 파일이 존재할 때에만 처리
        if(!filesList.isEmpty()){
            for(Files file : filesList){
                // 파일 db에 저장
                board.addFiles(filesRepository.save(file));
            }
        }
        return boardRepository.save(board).getId();
    }

    // R
    @Transactional(readOnly = true)
    public BoardResDto findById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new BoardResDto(entity);
    }

    @Transactional(readOnly = true)
    public List<BoardResDto> findAll(){
        return boardRepository.findAll().stream().map(BoardResDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardCommentResDto boardResponse(Long boardId, Long commentId){
        Board board = boardRepository.findById(boardId).orElseThrow();
//        List<ReCommentList> reCommentLists = commentService.reCommentLists(commentId);
        CommentList commentList = commentService.commentList(commentId);
        return new BoardCommentResDto(board, commentList);
    }


    // U
    @Transactional
    public Long boardUpdate(Long id, BoardUpdateReq req){
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        board.update(req.getTitle(), req.getContents());
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public Long boardUpdateWithIdAndPassword(Long id, BoardUpdateReq req){
        Board board = boardRepository.findByUserIdAndPasswordAndId(req.getUserId(), req.getPassword(), id);
        board.update(req.getTitle(), req.getContents());
        boardRepository.save(board);
        return board.getId();
    }

    // D
    @Transactional
    public void boardDelete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        boardRepository.delete(board);
    }

    @Transactional
    public void boardDeleteWithPwd(Long id, String password){

        Board board = boardRepository.findByIdAndPassword(id, password);
        boardRepository.delete(board);
    }


}
