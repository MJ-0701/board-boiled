package com.example.blinddate.domain.user.web.controller;

import com.example.blinddate.domain.user.config.JwtTokenProvider;
import com.example.blinddate.domain.user.domain.Role;
import com.example.blinddate.domain.user.domain.User;
import com.example.blinddate.domain.user.service.UserService;
import com.example.blinddate.domain.user.web.dto.req.UserLoginReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/login")
public class LoginApiController {

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/make-token")
    public ResponseEntity<String> login(@RequestBody UserLoginReqDto reqDto){
        User user = userService.findByUserIdAndPassword(reqDto.getAccount(), reqDto.getPassword());
        System.out.println("권한 :" + Role.USER.name());
        return ResponseEntity.ok(jwtTokenProvider.createToken(user.getAccount(), user.getRole()));
    }
}
