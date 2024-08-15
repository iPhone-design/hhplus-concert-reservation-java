package com.concert.reservation.domain.reservation.event;

import org.springframework.stereotype.Component;

@Component
public interface ReservationEventPublisher {
    void success(ReservationEvent reservationEvent);
}
