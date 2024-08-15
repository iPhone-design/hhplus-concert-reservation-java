package com.concert.reservation.domain.reservation;

import org.springframework.stereotype.Component;

@Component
public interface ReservationKafkaProducer {
    public void send(String jsonData);
}