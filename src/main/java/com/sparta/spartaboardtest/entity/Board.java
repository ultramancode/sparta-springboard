package com.sparta.spartaboardtest.entity;


import com.sparta.spartaboardtest.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Getter
@Entity  //유일하고 고유한 놈이라 id같은 primary key 필요
@NoArgsConstructor //기본 생성자 생성
//entity 기본생성자가 있어야함. Reflection API를 활용하기 때문. JVM 실행시 자바 코드 static 영역에 저장 ->  Reflection API가는 구체적인 클래스 타입 몰라도 클래스 이름 통해
//static 영역에서 해당 클래스의 정보(메서드 타입 변수 등)에 접근 할 수있게 해줌. 생성자의 인자정보는 못가져 오기 때문에 기본 생성자가 있어야 객체 생성하고 그걸 통해 필드값 등을 넣어줄 수 있음
public class Board extends Timestamped{
    @Id //  JPA 엔티티 객체의 식별자로 사용할 필드에 적용
    @GeneratedValue(strategy = GenerationType.AUTO) //@GeneratedValue 식별자 값을 자동 생성함. strategy는 4종류가 있는데 AUTO는 자동으로 IDENTITY, SEQUENCE, TABLE 中 택 1 해줌
    private Long id;
    @Column(nullable = false)  //null 안됨
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;


    public Board(BoardRequestDto requestDto,String writer) {
        this.writer = writer;
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();

    }
    public void addUser(User user){
        this.user = user;
        user.getBoards().add(this);   //연관관계 편의 메소드

    }
    public void update(BoardRequestDto requestDto){
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();
    }
}