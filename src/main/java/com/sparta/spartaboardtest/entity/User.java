package com.sparta.spartaboardtest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;

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
//    @Pattern(regexp = "[a-z0-9]{4,10}", message = "사용자 이름은 알파벳 소문자, 숫자로 구성한 4~10자 사이로 입력해주세요.")
    public String username;

    @Column(nullable = false)
//    @Pattern(regexp = "[a-z0-9]{4,10}", message = "사용자 이름은 알파벳 소문자, 숫자로 구성한 4~10자 사이로 입력해주세요.")
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany
    private final List<Board> boards = new ArrayList<>();

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}