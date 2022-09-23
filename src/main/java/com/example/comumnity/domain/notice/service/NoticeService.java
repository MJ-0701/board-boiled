package com.example.blinddate.domain.notice.service;

import com.example.blinddate.domain.notice.domain.Notice;
import com.example.blinddate.domain.notice.domain.repository.NoticeRepository;
import com.example.blinddate.domain.notice.web.dto.req.NoticeSaveReqDto;
import com.example.blinddate.domain.notice.web.dto.res.NoticeResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    // C
    @Transactional
    public Long noticeSave(NoticeSaveReqDto reqDto){
        Notice notice = Notice
                .builder()
                .title(reqDto.getTitle())
                .contents(reqDto.getContents())
                .build();
        noticeRepository.save(notice);
        return notice.getId();
    }

    // R
    @Transactional(readOnly = true)
    public NoticeResDto noticeResponse(Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow();
        return new NoticeResDto(notice);
    }



}
