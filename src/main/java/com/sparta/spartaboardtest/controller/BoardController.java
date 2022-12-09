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
    private BoardRequestDto requestDto;

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
    @GetMapping("/api/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){
        return boardService.getBoard(id);

    }

    //게시글 수정
    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){

        return boardService.update(id, requestDto);
    }
    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id, @RequestBody int password){
        return boardService.deleteBoard(id, password);
    }
}