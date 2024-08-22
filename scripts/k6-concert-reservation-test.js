import http from 'k6/http';
import { check, sleep } from 'k6';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

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
//export let options = {
//    stages: [
//        { duration: '2m', target: 100 },  // 2분 동안 100명의 가상 사용자를 유지
//        { duration: '2m', target: 500 },  // 2분 동안 가상 사용자를 500명으로 급증
//        { duration: '1m', target: 1000 }, // 1분 동안 가상 사용자를 1000명으로 급증 (피크)
//        { duration: '2m', target: 500 },  // 2분 동안 가상 사용자 수를 500명으로 감소
//        { duration: '2m', target: 100 },  // 2분 동안 100명의 가상 사용자를 유지
//        { duration: '2m', target: 0 }     // 2분 동안 가상 사용자 수를 0으로 감소
//    ],
//};

// 콘서트 예약
export default function concertReservation() {
    // 요청 URL
    const url = 'http://localhost:8080/concert/reservation';

    // 요청 페이로드
    const payload = JSON.stringify({
        // customerId 랜덤 값 (1 ~ 10)
        customerId: randomIntBetween(1, 10),
        // concertOptionId 랜덤 값 (1 ~ 1000)
        concertOptionId: randomIntBetween(1, 1000),
        // seatOptionId 랜덤 값 (1 ~ 1000)
        seatOptionId: randomIntBetween(1, 1000)
    });

    // 요청 헤더 설정
    const headers = {
        'Content-Type': 'application/json',
    };

    // HTTP POST 요청 보내기
    const res = http.post(url, payload, { headers: headers });

    // 응답 검증
    check(res, {
        'is status 200': (r) => r.status === 200,
        'is response time < 200ms': (r) => r.timings.duration < 200,
    });

    // 요청 간 대기 시간
    sleep(1); // 1초 대기
}