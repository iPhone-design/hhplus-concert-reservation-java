# 콘서트 예약 서비스

## 문서
> ### [Milestone](https://github.com/users/iPhone-design/projects/2)
> ### [SequenceDiagram](https://sequencediagram.org/index.html?presentationMode=readOnly&shrinkToFit=true#initialData=C4S2BsFMAJA15wcQcB1L1AQY4X1GBQGD03oG1ACxcAcF6QHB7BIOukHge6QAGbAGOsBfR6QR5bBdDoF0NACccAAJgLQA+QDtDgEqHAOosAuaCXJVAKWOBamYziJw-jMAu44EqxwAnj0QKprgD3Hoy3oNGTt+o6cCK44Fem1ZIEa+NgyeiATocAUy9Ai0E4u6kIkMnKUNPTMbOxmKiTCapGk0bSMLBzBzmop1rKkgIMDgDsL0IAtM4A2C9AAFGKAAPOAIJOAN+0AlKFu4cRp0IAznYAzHYH9Q+b5QppF8rlYuASAMROAuIOoaNz8BdLQSyuJnd293ubJVpty3iH57gem-sMXrvuxLIA4Q-B2dYAvy4CDnR3HqU-QV4zcZLGTbMrQQBINdBAAM9gF6axJ1QAh40FAAw9gFQJjpLDZg5YQ6Hw3LItGYvboGToXboHE7e5hCJTfGwhHKOpZaCAF1XADgTv2INLkTMJTjZcW5HXGk22KOgGJmODw+EADTVctaWf7K3bjBlnUxHPkna7A1xXKbeW5BOnCZUyDXmZU0jWWk2AFPHAIaj0EAAb2ARwnhrdLUJyTtBSylHVKYARMYGHWpBqD5QJCOFtQjUb2oK2ePjzMRybQWMWNPBWaFjjDeb22pKxZDdUGvP5Vah2aTdb2k0djiAA)
> ### [ERD](https://dbdiagram.io/d/consert-seat-reservation-service-668d2bd19939893dae72bf91)
> ### [API 명세서](https://documenter.getpostman.com/view/15679108/2sA3dyhWDj)
> ### [Swagger](https://github.com/user-attachments/assets/8638c1ac-4adf-42d8-bbec-c9fd32b3057c)

<details>
<summary><b>Junit 테스트 결과</b></summary>

- 단위 테스트
    - TokenServiceTest
        - ![image](https://github.com/user-attachments/assets/649eaf38-7f5e-4a74-99e5-15765bcc58ec)
    - SeatOptionServiceTest
        - ![image](https://github.com/user-attachments/assets/d7a0ef8d-6d98-471f-9910-b4f0a19f9b65)
    - ReservationServiceTest
        - ![image](https://github.com/user-attachments/assets/d36052d0-e2fe-411f-914f-c6e7f987beba)
    - PaymentServiceTest
        - ![image](https://github.com/user-attachments/assets/c48a90ce-76b0-4298-8487-096ef2e77ede)
    - ConcertOptionServiceTest
        - ![image](https://github.com/user-attachments/assets/3bbf7ef4-7600-4da9-bbef-9c40b139199a)


- 통합 테스트
    - TokenFacadeTest
        - ![image](https://github.com/user-attachments/assets/b270458b-d592-496f-a42b-72871a18ffab)
    - ReservationFacadeTest
        - ![image](https://github.com/user-attachments/assets/48ae86f0-557e-4338-b606-5b3af844ec16)
    - PaymentFacadeTest
        - ![image](https://github.com/user-attachments/assets/ac883506-e18e-4c44-bfa6-da6a318bedfe)
    - CustomerFacadeTest
        - ![image](https://github.com/user-attachments/assets/8bee0e77-45ef-47de-8b82-3298634aa6dc)
    - ConcertFacadeTest
        - ![image](https://github.com/user-attachments/assets/98ce654e-d5c8-4b3e-bff5-f04dd4b4069c)
</details>

<details>
<summary><b>Lock 테스트 결과</b></summary>

- **`적용 전`**
    - 콘서트 좌석 예약 요청
        - ![image](https://github.com/user-attachments/assets/1f4348bf-70dc-463a-b51c-a7c674950d1b)
      
    - 결제
        - ![image](https://github.com/user-attachments/assets/ceba8a6c-863c-4e19-9a21-a52a2e98eef1)

***

- **`비관적락`**

    - 콘서트 좌석 예약 요청
        - ![image](https://github.com/user-attachments/assets/cbe6117b-ab63-4f99-b4b3-75f38fd2a359)
     
    - 콘서트 좌석 예약 요청 테스트 코드
        - ![image](https://github.com/user-attachments/assets/7b181e5b-375a-4fc8-9991-ab875adf933c)



    - 결제
        - ![image](https://github.com/user-attachments/assets/2c43129a-6717-4df6-beea-07a7eb3a3f4b)

    - 결제 테스트 코드
        - ![image](https://github.com/user-attachments/assets/9df23872-b259-4403-aa5f-093ddda2742d)

***

- **`낙관적락`**
    - 콘서트 좌석 예약 요청
        - ![image](https://github.com/user-attachments/assets/9ca66541-c3f3-4740-8bc0-26c9063e17b2)
            - 낙관적락 오류는 발생하나 성공 Case가 없음

    - 콘서트 좌석 예약 요청 테스트 코드
        - ![image](https://github.com/user-attachments/assets/69f9c824-df3d-4c15-9773-ada46a888a20)
        - ![image](https://github.com/user-attachments/assets/518c28a2-2663-4672-86d4-18e7105640a8)
        - ![image](https://github.com/user-attachments/assets/9710aa69-d192-4e7b-aefb-3acdd846495e)

***

- **요약 : 비관적락은 적용 후 성공 Case까지 완료함. 낙관적락은 적용까지는 완료하였으나 retry 부분에 대해 추가 공부가 필요한 것 같다.. 그래서 본 로직에는 비관적락을 적용한 상태입니다.**
    - 비관적락 통합 테스트
    - ![image](https://github.com/user-attachments/assets/fcdc6738-3977-4fee-99c5-5aa5eed76549)
</details>


## Description

- **`콘서트 예약 서비스`** 를 구현해 봅니다.
- 대기열 시스템을 구축하고, 예약 서비스는 작업 가능한 유저만 수행할 수 있도록 해야합니다.
- 사용자는 좌석예약 시에 미리 충전한 잔액을 이용합니다.
- 좌석 예약 요청시에, 결제가 이루어지지 않더라도 일정 시간동안 다른 유저가 해당 좌석에 접근할 수 없도록 합니다.

## Requirements

- 아래 5가지 API 를 구현합니다.
    - 유저 토큰 발급 API
    - 예약 가능 날짜 / 좌석 API
    - 좌석 예약 요청 API
    - 잔액 충전 / 조회 API
    - 결제 API
- 각 기능 및 제약사항에 대해 단위 테스트를 반드시 하나 이상 작성하도록 합니다.
- 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.
- 동시성 이슈를 고려하여 구현합니다.
- 대기열 개념을 고려해 구현합니다.

## API Specs

1️⃣ **`주요` 유저 대기열 토큰 기능**

- 서비스를 이용할 토큰을 발급받는 API를 작성합니다.
- 토큰은 유저의 UUID 와 해당 유저의 대기열을 관리할 수 있는 정보 ( 대기 순서 or 잔여 시간 등 ) 를 포함합니다.
- 이후 모든 API 는 위 토큰을 이용해 대기열 검증을 통과해야 이용 가능합니다.

> 기본적으로 폴링으로 본인의 대기열을 확인한다고 가정하며, 다른 방안 또한 고려해보고 구현해 볼 수 있습니다.

**2️⃣ `기본` 예약 가능 날짜 / 좌석 API**

- 예약가능한 날짜와 해당 날짜의 좌석을 조회하는 API 를 각각 작성합니다.
- 예약 가능한 날짜 목록을 조회할 수 있습니다.
- 날짜 정보를 입력받아 예약가능한 좌석정보를 조회할 수 있습니다.

> 좌석 정보는 1 ~ 50 까지의 좌석번호로 관리됩니다.

3️⃣ **`주요` 좌석 예약 요청 API**

- 날짜와 좌석 정보를 입력받아 좌석을 예약 처리하는 API 를 작성합니다.
- 좌석 예약과 동시에 해당 좌석은 그 유저에게 약 (예시 : 5분)간 임시 배정됩니다. ( 시간은 정책에 따라 자율적으로 정의합니다. )
- 만약 배정 시간 내에 결제가 완료되지 않는다면 좌석에 대한 임시 배정은 해제되어야 하며 만약 임시배정된 상태라면 다른 사용자는 예약할 수 없어야 한다.

4️⃣ **`기본`**  **잔액 충전 / 조회 API**

- 결제에 사용될 금액을 API 를 통해 충전하는 API 를 작성합니다.
- 사용자 식별자 및 충전할 금액을 받아 잔액을 충전합니다.
- 사용자 식별자를 통해 해당 사용자의 잔액을 조회합니다.

5️⃣ **`주요` 결제 API**

- 결제 처리하고 결제 내역을 생성하는 API 를 작성합니다.
- 결제가 완료되면 해당 좌석의 소유권을 유저에게 배정하고 대기열 토큰을 만료시킵니다.

---

### 심화 과제

6️⃣ **`심화` 대기열 고도화**

- 다양한 전략을 통해 합리적으로 대기열을 제공할 방법을 고안합니다.
- e.g. 특정 시간 동안 N 명에게만 권한을 부여한다.
- e.g. 한번에 활성화된 최대 유저를 N 으로 유지한다.
