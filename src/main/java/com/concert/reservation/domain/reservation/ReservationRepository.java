package com.concert.reservation.domain.reservation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface ReservationRepository {
    ReservationDomain findByConcertOptionIdAndSeatOptionIdAndCustomerId(Long concertOptionId, Long seatOptionId, Long customerId);
    List<ReservationDomain> findAllIncompleteReservationsByCustomerIdAndReservationDt(Long customerId, LocalDateTime reservationDt);
    ReservationDomain save(ReservationDomain reservationDomain);
}