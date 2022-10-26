package com.example.comumnity.domain.notice.web.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NoticeSaveReqDto {

    private String title;

    private String contents;

}
