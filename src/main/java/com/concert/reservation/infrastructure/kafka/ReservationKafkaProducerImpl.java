package com.concert.reservation.infrastructure.kafka;

import com.concert.reservation.domain.reservation.ReservationKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationKafkaProducerImpl implements ReservationKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Kafka 예약 메시지 발행
     *
     * @author 양종문
     * @since  2024-08-15
     * @param  jsonData - json data
     */
    @Override
    public void send(String jsonData) {
        kafkaTemplate.send("topic", jsonData);
    }
}