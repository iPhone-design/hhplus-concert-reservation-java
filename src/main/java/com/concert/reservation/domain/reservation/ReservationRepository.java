package com.concert.reservation.domain.reservation;

import org.springframework.stereotype.Component;

@Component
public interface ReservationRepository {
    ReservationDomain findByConcertOptionIdAndSeatOptionIdAndCustomerId(Long concertOptionId, Long seatOptionId, Long customerId);
    ReservationDomain save(ReservationDomain reservationDomain);
}