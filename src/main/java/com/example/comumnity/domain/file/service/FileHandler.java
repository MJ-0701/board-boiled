package com.example.comumnity.domain.file.service;

import com.example.comumnity.domain.file.domain.Files;
import com.example.comumnity.domain.file.web.dto.req.FilesDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileHandler {

    public List<Files> fileInfo(
            List<MultipartFile> multipartFiles
    )throws Exception {
        // 반환할 파일 리스트
        List<Files> fileList = new ArrayList<>();

        // 전달되어 온 파일이 존재할 경우
        if(!CollectionUtils.isEmpty(multipartFiles)) {
            // 파일명을 업로드 한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 File.separator 사용
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            // 파일을 저장할 세부 경로 지정
            String path = "files" + File.separator + current_date;
            File file = new File(path);

            // 디렉터리가 존재하지 않을 경우
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();

                // 디렉터리 생성에 실패했을 경우
                if(!wasSuccessful)
                    System.out.println("file: was not successful");
            }

            // 다중 파일 처리
            for(MultipartFile multipartFile : multipartFiles) {

                // 파일의 확장자 추출
                String originalFileExtension;
                String contentType = multipartFile.getContentType();
                String file_name = multipartFile.getOriginalFilename();

                // 확장자명이 존재하지 않을 경우 처리 x
                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                }
                else {  // 확장자가 jpeg, png, gif, pdf, mp4 인 파일들만 받아서 처리
                    if(contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if(contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else if(contentType.contains("image/gif"))
                        originalFileExtension = ".gif";
                    else if(contentType.contains("pdf"))
                        originalFileExtension = ".pdf";
                    else if (contentType.contains("mp4"))
                        originalFileExtension = ".mp4";

                    else  // 다른 확장자일 경우 처리 x
                        break;
                }

                // 파일명 중복 피하고자 나노초까지 얻어와 지정
                String new_file_name = System.nanoTime() + originalFileExtension;
                UUID uuid = UUID.randomUUID();
                String fileName = uuid + "_" + file_name;

                // 파일 DTO 생성
                FilesDto filesDto = FilesDto.builder()
                        .originFileName(multipartFile.getOriginalFilename())
//                        .filePath(path + File.separator + new_file_name)
                        .filePath(path + File.separator + fileName)
                        .fileSize(multipartFile.getSize())
                        .build();

                // 파일 DTO 이용하여 Photo 엔티티 생성
                Files files = new Files(
                        filesDto.getOriginFileName(),
                        filesDto.getFilePath(),
                        filesDto.getFileSize()
                );

                // 생성 후 리스트에 추가
                fileList.add(files);

                // 업로드 한 파일 데이터를 지정한 파일에 저장
//                file = new File(absolutePath + path + File.separator + new_file_name);
                file = new File(absolutePath + path + File.separator + fileName);
                multipartFile.transferTo(file);

                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
            }
        }

        return fileList;
    }
}
