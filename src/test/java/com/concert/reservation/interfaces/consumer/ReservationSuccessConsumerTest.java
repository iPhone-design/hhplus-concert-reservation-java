package com.concert.reservation.interfaces.consumer;

import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import com.concert.reservation.domain.reservation.ReservationOutboxWriter;
import com.concert.reservation.domain.reservation.ReservationStatus;
import com.concert.reservation.domain.reservation.event.ReservationEvent;
import com.concert.reservation.domain.reservation.event.ReservationEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ReservationSuccessConsumerTest {

    @Autowired
    ReservationEventPublisher reservationEventPublisher;

    @Autowired
    ReservationOutboxWriter reservationOutboxWriter;

    @Test
    @DisplayName("Kafka 메시지 발행 및 소비 테스트")
    void consumer() throws InterruptedException {
        // given
        Long reservationId = 16L;
        Long concertOptionId = 100000L;
        Long seatOptionId = 109L;
        Long customerId = 1L;
        ReservationEvent reservationEvent = ReservationEvent.builder()
                                                            .reservationId(reservationId)
                                                            .concertOptionId(concertOptionId)
                                                            .seatOptionId(seatOptionId)
                                                            .customerId(customerId)
                                                            .status(ReservationStatus.INCOMPLETE)
                                                            .reservationDt(LocalDateTime.now())
                                                            .build();

        // when
        // kafka 메시지 발행
        reservationEventPublisher.success(reservationEvent);

        // 예약 Outbox table 조회
        Optional<ReservationOutBoxDomain> reservationOutBoxDomain1 = reservationOutboxWriter.findByReservationIdAndStatus(reservationId, ReservationOutboxStatus.INIT);

        // Consumer 동작을 위해 5초간 멈춰준다.
        Thread.sleep(5000);
        
        // 예약 Outbox table 조회
        Optional<ReservationOutBoxDomain> reservationOutBoxDomain2 = reservationOutboxWriter.findByReservationIdAndStatus(reservationId, ReservationOutboxStatus.PUBLISHED);

        log.info(reservationOutBoxDomain1.get().toString());
        log.info(reservationOutBoxDomain2.get().toString());

        // then
        // Consumer 전
        assertThat(reservationOutBoxDomain1.get().getReservationId()).isEqualTo(reservationId);
        assertThat(reservationOutBoxDomain1.get().getStatus()).isEqualTo(ReservationOutboxStatus.INIT);

        // Consumer 후
        assertThat(reservationOutBoxDomain2.get().getReservationId()).isEqualTo(reservationId);
        assertThat(reservationOutBoxDomain2.get().getStatus()).isEqualTo(ReservationOutboxStatus.PUBLISHED);
    }
}