package com.sparta.spartaboardtest.entity;


import com.sparta.spartaboardtest.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor

public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;


    public Board(BoardRequestDto requestDto,String username) {
        this.username = username;
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();

    }
    public void addUser(User user){
        this.user = user;
        user.getBoards().add(this);

    }
    public void update(BoardRequestDto requestDto){
//        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}