package com.sparta.spartaboardtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing  ///Auditing 기능 활성화 .. entity에서 발생하는 변경사항 추적에 도움
@SpringBootApplication
@EnableScheduling
public class SpartaBoardTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaBoardTestApplication.class, args);
    }

}
