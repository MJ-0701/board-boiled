package com.example.comumnity.domain.board.web;

import com.example.comumnity.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/post-file")
    public String viewPost(){
        return "post-file";
    }
}
