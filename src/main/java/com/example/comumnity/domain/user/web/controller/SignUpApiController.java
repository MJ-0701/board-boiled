package com.example.comumnity.domain.user.web.controller;


import com.example.comumnity.domain.user.domain.User;
import com.example.comumnity.domain.user.service.UserService;
import com.example.comumnity.domain.user.web.dto.req.UserSignUpReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/sign-up")
public class SignUpApiController {

    private final UserService userService;

    @PostMapping("/user/save")
    public ResponseEntity<User> userSave(
            @RequestBody @Valid UserSignUpReqDto reqDto
    ){
        User user = userService.saveUser(reqDto.toEntity());
        userService.addAuthority(user.getId(), "ROLE_USER"); // ROLE_USER 이란식으로 ROLE_을 붙여줘야 권한설정이 됨. -> USER -> forbidden -> 관련내용 https://minholee93.tistory.com/entry/Spring-Security-Authorities-Role
        return ResponseEntity.ok(user);
    }
}
