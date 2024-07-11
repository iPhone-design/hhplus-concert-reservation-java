package com.concert.reservation.application.seat;

import com.concert.reservation.domain.seat.SeatOptionDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface SeatOptionRepository {
    SeatOptionDomain findSeat(Long seatOptionId, Long concertOptionId, LocalDateTime startDt, LocalDateTime endDt);
    List<SeatOptionDomain> findAllAvailableSeatForReservation(Long concertOptionId, LocalDateTime startDt, LocalDateTime endDt);
    void modifyStatus(SeatOptionDomain seatOptionDomain);
}