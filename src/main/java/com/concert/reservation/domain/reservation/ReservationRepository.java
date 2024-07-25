package com.concert.reservation.domain.reservation;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface ReservationRepository {
    ReservationDomain findByReservationId(Long reservationId);
    ReservationDomain findByConcertOptionIdAndSeatOptionIdAndCustomerId(Long concertOptionId, Long seatOptionId, Long customerId);
    Optional<ReservationDomain> findByConcertOptionIdAndSeatOptionIdAndCustomerIdNotException(Long concertOptionId, Long seatOptionId, Long customerId);
    List<ReservationDomain> findAllIncompleteReservationsByCustomerIdAndReservationDt(Long customerId, LocalDateTime reservationDt);
    ReservationDomain save(ReservationDomain reservationDomain);
}