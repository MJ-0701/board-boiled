package com.example.comumnity.domain.user.web.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReqDto {

    private String account;

    private String password;

    @JsonProperty("refresh_time")
    private String refreshTime;
}
