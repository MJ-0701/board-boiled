
package com.example.comumnity.domain.board.service;

import com.example.comumnity.domain.board.domain.Board;
import com.example.comumnity.domain.board.domain.Tag;
import com.example.comumnity.domain.board.domain.repository.BoardRepository;
import com.example.comumnity.domain.board.web.dto.req.BoardSaveReqDto;
import com.example.comumnity.domain.board.web.dto.req.BoardUpdateReq;
import com.example.comumnity.domain.board.web.dto.res.BoardListDto;
import com.example.comumnity.domain.board.web.dto.res.BoardResDto;
import com.example.comumnity.domain.commnet.service.CommentService;
import com.example.comumnity.domain.file.domain.Files;
import com.example.comumnity.domain.file.domain.repository.FilesRepository;
import com.example.comumnity.domain.file.service.FileService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                .gender(reqDto.getGender())
                .likeCount(reqDto.getLikeCount())
                .tag(reqDto.getTag())
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
                .gender(reqDto.getGender())
                .tag(reqDto.getTag())
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
    public List<BoardListDto> findAll(){
        return boardRepository.findAll().stream().map(BoardListDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BoardListDto> tagList(Tag tag){
        if(tag == null){
            return boardRepository.findByTagIsNull(null).stream().map(BoardListDto::new).collect(Collectors.toList());
        }else {
            return boardRepository.findByTag(tag).stream().map(BoardListDto::new).collect(Collectors.toList());
        }


    }

    @Transactional(readOnly = true)
    public BoardListDto boardResponse(Long boardId, Long commentId){
        Board board = boardRepository.findById(boardId).orElseThrow();
        return new BoardListDto(board);
    }

    @Transactional(readOnly = true)
    public List<BoardListDto> searchBoard(String keyword){
        List<Board> searchList = boardRepository.findByTitleContaining(keyword);
        return searchList.stream().map(BoardListDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BoardListDto> searchLikeBoard(String keyword){
        List<Board> searchList = boardRepository.findByTitleLike(keyword);
        return searchList.stream().map(BoardListDto::new).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public Page<BoardListDto> boardPaging(Pageable pageable){
         return boardRepository.findAll(pageable).map(BoardListDto::new);
    }

    @Transactional(readOnly = true)
    public Page<BoardListDto> searchPaging(String keyword, Pageable pageable){
        return boardRepository.findByTitleLike(keyword, pageable).map(BoardListDto::new);
    }

    @Transactional(readOnly = true)
    public Page<BoardListDto> pagingTest(Pageable pageRequest) {
        return boardRepository.findAll(pageRequest).map(BoardListDto::new);
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

    @Transactional
    public void updateViewCount(Long id){
        boardRepository.updateViewCount(id);
    }

    @Transactional
    public int updatePlusLikeCount(Long id){
        return boardRepository.updatePlusLikeCount(id);
    }

    @Transactional
    public int updateMinusLikeCount(Long id){
        return boardRepository.updateMinusLikeCount(id);
    }

    @Transactional
    public void updateDeclaration(Long id){
        Board board = boardRepository.findById(id).orElseThrow();
        board.declarationUpdate(true);
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
