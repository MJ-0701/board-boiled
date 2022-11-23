package com.example.comumnity.domain.board.web.dto.search;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardSearchCondition {

    private String userId;

    private String title;



}
