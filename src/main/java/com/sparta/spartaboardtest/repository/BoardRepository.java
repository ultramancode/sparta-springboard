package com.sparta.spartaboardtest.repository;

import com.sparta.spartaboardtest.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByModifiedAtDesc();  //findAll ByOrder 순서대로 ByModifiedAtDesc 내림차순 수정시간.. ()

    List<Board> findAllByUserId(Long id);
}
