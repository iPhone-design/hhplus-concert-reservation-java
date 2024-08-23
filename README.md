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

<details>
<summary><b>Redis 캐싱</b></summary>

- **`적용 부분과 이유`**
    - 콘서트 예약 가능 날짜와 같은 데이터는 자주 수정되지 않고, 주로 조회되는 데이터입니다. Redis 캐시는 이러한 자주 수정되지 않는 데이터에 적합하다 생각합니다. 현재는 콘서트 예약 가능 날짜 데이터가 많지 않지만 앞으로 데이터의 양이 많아질 수 록 퍼포먼스가 더욱 극대화될 것이라고 예상합니다.
      
- **`적용전 후 비교`**
    - 예약 가능 콘서트 조회
        - ![image](https://github.com/user-attachments/assets/5374e085-ac22-4c93-95a6-eeea0e28106e)
</details>

<details>
<summary><b>Redis 대기열 구현</b></summary>

- **`AS-IS`**
    - 기존 스케줄러는 토큰 만료와 예약 미완료 시 좌석을 활성화하는 로직이 하나로 통합되어 있었습니다. 이로 인해 데이터가 증가할 경우 데이터베이스에 큰 부하를 초래할 것으로 예상되며, 쿼리의 의존성도 매우 커졌습니다. 이러한 문제를 해결하기 위해, 스케줄러의 로직을 다음과 같이 조정할 필요가 있습니다.
        - **로직 분리** : 토큰 만료 처리와 예약 미완료 좌석 활성화 처리를 각각 별도의 스케줄러로 분리하여 데이터베이스에 가해지는 부하를 줄입니다. 이 방법은 각 작업을 독립적으로 관리할 수 있게 하여 성능 최적화를 도와줍니다.
        - **캐시 활용** : Redis와 같은 캐시 솔루션을 활용하여 데이터베이스 조회 빈도를 줄입니다. 특히 자주 조회되는 데이터와 같이 변동이 적은 정보를 캐시에 저장하여 데이터베이스의 부하를 줄이고 쿼리 성능을 개선할 수 있습니다.
        - **쿼리 최적화** : 데이터베이스 쿼리를 최적화하여 불필요한 자원 소모를 줄입니다. 쿼리 성능을 향상시키기 위해 인덱스를 추가하거나 쿼리를 재작성하여 데이터베이스의 응답 속도를 개선할 수 있습니다.
    - 쿼리
        - ![image](https://github.com/user-attachments/assets/3981ecc8-0b81-45f0-a95b-4fc9b57d2ae4)

        - ![image](https://github.com/user-attachments/assets/aa80acd7-0490-48ed-aa7e-9b12c8802069)

    - 로직
        - ![image](https://github.com/user-attachments/assets/d80c468d-50d1-4427-a87d-75e6ac6b3b13)

- **`TO-BE`**
    - 기존의 스케줄러는 토큰 만료와 예약 미완료 시 좌석 활성화를 하나의 로직으로 처리했습니다. 이로 인해 데이터베이스에 높은 부하가 발생하고, 쿼리에 대한 의존성이 커졌습니다. 이를 해결하기 위해 다음과 같이 조정했습니다.
        - 토큰 만료 처리와 예약 미완료 좌석 활성화를 **별도의 스케줄러로 분리**했습니다.
        - 각 스케줄러는 **독립적**으로 작동하며, **특정 작업만**을 처리합니다.
        - 로직 분리로 인해 **데이터베이스의 부하가 줄어들고**, 각 작업에 대한 **성능 최적화가 가능**해졌습니다.
      
- **`결과`**
    - 현재의 결과는 더미 데이터가 많지 않아 효과적이다 볼 수는 없지만 추후에 데이터가 많아질 수 록 성능이 더 욱 좋아질 것으로 예상합니다.
        - 토큰 만료와 좌석 활성화 시키는 스케줄러 (DB)
            - ![image](https://github.com/user-attachments/assets/46447b1a-f710-4962-92de-56c744a886f7)
          
        - 대기열 토큰을 활성화 시키는 스케줄러 (Redis)
            - ![image](https://github.com/user-attachments/assets/94e18b42-2a6a-4360-813c-6439e400bd8b)

        - 토큰 만료와 좌석 활성화 시키는 스케줄러 (Redis)
            - ![image](https://github.com/user-attachments/assets/297a73df-cba6-4790-bec8-4ec0b2d594c6)
    - 로직
        - ![image](https://github.com/user-attachments/assets/eac7aaee-dc0e-4f9d-b08e-567ecbd4adc5)
        - ![image](https://github.com/user-attachments/assets/fe5102db-9825-41f3-8d10-f8a09faf6ec6)
</details>

<details>
<summary><b>INDEX 적용</b></summary>
    
- **`AS-IS`**
    - **예약 가능 날짜**
        - 실행계획
            - ![image](https://github.com/user-attachments/assets/8d2a0d4f-6876-4bb1-a841-527ab539e61b)
        - 소요시간
            - ![image](https://github.com/user-attachments/assets/aa86a4e7-6be5-43b2-987d-a4d2d95a218a)

    - **예약 가능 좌석**
        - 실행계획
            - ![image](https://github.com/user-attachments/assets/2e09a82b-729f-478c-bcd9-2aca00dd77d5)
        - 소요시간
            - ![image](https://github.com/user-attachments/assets/26ceefae-8c95-4056-a86e-c0968c21e09f)

- **`TO-BE`**
    - **예약 가능 날짜** : 예약 가능 날짜를 조회할 때, 대량의 콘서트 데이터 중에서 날짜 기준으로 조회하기 때문에 날짜에 대한 정보를 빠르게 필터 하기 위해 인덱스를 사용해야 한다고 생각하여 적용하였습니다.
        - 인덱스
            - [CONCERT_OPTION](https://github.com/iPhone-design/hhplus-concert-reservation-java/pull/33/commits/82f64bb4298cf7d10882d9e397a1a585c0053813)
        - 실행계획
            - ![image](https://github.com/user-attachments/assets/1ec77d86-a6e1-43cd-a4ca-7f49c5697b2e)
        - 소요시간
            - ![image](https://github.com/user-attachments/assets/9c484c03-f0ef-483b-b70a-37e3f18f3412)
              
    - **예약 가능 좌석** : 예약 가능 날짜를 조회할 때, 대량의 좌석 데이터 중에서 활성화 상태와 특정 콘서트 옵션 ID 기준으로 조회하기 때문에 상태, 콘서트 옵션 ID의 복합 인덱스를 사용해야 한다고 생각하여 적용하였습니다.
        - 인덱스
            - [SEAT_OPTION](https://github.com/iPhone-design/hhplus-concert-reservation-java/pull/33/commits/17c2d617a1845187204892a5d6a66992ed772949#diff-39601111723397cde51fa9421ae3bcba523479b69d6ad24231bdf8df9ff638a6)
        - 실행계획
            - ![image](https://github.com/user-attachments/assets/bdf147fd-f6f3-4c04-9347-5d99bb0e319b)
        - 소요시간
            - ![image](https://github.com/user-attachments/assets/c204bef7-c93f-492b-9c0f-52e4fed32536)
      
- **`결과`**
    - 예약 가능 날짜 : **68.64%** 소요 시간 개선
    - 예약 가능 좌석 : **55.55%** 소요 시간 개선
</details>

<details>
<summary><b>트랜잭션과 관심사 분리</b></summary>
    
- 분리할 필요가 있는 트랜잭션
    - 결제
        - 분리 필요성
            - 포인트 차감 로직과 결제 처리 로직이 하나의 트랜잭션 내에 포함되어 있을 경우, 결제 처리 로직이 실패하면 전체 결제가 실패하는 문제가 발생할 수 있습니다. 특히, 결제 처리 로직이 외부 API와 연동되어 있다면, 외부 API의 문제로 인해 결제가 실패할 경우 결제 시스템 전체에 영향을 미칠 수 있는 심각한 문제가 발생할 수 있습니다.
        - 로직
            - **`AS-IS`**
                - ```
                  // 트랜잭션 시작
                  예약 상태 값 변경
                  이용자 금액 사용
                  활성화 토큰 삭제
                  결제
                  // 트랜잭션 끝
                  ```
     
                - ![image](https://github.com/user-attachments/assets/be4cbdea-977d-4df1-ac2f-66a9e2534b62)
                  
            - **`TO-BE`**
                - ```
                  // 트랜잭션 시작
                  예약 상태 값 변경
                  이용자 금액 사용
                  활성화 토큰 삭제
                  // 트랜잭션 끝
                    
                  결제
                  ```
                - ![image](https://github.com/user-attachments/assets/bfdeb191-6ad2-4135-bc9e-2ef1c998816c)
                - ![image](https://github.com/user-attachments/assets/1daf7ec1-a899-4c47-a11f-a41dae9e209f)

    - 예약
        - 분리 필요성
            - 예약 정보를 저장하는 로직이 실패할 경우, 자리 임시 배정 처리가 롤백되는 문제가 발생할 수 있습니다. 이로 인해 사용자가 선택한 자리가 해제되어 혼란이 생길 수 있습니다. 또한, 이후 예약 정보를 처리하는 서비스가 분리되면 이러한 문제는 더 심각해질 수 있으며, 시스템 전반에 걸쳐 일관성을 유지하기 어려워질 수 있습니다.
        - 로직
            - **`AS-IS`**
                - ```
                  // 트랜잭션 시작
                  좌석 상태 값 변경
                  예약
                  // 트랜잭션 끝
                  ```
                - ![image](https://github.com/user-attachments/assets/1fc53a5c-dd94-42b9-a32b-2fb33b5124f8)

            - **`TO-BE`**
                - ```
                  // 트랜잭션 시작
                  좌석 상태 값 변경
                  // 트랜잭션 끝
    
                  예약
                  ```
                - ![image](https://github.com/user-attachments/assets/96a534f4-bbf8-4b7d-9e55-f04a8bf09ecb)
                - ![image](https://github.com/user-attachments/assets/ccfa3746-521c-4db1-ac4f-7908cea2f949)
</details>

<details>
<summary><b>Kafka 설정 및 연결 테스트</b></summary>
    
- **[Step17](https://github.com/iPhone-design/hhplus-concert-reservation-java/pull/35)**
    
- **[Kafka 설정](https://github.com/iPhone-design/hhplus-concert-reservation-java/pull/35/commits/24fc0026bdd511a0cbf04aa022d8bdd6afed8335)**

- **`Kafka 메시지 발행 및 소비 테스트 결과`**
    - ![image](https://github.com/user-attachments/assets/ae09ff84-3db4-4978-9144-c2b149083bb3)
</details>

<details>
<summary><b>Kafka 재발행 테스트</b></summary>

- **[Step18](https://github.com/iPhone-design/hhplus-concert-reservation-java/pull/36)**

- **`Outbox 재발행 테스트 결과`**
    - ![image](https://github.com/user-attachments/assets/ee5ec739-4603-4708-9b7d-3d67afebb4b4)
    - ![image](https://github.com/user-attachments/assets/277c34a5-ce5b-4640-a529-e63cc7794fed)


</details>

<details>
<summary><b>API 부하 테스트 분석</b></summary>
    
# API 부하 테스트 분석 보고서
- **테스트 목적**
	- 이 문서의 목적은 콘서트 예약 서비스 API의 성능, 안정성, 확장성을 평가하기 위해 부하 테스트와 최고 부하 테스트를 수행한 결과를 분석하는 것입니다. 이를 통해 시스템이 예상되는 트래픽을 얼마나 잘 처리할 수 있는지, 급증하는 트래픽 상황에서 안정적으로 동작할 수 있는지를 확인하고, 개선이 필요한 부분을 제시합니다.

- **테스트 도구 및 환경**
	- **K6** : 부하 테스트 도구로, 다양한 사용자 시나리오를 시뮬레이션하여 API의 성능을 평가합니다.
	- **InfluxDB** : 테스트 결과를 수집하고 저장하는 데 사용된 시계열 데이터베이스입니다.
	- **Grafana** : 시각화 도구로, InfluxDB에 저장된 데이터를 분석하고 대시보드에서 실시간으로 모니터링합니다.

- **주요 성능 지표 (K6 지표의 의미)**
	- **Checks**
		- checks : 성공률과 실패율을 보여주는 중요한 지표로, 성능과 관련된 다양한 조건이 충족되었는지 확인할 수 있습니다.
	- **Data Received / Data Sent**
		- data_received : 서버가 받은 총 데이터 양과 초당 받은 데이터 양을 나타냅니다.
		- data_sent : 클라이언트가 서버로 보낸 총 데이터 양과 초당 보낸 데이터 양을 나타냅니다.
	- **HTTP Request Metrics** : 각 요청의 지연 시간, 연결 시간, 전송 시간 등을 측정하여 응답 시간의 품질을 평가합니다.
		- http_req_blocked : 요청이 차단된 평균 시간, 최소, 중앙값, 최대값 등을 나타냅니다.
		- http_req_connecting : 서버와 연결을 수립하는 데 걸린 평균 시간입니다.
		- http_req_duration : 요청이 시작된 순간부터 완료되기까지 걸린 총 시간입니다. 이 값이 크면 서버의 응답이 느리다는 것을 의미합니다.
		- http_req_failed : 총 요청 중 실패한 요청의 비율입니다.
		- http_req_waiting : 클라이언트가 응답을 기다린 시간입니다.
		- http_req_receiving : 서버로부터 응답을 받은 시간입니다.
		- http_req_sending : 클라이언트가 요청을 서버로 보내는 데 걸린 시간입니다.
	- **Virtual Users (VUs)** : 테스트 동안 활성화된 가상 사용자 수를 통해 서버의 부하 처리 능력을 평가합니다.
		- vus : 테스트 동안 활성화된 가상 사용자의 수입니다. 최소값과 최대값도 함께 나타납니다.
		- vus_max : 테스트 동안 동시 활성화된 최대 가상 사용자 수입니다.

- **테스트 시나리오**
	- **Load Test (부하 테스트)**
		- **목적** : 일정한 트래픽 하에서 시스템의 안정성과 성능을 평가합니다.
		- **시나리오** : 가상의 사용자를 점진적으로 증가시키면서 시스템이 정상적으로 동작하는지 평가합니다.
		- **설정** : 1분간 50명, 3분간 100명, 1분간 150명의 가상 사용자 유지 이후 2분간 사용자를 0으로 감소시킵니다.
	-  **Peak Test (최고 부하 테스트)**
		- **목적** : 급증하는 트래픽 상황에서 시스템이 어떻게 반응하는지를 평가합니다.
		- **시나리오** : 사용자를 급격히 증가시켜 최고 부하 상태에서 시스템의 성능을 확인합니다.
		- **설정** : 2분간 100명, 2분간 500명, 1분간 1000명(피크 상태)의 가상 사용자 유지이후 사용자를 단계적으로 감소시킵니다.
  	 ```
	    	// Load 테스트 설정
		export let options = {
		    stages: [
			{ duration: '1m', target: 50 },   // 1분 동안 50명의 가상 사용자를 유지
			{ duration: '3m', target: 100 },  // 3분 동안 100명의 가상 사용자를 유지
			{ duration: '1m', target: 150 },  // 1분 동안 150명의 가상 사용자를 유지
			{ duration: '2m', target: 0 }     // 2분 동안 가상 사용자 수를 0으로 감소
		    ],
		};
		
		// Peak 테스트 설정
		export let options = {
		    stages: [
			{ duration: '2m', target: 100 },  // 2분 동안 100명의 가상 사용자를 유지
			{ duration: '2m', target: 500 },  // 2분 동안 가상 사용자를 500명으로 급증
			{ duration: '1m', target: 1000 }, // 1분 동안 가상 사용자를 1000명으로 급증 (피크)
			{ duration: '2m', target: 500 },  // 2분 동안 가상 사용자 수를 500명으로 감소
			{ duration: '2m', target: 100 },  // 2분 동안 100명의 가상 사용자를 유지
			{ duration: '2m', target: 0 }     // 2분 동안 가상 사용자 수를 0으로 감소
		    ],
		};
	```
      

- **테스트 결과 분석**
	- **유저 토큰 발급 API (POST /redis/issue)**
		- **Load Test 결과**
  			- ![image](https://github.com/user-attachments/assets/319dd709-8ee5-4924-8dde-16eb5ca7b551)
			- **성공률** : 100% (모든 요청이 성공적으로 처리됨)
			- **처리량** : 초당 약 52개의 요청을 처리하여 총 22,169개의 요청을 처리함
			- **응답 시간**
				- 평균 : 415.34ms
				- 중앙값 : 237.12ms
				- 90번째 백분위수 : 1.01s
				- 95번째 백분위수 : 1.13s
			- **분석** : 평균 응답 시간이 415.34ms로 양호한 편이나, 90번째와 95번째 백분위수에서 1초 이상 걸리는 경우가 나타났습니다. 이는 특정 시점에서 서버의 부하가 증가했음을 시사하며, 이러한 경우에 대한 최적화가 필요합니다.
		- **Peak Test 결과**
  			- ![image](https://github.com/user-attachments/assets/5fb9ff87-a3f1-4833-a885-181f4f7ce7a0)
			- **성공률** : 100% (모든 요청이 성공적으로 처리됨)
			- **처리량** : 초당 약 72개의 요청을 처리하여 총 47,845개의 요청을 처리함
			- **응답 시간**
				- 평균 : 3.76s
				- 중앙값 : 2.66s
				- 90번째 백분위수 : 8.77s
				- 95번째 백분위수 : 10.02s
			- **분석** : 피크 부하 상황에서 평균 응답 시간이 크게 증가하였으며, 95번째 백분위수에서 10초 이상의 응답 지연이 발생했습니다. 이는 시스템이 피크 부하를 처리하는 데 어려움을 겪고 있음을 나타내며, 특히 높은 트래픽에 대한 대응 방안이 필요합니다.
	- **예약 날짜 가능 조회 API (GET /concert/dates)**
		- **Load Test 결과**
  			- ![image](https://github.com/user-attachments/assets/f073bbc6-741c-4392-a888-d8a444b70577)
			 - **성공률** : 100% (모든 요청이 성공적으로 처리됨)
			 - **처리량** : 초당 약 73개의 요청을 처리하여 총 30,092개의 요청을 처리함
			 - **응답 시간**
				- 평균 : 6.26ms
				- 중앙값 : 5.62ms
				- 90번째 백분위수 : 10.92ms
				- 95번째 백분위수 : 12.87ms
			- **분석**: 응답 시간이 매우 짧아, 부하 테스트에서 우수한 성능을 보였습니다. API의 효율성과 네트워크 상태가 양호함을 알 수 있습니다.
		- **Peak Test 결과**
  			- ![image](https://github.com/user-attachments/assets/ee3ddc88-4ebc-4adc-8ae6-20eb05fb444c)
			- **성공률** : 100% (모든 요청이 성공적으로 처리됨)
			- **처리량** : 초당 약 325개의 요청을 처리하여 총 215,331개의 요청을 처리함
			- **응답 시간**
				- 평균 : 12.1ms
				- 중앙값 : 10.55ms
				- 90번째 백분위수 : 22.24ms
				- 95번째 백분위수 : 26.83ms
			- **분석** : 피크 부하 상황에서도 응답 시간이 12.1ms로 매우 짧았습니다. 다만, 95번째 백분위수에서 응답 시간이 26.83ms로 증가하였으며, 이는 매우 높은 트래픽에서도 우수한 성능을 유지하고 있음을 나타냅니다.
	- **콘서트 예약 API (POST /concert/reservation)**
		- **Load Test 결과**
			- TODO
			- **성공률** : TODO
			- **처리량** : TODO
			- **응답 시간**
				- 평균 : TODO
				- 중앙값 : TODO
				- 90번째 백분위수 : TODO
				- 95번째 백분위수 : TODO
			- **분석** : TODO
		- **Peak Test 결과**
			- TODO
			- **성공률** : TODO
			- **처리량** : TODO
			- **응답 시간**
				- 평균 : TODO
				- 중앙값 : TODO
				- 90번째 백분위수 : TODO
				- 95번째 백분위수 : TODO
			- **분석** : TODO

- **종합 분석 및 개선 방안**
	- **성공률 및 실패율** : 두 테스트 모두 100% 성공률을 기록하여 안정성이 높음을 나타냈습니다. 다만, 피크 테스트에서 응답 시간이 급격히 증가하는 부분은 개선이 필요합니다.
	- **응답 시간** : `POST /redis/issue API`의 경우 피크 부하에서의 응답 시간이 크게 증가했습니다. 이는 데이터베이스나 서버 자원에 대한 추가적인 최적화가 필요함을 의미합니다.
	- **서버 부하 처리 능력** : `GET /concert/dates API`는 피크 부하에서도 안정적인 성능을 유지했으나, `POST /redis/issue API`는 성능 저하가 발생하므로, 이를 처리하기 위한 캐싱 전략이나, 데이터베이스의 확장성 고려가 필요합니다.
	- **네트워크 성능** : 네트워크 지연은 큰 문제가 없었으나, 피크 부하에서의 연결 시간이나 전송 시간 분석이 추가로 필요합니다.
	- **데이터 전송 효율성** : 데이터 전송 양은 효율적으로 관리되고 있는 것으로 보이나, 피크 부하에서의 데이터 전송 최적화를 위해 압축이나 경량화 방안을 고려할 수 있습니다.

- **결론**
	- 이번 부하 테스트와 피크 테스트 결과, 콘서트 예약 서비스 API는 일반적인 트래픽 상황에서는 안정적으로 동작하고 있음을 확인했습니다. 다만, 피크 트래픽 상황에서 일부 API의 응답 시간이 크게 증가하는 현상이 관찰되었으며, 이에 대한 추가적인 최적화와 인프라 확장이 필요합니다. 특히, 급증하는 트래픽에 대한 대응력을 높이기 위한 캐싱 전략, 데이터베이스 확장성 고려, 네트워크 성능 최적화 방안을 검토하는 것이 필요합니다.
</details>

<details>
<summary><b>가상 장애 대응</b></summary>
    
# 장애 대응 보고서
- **장애 개요**
	- 장애 발생 서비스 : 콘서트 예약 시스템 API
	- 장애 발생 일시 : 2024년 8월 22일 19 :30
	- 장애 복구 일시 : 2024년 8월 22일 20 :45
	- 장애 지속 시간 : 1시간 15분
	- 장애 유형 : 데이터베이스 연결 장애
	
- **장애 발생 원인**
	- 데이터베이스 연결 풀 설정 오류 : 연결 풀(pool)의 설정 오류로 인해 비정상적으로 많은 연결이 생성되었고, 이로 인해 데이터베이스의 자원이 고갈되었습니다.
	- 과도한 트래픽 : 해당 시간대에 예상치를 초과하는 과도한 트래픽이 발생하였고, 데이터베이스 서버가 이를 처리하지 못해 연결이 끊기면서 API 서버가 정상적으로 요청을 처리하지 못했습니다.
	- 시스템 설계 문제 : 트래픽 급증에 대비한 충분한 시스템 설계가 미비하여 서버 자원의 효율적 분배 및 확장이 이루어지지 않았습니다.
		
- **장애 영향**
	- 유저 불편 : 장애가 발생한 1시간 15분 동안 사용자들은 콘서트 예약을 진행할 수 없었습니다.
	- 예약 요청 실패 : 약 5,000건의 예약 요청이 실패하였으며, 이로 인해 서비스 이용에 불편을 겪은 사용자가 다수 발생했습니다.
	- 비즈니스 손실 : 평균 콘서트 티켓 가격이 100,000원이며, 일반적인 예약 성공률이 60%임을 감안할 때 약 3,000건의 판매가 이루어질 것으로 추정됩니다. 이를 바탕으로 예상 손실 금액은 3억 원으로 산출됩니다.

- **장애 대응 절차**
	- 장애 탐지 및 알림 : 19 :30에 장애가 발생한 직후 시스템 모니터링 툴을 통해 장애가 탐지되었습니다. 관련 팀에게 자동 알림이 전송되었습니다.
	- 장애 원인 분석 : 초기 분석 결과 데이터베이스 연결 문제로 판단되어 해당 서버 및 연결 풀 설정을 점검하였습니다.
	- 응급 조치 : 연결 풀 설정을 임시로 수정하고, 데이터베이스 서버를 재시작하여 장애를 우선적으로 해결하였습니다.
	- 복구 및 확인 : 20 :45에 시스템이 정상화되었음을 확인하고, 추가적인 트래픽 테스트를 통해 복구 상태를 점검하였습니다.

- **장애 방지 정책**
	- 숏텀 (Short-term)
		- 데이터베이스 연결 풀 설정 조정 : 불필요한 연결 생성을 방지하기 위해 연결 풀 설정을 조정하고, 필요 시 최대 연결 수를 제한하여 자원 고갈을 방지합니다.
		- 모니터링 강화 : 트래픽 급증 시 알림을 받을 수 있는 모니터링 시스템을 강화하고, 실시간으로 트래픽 상태를 모니터링할 수 있는 대시보드를 추가로 구축합니다.
		- 장애 대응 절차 정립 및 교육 : API 서버 장애 발생 시 빠르게 대응할 수 있도록 장애 대응 절차를 재정립하고, 이를 바탕으로 팀원들을 대상으로 정기적인 교육을 실시합니다.

	- 미드텀 (Mid-term)
		- 서버 용량 확장 : 데이터베이스 서버의 용량을 확장하고, 서버 리소스의 효율적 배분을 통해 트래픽 증가에 대응할 수 있도록 시스템을 업그레이드합니다.
		- 트래픽 패턴 분석 및 분산 : 특정 시간대에 트래픽이 집중되는 패턴을 분석하여, 트래픽 분산을 위한 로드 밸런싱 및 캐싱 전략을 도입합니다.
		- 쿼리 최적화 : 데이터베이스 쿼리의 효율성을 향상시키기 위한 최적화 작업을 수행하고, 자주 조회되는 데이터에 대해 캐싱을 적용하여 데이터베이스 부하를 줄입니다.

	- 롱텀 (Long-term)
		- 서버 이중화 및 자동 페일오버(failover) 시스템 도입 : 장애 발생 시에도 서비스가 중단되지 않도록 이중화 및 자동 페일오버 시스템을 도입합니다.
		- 클라우드 기반 자원 할당 시스템 : 트래픽 변화에 따라 자원을 탄력적으로 할당할 수 있는 클라우드 기반의 시스템을 도입하여 유연하게 대응할 수 있도록 합니다.
		- 주기적인 모의 훈련 : 장애 상황에 대한 주기적인 모의 훈련을 통해 장애 대응 능력을 강화하고, 실질적인 장애 대응 절차를 지속적으로 점검합니다.

- **결론 및 교훈**
	- 이번 장애는 트래픽 급증에 대한 대비가 충분하지 못한 점과 데이터베이스 연결 풀 설정의 미비로 인해 발생했습니다. 장애가 발생한 기간 동안 사용자들에게 큰 불편을 초래했고, 비즈니스 측면에서도 상당한 손실이 발생했습니다. 이를 교훈 삼아, 앞으로는 장애 예방 및 대응 절차를 강화하여 동일한 문제가 재발하지 않도록 최선을 다할 것입니다.
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
