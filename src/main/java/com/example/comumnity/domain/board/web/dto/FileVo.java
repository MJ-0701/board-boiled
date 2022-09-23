package com.example.comumnity.domain.board.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FileVo {

    private String contents;

    private String title;

    @JsonProperty("user_id")
    private String userId;

    private String password;

    private List<MultipartFile> files;
}
