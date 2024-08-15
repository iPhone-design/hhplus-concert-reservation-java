package com.concert.reservation.interfaces.scheduler;

import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import com.concert.reservation.domain.reservation.ReservationOutboxWriter;
import com.concert.reservation.domain.reservation.event.ReservationEvent;
import com.concert.reservation.interfaces.event.ReservationEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class OutboxScheduler {

    private final ReservationOutboxWriter reservationOutboxWriter;
    private final ReservationEventListener reservationEventListener;

    /**
     * 예약 Outbox 재발행 스케줄러
     *
     * @author  양종문
     * @since   2024-08-16
     */
    @Scheduled(fixedRate = 5000) // 5초마다 실행
    public void reservationOutboxReissueToKafka() {
        LocalDateTime startDt = LocalDateTime.now();
        log.info("Start outboxDataReissueToKafka");

        // 예약 아웃 박스 조회
        List<ReservationOutBoxDomain> reservationOutBoxDomains = reservationOutboxWriter.findAllByStatus(ReservationOutboxStatus.INIT);
        for (ReservationOutBoxDomain reservationOutBoxDomain : reservationOutBoxDomains) {
            // 생성 시간이 5분이 지나지 않은 데이터들은 Kafka로 재발행 해준다.
            if (reservationOutBoxDomain.getCreateDt().isBefore(LocalDateTime.now().minusMinutes(5))) {
                log.info("kafka 재발행 : {}", reservationOutBoxDomain.getJsonData());
                reservationEventListener.reservationSuccessHandler(ReservationEvent.builder().build().toReservationEvent(reservationOutBoxDomain.getJsonData()));
            }
        }

        LocalDateTime endDt = LocalDateTime.now();
        log.info("소요 시간 (밀리초) : {}", ChronoUnit.MILLIS.between(startDt, endDt));
        log.info("End outboxDataReissueToKafka");
    }
}
