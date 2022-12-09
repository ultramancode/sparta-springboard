package com.sparta.spartaboardtest.controller;

import com.sparta.spartaboardtest.dto.BoardRequestDto;
import com.sparta.spartaboardtest.dto.BoardResponseDto;
import com.sparta.spartaboardtest.entity.Board;
import com.sparta.spartaboardtest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class BoardController {


    private final BoardService boardService;
    private Long id;
    private BoardResponseDto responseDto;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
    // 게시글 작성
    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){

    return boardService.createBoard(requestDto);
    }
    // 전체 게시글 조회
    @GetMapping("/api/boards")
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    //게시글 수정
    @PutMapping("/api/memos/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardResponseDto responseDto){
        this.id = id;
        this.responseDto = responseDto;
        return boardService.update(id, responseDto);
    }
    // 게시글 삭제
    @DeleteMapping("/api/memos/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestBody String password){
        return boardService.deleteBoard(id, password);
    }
}