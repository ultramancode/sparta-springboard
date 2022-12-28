package com.sparta.spartaboardtest.controller;

import com.sparta.spartaboardtest.dto.LoginRequestDto;
import com.sparta.spartaboardtest.dto.SignupRequestDto;
import com.sparta.spartaboardtest.dto.UserResponseDto;
import com.sparta.spartaboardtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {   // @RequestBody 옆에 @Valid를 작성하면, RequestBody로 들어오는 객체에 대한 검증을 수행(정규표현식)
       return userService.signup(signupRequestDto);
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        //login메서드 보이드로 해놨으니 밑에처럼 success 리턴해놓음
        return "success";
    }

}