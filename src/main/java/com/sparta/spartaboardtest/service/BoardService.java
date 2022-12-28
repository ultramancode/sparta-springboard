package com.sparta.spartaboardtest.service;


import com.sparta.spartaboardtest.dto.BoardRequestDto;
import com.sparta.spartaboardtest.dto.BoardResponseDto;
import com.sparta.spartaboardtest.entity.Board;
import com.sparta.spartaboardtest.entity.User;
import com.sparta.spartaboardtest.jwt.JwtUtil;
import com.sparta.spartaboardtest.repository.BoardRepository;
import com.sparta.spartaboardtest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service // DB또는 Controller를 통해서 전달받은 데이터를 가지고 DB나 entity+entity에 있는 행위(ex.update)들 일을 시킴
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;


    @Transactional //
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
            //.orElseThrow 이 부분은 optional 기능임. 새로운 오류를 만들어서 처리해라.
            //이렇게 안할거면 막 if(board == null) {} else{} 이런 식으로 하나 하나 핸들링 해가며 널포인터 처리해야됨


            Board board = new Board(requestDto, user.getUsername());
            board.addUser(user);
            boardRepository.save(board);
            //@Transactional 달면 .save 이거 안써줘도 알아서 DB에 저장된다고 했는데 왜 id랑 localdatetime null 뜨네 h2저장안되고.. 생성할때는 예외인가??
            return new BoardResponseDto(board);

        } else {
            throw new IllegalArgumentException("올바른 Token값을 넣어주세요");  // null 대신 오류 보내자 왜 return null;만 생각했지..
        }
    }
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoardList() {
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();  ///담을 공간 만들어 주고
        for(Board board : boardList){ //for each문으로 돌면서 하나하나 훑고
            boardResponseDtoList.add(new BoardResponseDto(board)); // board를 BoardResponserDto 타입으로 add해서 DtoList에 저장!!
        }
        return boardResponseDtoList;
    }



    @Transactional
    public BoardResponseDto getBoard(Long id) {
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );

//            BoardResponseDto boardResponseDto = new BoardResponseDto(board);
//            return boardResponseDto;
            return new BoardResponseDto(board);   //위에랑 같???
        }


    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
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
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );
            board.update(requestDto);
//            BoardResponseDto boardResponseDto = new BoardResponseDto(board);
//            return boardResponseDto;
            return new BoardResponseDto(board);
        }
        else {
            throw new IllegalArgumentException("올바른 Token값을 넣어주세요");
        }
    }




    @Transactional
    public Long deleteBoard(Long id, HttpServletRequest request) {
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
                        () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
                );

                if(user.getUsername().equals(board.getWriter())) {
                    boardRepository.deleteById(id);
                }else {
                    throw new IllegalArgumentException("올바른 Token값을 넣어주세요");
                }
        }
        return id;
    }
}





