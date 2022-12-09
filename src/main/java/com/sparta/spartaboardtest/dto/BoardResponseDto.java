package com.sparta.spartaboardtest.dto;

import com.sparta.spartaboardtest.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private String username;
    private String contents;
    private String title;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public BoardResponseDto(Board board) {
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.title = board.getTitle();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}



