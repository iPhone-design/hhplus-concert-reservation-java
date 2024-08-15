package com.concert.reservation.domain.reservation;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ReservationOutboxWriter {
    Optional<ReservationOutBoxDomain> findByReservationIdAndStatus(Long reservationId, ReservationOutboxStatus status);
    List<ReservationOutBoxDomain> findAllByStatus(String status);
    void save(ReservationOutBoxDomain reservationOutboxDomain);
}