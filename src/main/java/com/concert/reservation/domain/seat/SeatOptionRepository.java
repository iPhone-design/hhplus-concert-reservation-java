package com.concert.reservation.domain.seat;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface SeatOptionRepository {
    Optional<SeatOptionDomain> findSeat(Long seatOptionId, Long concertOptionId, LocalDateTime startDt, LocalDateTime endDt);
    List<SeatOptionDomain> findAllAvailableSeatForReservation(Long concertOptionId, LocalDateTime startDt, LocalDateTime endDt);
    SeatOptionDomain save(SeatOptionDomain seatOptionDomain);
}