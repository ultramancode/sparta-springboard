# 게시판 만들기 과제 (spring)

Created: 2022년 12월 9일 오후 12:17
Last Edited Time: 2022년 12월 9일 오후 12:31

# README

---

미완성입니다……………………………………………………………………………….

- 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)

—> body 사용

- 어떤 상황에 어떤 방식의 request를 써야하나요?

ㅡ> json방식을 담기 위해 body 사용했습니다 

- RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?

ㅡ>………………………………………

- 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)

ㅡ>네 창구역할하는 Controller, 비즈니스 로직이 들어가는 Service, DB와 연결하는 Repository로 나눴습니다.

- API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!

ㅡ> 네

## API 명세서

| 기능 | Method | URL | Request | Response |
| --- | --- | --- | --- | --- |
| 전체 게시글 조회 | GET | /api/boards |  | 작성시간
수정시간
닉네임
제목
내용 |
| 선택 게시글 조회 | GET | /api/boards |  | 작성시간
수정시간
닉네임
제목
내용 |
| 게시글 작성 | POST | /api/boards | 닉네임
제목
내용
비밀번호 | 작성시간
수정시간
닉네임
제목
내용 |
| 선택 게시글 수정 | PUT | /api/boards/{id} | 닉네임
제목
내용
비밀번호 | 작성시간
수정시간
닉네임
제목
내용 |
| 선택 게시글 삭제 | DELETE | /api/boards/{id} | 비밀번호 |  |

## Usecase

![Untitled](%E1%84%80%E1%85%A6%E1%84%89%E1%85%B5%E1%84%91%E1%85%A1%E1%86%AB%20%E1%84%86%E1%85%A1%E1%86%AB%E1%84%83%E1%85%B3%E1%86%AF%E1%84%80%E1%85%B5%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A6%20(spring)%20c09fe9812b19495b8cc26146c674df3a/Untitled.png)