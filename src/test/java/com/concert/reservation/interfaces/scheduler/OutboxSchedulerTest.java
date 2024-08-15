package com.concert.reservation.interfaces.scheduler;

import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import com.concert.reservation.domain.reservation.ReservationOutboxWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class OutboxSchedulerTest {

    @Autowired
    OutboxScheduler outboxScheduler;

    @Autowired
    ReservationOutboxWriter reservationOutboxWriter;

    @Test
    @DisplayName("Outbox 재발행")
    void reservationOutboxReissueToKafka() throws InterruptedException {
        // given
        long reservationId = new Random().nextLong(1000L);
        LocalDateTime createDt = LocalDateTime.now().minusMinutes(10);
        String jsonData = "{\"reservationId\":" + reservationId + ",\"concertOptionId\":100000,\"seatOptionId\":106,\"customerId\":1,\"status\":\"INCOMPLETE\",\"reservationDt\":[2024,8,16,5,8,54,347392000]}";
        ReservationOutBoxDomain reservationOutBoxDomainForSave = ReservationOutBoxDomain.builder().reservationId(reservationId).jsonData(jsonData).createDt(createDt).status(ReservationOutboxStatus.INIT).build();
        log.info("Outbox table 저장 : {}", reservationOutBoxDomainForSave);
        reservationOutboxWriter.save(reservationOutBoxDomainForSave);

        // 스케줄러 동작을 위해 10초간 멈춰준다.
        Thread.sleep(10000);

        // when-then
        Optional<ReservationOutBoxDomain> reservationOutBoxDomain = reservationOutboxWriter.findByReservationIdAndStatus(reservationId, ReservationOutboxStatus.PUBLISHED);
        assertThat(reservationOutBoxDomain).isPresent();
        assertThat(reservationOutBoxDomain.get().getStatus()).isEqualTo(ReservationOutboxStatus.PUBLISHED);
    }
}