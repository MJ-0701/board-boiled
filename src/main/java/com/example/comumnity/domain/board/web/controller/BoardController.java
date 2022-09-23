<<<<<<< HEAD:src/main/java/com/example/comumnity/domain/board/web/controller/BoardController.java
package com.example.blinddate.domain.board.web.controller;
=======
package com.example.comumnity.domain.board.web;
>>>>>>> master:src/main/java/com/example/comumnity/domain/board/web/BoardController.java

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
