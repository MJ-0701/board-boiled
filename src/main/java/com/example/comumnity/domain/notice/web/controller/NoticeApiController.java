package com.example.blinddate.domain.notice.web.controller;

import com.example.blinddate.domain.notice.service.NoticeService;
import com.example.blinddate.domain.notice.web.dto.req.NoticeSaveReqDto;
import com.example.blinddate.domain.notice.web.dto.res.NoticeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notice")
@RestController
@RequiredArgsConstructor
public class NoticeApiController {

    private final  NoticeService noticeService;

    // C
    @PostMapping("/save")
    public ResponseEntity<Long> noticeSave(@RequestBody NoticeSaveReqDto reqDto){
        return ResponseEntity.ok(noticeService.noticeSave(reqDto));
    }

    // R
    @GetMapping("/detail/{id}")
    public ResponseEntity<NoticeResDto> noticeDetail(@PathVariable Long id){
        return ResponseEntity.ok(noticeService.noticeResponse(id));
    }


}
