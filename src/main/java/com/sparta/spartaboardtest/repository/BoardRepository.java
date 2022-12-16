package com.sparta.spartaboardtest.repository;

import com.sparta.spartaboardtest.entity.Board;
import com.sparta.spartaboardtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {


    List<Board> findAllByOrderByModifiedAtDesc();
    Optional<Board> findByUsername(String username);

}
