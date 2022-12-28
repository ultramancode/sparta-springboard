package com.sparta.spartaboardtest.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //공통 매핑 정보가 필요할 때, 부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때 @MappedSuperclass를 사용한다.
@EntityListeners(AuditingEntityListener.class) //entity를 DB에 적용하기 전,후 커스텀로직 선언하는 인터페이스. AuditingEntityListener(JPA기능) 등록
//AuditingEntityListener는 해당 엔티티에 선언된 CreatedDate, LastModifiedDate 어노테이션을 탐색해 엔티티 변경 시 해당값을 자동으로 업데이트 해줌



public class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


}
