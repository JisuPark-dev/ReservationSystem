# ReservationSystem
프로젝트명 : 매장 예약 시스템

프로젝트 기능 소개 : 

---

# API 명세서

## ✅ 사용자 관련 API
### 1) 사용자 생성

## ✅ 매장 관련 API
### 1) 매장 등록
- 파라미터 : storeDto
  - 입력 예시
      > {
          "name" : "testStore3",
          "location" : "seoul",
          "description" : "test description2"
      }
- 결과
  - 성공
    - 반환결과
    - >{
      "id": 13,
      "member": {
      "id": 12,
      "username": "mockMember",
      "password": "mockPassword",
      "memberStatus": "PARTNER"
      },
      "name": "testStore3",
      "location": "seoul",
      "description": "test description2",
      "createdAt": "2023-08-17T14:28:38.676748",
      "updatedAt": null
      }
### 2) 매장 리스트 조회
- 결과
  - 매장 리스트 확인 가능

### 3) 매장 상세정보 조회
- 파라미터 : storeId
- 결과
  - 매장 상세 정보 표시
### 4) 매장 정보 수정
파라미터 : storeId, storeDto
결과
- 입력값이 있는 데이터에 대해서만 수정 진행

### 5) 매장 정보 삭제(등록 해제)
파라미터 : storeId

결과 : 삭제기능 구현

## ✅ 예약 관련 API
### 1) 예약 생성
### 2) 사용자별 예약 목록 조회
### 3) 상점별 예약 목록 조회
### 4) 예약 확정
### 5) 예약 취소

## ✅ 리뷰 관련 API
### 1) 사용자별 미작성 리뷰 조회
### 2) 사용자별 작성된 리뷰 조회
### 3) 매장별 작성된 리뷰 조회
### 4) 리뷰 등록
### 5) 리뷰 수정
### 6) 리뷰 삭제
