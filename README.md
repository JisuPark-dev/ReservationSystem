# ReservationSystem
프로젝트명 : 매장 예약 시스템

프로젝트 기능 소개 : 

---

# API 명세서

## ✅ 사용자 관련 API
### 1) 사용자 생성

## ✅ 매장 관련 API
### 1) 매장등록
<details>
<summary> 파라미터 / 결과</summary>

파라미터 : storeDto
  ~~~
  {
    "memberId" : 2,
    "name" : "testStore",
    "location" : "seoul",
    "description" : "test description"
  }
  ~~~
결과
- 성공
    ~~~ 
    {
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
    ~~~
- 실패
</details>


### 2) 매장 리스트 조회
<details>
<summary>파라미터 / 결과</summary>

결과
- 매장 리스트 확인 가능
</details>


### 3) 매장 상세정보 조회
<details>
<summary>파라미터 / 결과</summary>

파라미터 : storeId
결과
- 매장 상세 정보 표시
</details>

### 4) 매장 정보 수정
<details>
<summary>파라미터 / 결과</summary>

파라미터 : storeId, storeDto
결과
- 입력값이 있는 데이터에 대해서만 수정 진행
</details>


### 5) 매장 정보 삭제(등록 해제)
<details>
<summary>파라미터 / 결과</summary>

파라미터 : storeId

결과 : 삭제기능 구현
</details>

## ✅ 예약 관련 API
### 1) 예약 생성
<details>
<summary>파라미터 / 결과</summary>

파라미터
~~~
{
  "memberId" : 2,
  "storeId" : 15,
  "reservationStatus" : "REQUESTED",
  "time" : "2023-08-18T14:30:00"
}
~~~

결과
~~~
{
  "id": 18,
  "member": {
    "id": 2,
    "username": "jisu",
    "password": "dodlal123",
    "memberStatus": "CLIENT"
  },
  "store": {
    "id": 15,
    "member": {
    "id": 2,
    "username": "jisu",
    "password": "dodlal123",
    "memberStatus": "CLIENT"
    },
    "name": "testStore4",
    "location": "seoul",
    "description": "test description2",
    "createdAt": "2023-08-17T14:55:54.646998",
    "updatedAt": null
  },
  "review": null,
  "reservationStatus": "REQUESTED",
  "time": "2023-08-18T14:30:00",
  "createdAt": "2023-08-17T15:48:52.295923",
  "updatedAt": null
}
~~~
</details>

### 2) 사용자별 예약 목록 조회
<details>
<summary>파라미터 / 결과</summary>

파라미터 : http://localhost:8080/reservation/member/19

결과
~~~
[
    {
        "id": 20,
        "member": {
            "id": 19,
            "username": "jiho",
            "password": "dodlal1234",
            "memberStatus": "PARTNER"
        },
        "store": {
            "id": 15,
            "member": {
                "id": 2,
                "username": "jisu",
                "password": "dodlal123",
                "memberStatus": "CLIENT"
            },
            "name": "testStore4",
            "location": "seoul",
            "description": "test description2",
            "createdAt": "2023-08-17T14:55:54.646998",
            "updatedAt": null
        },
        "review": null,
        "reservationStatus": "REQUESTED",
        "time": "2023-08-18T14:30:00",
        "createdAt": "2023-08-17T16:22:17.555752",
        "updatedAt": null
    },
    {
        "id": 21,
        "member": {
            "id": 19,
            "username": "jiho",
            "password": "dodlal1234",
            "memberStatus": "PARTNER"
        },
        ...
]
~~~

</details>

### 3) 상점별 예약 목록 조회
<details>
<summary>파라미터 / 결과</summary>

파라미터 : http://localhost:8080/reservation/store/15

결과 
~~~
[
    {
        "id": 17,
        "member": {
            "id": 2,
            "username": "jisu",
            "password": "dodlal123",
            "memberStatus": "CLIENT"
        },
        "store": {
            "id": 15,
            "member": {
                "id": 2,
                "username": "jisu",
                "password": "dodlal123",
                "memberStatus": "CLIENT"
            },
            "name": "testStore4",
            "location": "seoul",
            "description": "test description2",
            "createdAt": "2023-08-17T14:55:54.646998",
            "updatedAt": null
        },
        "review": null,
        "reservationStatus": "REQUESTED",
        "time": "2023-08-18T14:30:00",
        "createdAt": "2023-08-17T15:23:20.871731",
        "updatedAt": null
    },
    ...
]
~~~

</details>

### 4) 예약 확정
<details>
<summary>파라미터 / 결과</summary>

파라미터 : http://localhost:8080/reservation/cancel?reservationId=21

결과 
~~~
{
    "id": 21,
    "member": {
        "id": 19,
        "username": "jiho",
        "password": "dodlal1234",
        "memberStatus": "PARTNER"
    },
    "store": {
        "id": 15,
        "member": {
            "id": 2,
            "username": "jisu",
            "password": "dodlal123",
            "memberStatus": "CLIENT"
        },
        "name": "testStore4",
        "location": "seoul",
        "description": "test description2",
        "createdAt": "2023-08-17T14:55:54.646998",
        "updatedAt": null
    },
    "review": null,
    "reservationStatus": "CONFIRMED",
    "time": "2023-08-18T14:30:00",
    "createdAt": "2023-08-17T16:22:18.306983",
    "updatedAt": null
}
~~~
"reservationStatus": "CONFIRMED"으로 변경됨.

</details>

### 5) 예약 취소
<details>
<summary>파라미터 / 결과</summary>

파라미터 : http://localhost:8080/reservation/cancel?reservationId=21

결과 
~~~
{
    "id": 21,
    "member": {
        "id": 19,
        "username": "jiho",
        "password": "dodlal1234",
        "memberStatus": "PARTNER"
    },
    "store": {
        "id": 15,
        "member": {
            "id": 2,
            "username": "jisu",
            "password": "dodlal123",
            "memberStatus": "CLIENT"
        },
        "name": "testStore4",
        "location": "seoul",
        "description": "test description2",
        "createdAt": "2023-08-17T14:55:54.646998",
        "updatedAt": null
    },
    "review": null,
    "reservationStatus": "CANCELED",
    "time": "2023-08-18T14:30:00",
    "createdAt": "2023-08-17T16:22:18.306983",
    "updatedAt": null
}
~~~

"reservationStatus": "CANCELED"으로 변경됨.

</details>

## ✅ 리뷰 관련 API
### 1) 사용자별 미작성 리뷰 조회
### 2) 사용자별 작성된 리뷰 조회
### 3) 매장별 작성된 리뷰 조회
### 4) 리뷰 등록
### 5) 리뷰 수정
### 6) 리뷰 삭제
