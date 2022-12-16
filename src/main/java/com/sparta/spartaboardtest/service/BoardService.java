package com.sparta.spartaboardtest.service;


import com.sparta.spartaboardtest.dto.BoardRequestDto;
import com.sparta.spartaboardtest.dto.BoardResponseDto;
import com.sparta.spartaboardtest.dto.LoginRequestDto;
import com.sparta.spartaboardtest.dto.SignupRequestDto;
import com.sparta.spartaboardtest.entity.Board;
import com.sparta.spartaboardtest.entity.User;
import com.sparta.spartaboardtest.entity.UserRoleEnum;
import com.sparta.spartaboardtest.jwt.JwtUtil;
import com.sparta.spartaboardtest.repository.BoardRepository;
import com.sparta.spartaboardtest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional


    public Board createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

        Board board = boardRepository.save(new Board(requestDto, user);
            return new BoardResponseDto(board);

//
//            new Board(requestDto);
//        boardRepository.save(board);
//        return board;
    }
    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }



    @Transactional
    public BoardResponseDto getBoard(Long id, HttpServletRequest request){
            if (token != null) {
                // Token 검증
                if (jwtUtil.validateToken(token)) {
                    // 토큰에서 사용자 정보 가져오기
                    claims = jwtUtil.getUserInfoFromToken(token);
                } else {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
                User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
                );

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        return boardResponseDto;

    }


    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
            if (token != null) {
                // Token 검증
                if (jwtUtil.validateToken(token)) {
                    // 토큰에서 사용자 정보 가져오기
                    claims = jwtUtil.getUserInfoFromToken(token);
                } else {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
                User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
                );
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        if(Objects.equals(board.getPassword(), requestDto.getPassword())) {
                    board.update(requestDto);
                }else {
                    return -1L;}
            }
        return board.getId();
    }

    @Transactional
    public Long deleteBoard(Long id, String password) {
            if (token != null) {
                // Token 검증
                if (jwtUtil.validateToken(token)) {
                    // 토큰에서 사용자 정보 가져오기
                    claims = jwtUtil.getUserInfoFromToken(token);
                } else {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
                User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
                );
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//
//        if(!board.getPassword().equals(password)){
//            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return id;
    }

}
}




