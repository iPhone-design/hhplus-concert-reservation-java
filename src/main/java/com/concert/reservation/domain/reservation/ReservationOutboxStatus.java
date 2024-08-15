package com.concert.reservation.domain.reservation;

public enum ReservationOutboxStatus {
    INIT,           // 발행 초기
    PUBLISHED       // 발행 완료
}