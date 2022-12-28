package com.sparta.spartaboardtest.dto;

import com.sparta.spartaboardtest.entity.User;
import com.sparta.spartaboardtest.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String username;
    private UserRoleEnum role;

    public UserResponseDto(User user){
        this.username = user.getUsername();
        this.role = user.getRole();
        }

    }

