package com.sparta.spartaboardtest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users")  //h2에서 user 예약어로 돼서 users로 쓰는 것
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)

    public String username;

    @Column(nullable = false)

    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user"/*,cascade = CascadeType.REMOVE*/)  //user 지우면 board도 지우는 것 user만 지우면 남아있는 글들 오류나니 cacade로 지우기..이건 댓글에서 활용!! 여기 아님!!
    private final List<Board> boards = new ArrayList<>();

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}