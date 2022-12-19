package com.sparta.spartaboardtest.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Pattern;

@Setter
@Getter
    public class SignupRequestDto {
//        @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "username은 알파벳 소문자와 숫자로 구성된 4~10자 입니다")
        @Pattern(regexp = "[a-z0-9]{4,10}", message = "사용자 이름은 알파벳 소문자, 숫자로 구성한 4~10자 사이로 입력해주세요.")
        private String username;
        @Pattern(regexp = "[a-z0-9]{4,10}", message = "사용자 이름은 알파벳 소문자, 숫자로 구성한 4~10자 사이로 입력해주세요.")
        private String password;
        private String email;
        private boolean admin = false;
        private String adminToken = "";
    }
