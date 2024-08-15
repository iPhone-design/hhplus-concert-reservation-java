package com.concert.reservation.interfaces.consumer;

import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import com.concert.reservation.domain.reservation.ReservationOutboxWriter;
import com.concert.reservation.domain.reservation.event.ReservationEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReservationSuccessConsumer {

    private final ReservationOutboxWriter reservationOutboxWriter;

    /**
     * Kafka consumer
     * Kafka 메시지 수신 후 예약 아웃박스 데이터 상태를 발행 완료로 변경해 준다.
     *
     * @author 양종문
     * @since  2024-08-15
     * @param  consumerRecord - 컨슈머 레코드
     */
    @KafkaListener(topics = "topic", groupId = "reservation")
    public void consumer(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ReservationEvent reservationEvent = objectMapper.readValue(consumerRecord.value(), ReservationEvent.class);

        log.info("Received message from Kafka reservation : {}", reservationEvent.toString());

        // 예약 아웃 박스 조회
        Optional<ReservationOutBoxDomain> result = reservationOutboxWriter.findByReservationIdAndStatus(reservationEvent.getReservationId(), ReservationOutboxStatus.INIT);
        if (result.isPresent()) {
            ReservationOutBoxDomain reservationOutBoxDomain = result.get();
            // 상태 값 변경
            reservationOutBoxDomain.changeStatusToPublished();
            // 저장
            reservationOutboxWriter.save(reservationOutBoxDomain);

            log.info("상태 값 변경 완료");
        }
    }
}