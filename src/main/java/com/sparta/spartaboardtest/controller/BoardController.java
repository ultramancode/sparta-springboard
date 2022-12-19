package com.sparta.spartaboardtest.controller;

import com.sparta.spartaboardtest.dto.BoardRequestDto;
import com.sparta.spartaboardtest.dto.BoardResponseDto;
import com.sparta.spartaboardtest.dto.LoginRequestDto;
import com.sparta.spartaboardtest.dto.SignupRequestDto;
import com.sparta.spartaboardtest.entity.Board;
import com.sparta.spartaboardtest.service.BoardService;
import com.sparta.spartaboardtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request){
        System.out.println(requestDto.getUsername());
    return boardService.createBoard(requestDto, request);
    }
    // 전체 게시글 조회
    @GetMapping("/api/boards")
    public List<Board> getBoards() {
        return boardService.getBoards();
    }
    @GetMapping("/api/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id, HttpServletRequest request){
        return boardService.getBoard(id, request);

    }

    //게시글 수정
    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request){

        return boardService.update(id, requestDto, request);
    }
    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id, HttpServletRequest request/*, Board board*/){
        return boardService.deleteBoard(id, request/*, board*/);
    }

}