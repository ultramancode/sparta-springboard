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

    private final JwtUtil jwtUtil;


    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 작성 가능
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

            Board board = new Board(requestDto,user.getUsername());
            board.addUser(user);
            boardRepository.save(board);
            return new BoardResponseDto(board);

        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }



    @Transactional
    public BoardResponseDto getBoard(Long id, HttpServletRequest request) {
        Claims claims;
        String token = jwtUtil.resolveToken(request);

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

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("해당 글은 존재하지 않습니다.")
            );
           BoardResponseDto boardResponseDto = new BoardResponseDto(board);

            return boardResponseDto;

        }
        return null;
    }


    @Transactional
    public Long update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        Claims claims;
        String token = jwtUtil.resolveToken(request);
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
                Board board = boardRepository.findById(id).orElseThrow(
                        () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
                );
                    board.update(requestDto);

                return board.getId();
                }else {
                    return -1L;}
            }



    @Transactional
    public Long deleteBoard(Long id, HttpServletRequest request/* ,Board board*/) {
        Claims claims;
        String token = jwtUtil.resolveToken(request);
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
                Board board = boardRepository.findById(id).orElseThrow(
                        () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
                );

                if(user.getUsername().equals(board.getUsername())) {
                    boardRepository.deleteById(id);
                }else {
                    throw new IllegalArgumentException("삭제 권한이 없습니다.");
                }
        }

        return id;

    }

}





