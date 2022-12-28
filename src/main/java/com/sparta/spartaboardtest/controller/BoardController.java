package com.sparta.spartaboardtest.controller;

import com.sparta.spartaboardtest.dto.BoardRequestDto;
import com.sparta.spartaboardtest.dto.BoardResponseDto;
import com.sparta.spartaboardtest.entity.Board;
import com.sparta.spartaboardtest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController // Restful한 Controller (json형식으로 왔다갔다해주는 느낌?)
@RequiredArgsConstructor //final 붙은 필드 받는 생성자 자동 생성

public class BoardController {

    private final BoardService boardService;  //의존성 주입(new로 새로 객체 생성 안해줘도 됨) @RequiredArgsConstructor + final(필수라고 지정)

    // 게시글 작성
    @PostMapping("/api/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request){  //jwt 이용해야하니 HttpServletRequest(토큰정보 포함)
        //requestDto, request 에 데이터를 담아서, boardService로 응답을 보냄
    return boardService.createBoard(requestDto, request);
    }
    // 전체 게시글 조회
    @GetMapping("/api/boards")
    public List<BoardResponseDto> getBoardList(HttpServletRequest request) {

        return boardService.getBoardList(request);
    }
    //개별 게시글 조회
    @GetMapping("/api/boards/{id}")   //API URI에 변수가 들어가는 경우 @PathVariable 붙이기, id 값으로 각각의 게시글을 구별
    public BoardResponseDto getBoard(@PathVariable Long id){
        //id값 담아서 boardService로 보내기
        return boardService.getBoard(id);
    }

    //게시글 수정
    @PutMapping("/api/boards/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request){
        return boardService.update(id, requestDto, request);
    }
    // 게시글 삭제
    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id, HttpServletRequest request){
        return boardService.deleteBoard(id, request);
    }

}