package com.example.comumnity.domain.user.web.dto.req;


import com.example.comumnity.domain.user.domain.Gender;
import com.example.comumnity.domain.user.domain.Role;
import com.example.comumnity.domain.user.domain.User;
import com.example.comumnity.domain.user.domain.UserAuthority;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpReqDto {

    @JsonProperty("account")
    private String account;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("nick_name")
    private String nickName;

    private String email;

    private String password;

    private Gender gender;

    private String birth;

    private Set<UserAuthority> authorities;

    public User toEntity(){
        return User
                .builder()
                .account(account)
                .nickName(nickName)
                .userName(userName)
                .email(email)
                .password(password)
                .gender(gender)
                .role(Role.USER)
                .birth(birth)
                .enabled(true)
                .authorities(authorities)
                .build();
    }
}
