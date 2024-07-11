package com.concert.reservation.application.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import org.springframework.stereotype.Component;

@Component
public interface ReservationRepository {
    ReservationDomain findBySeatOptionIdAndCustomerId(Long seatOptionId, Long customerId);
    ReservationDomain save(ReservationDomain reservationDomain);
    void modifyStatus(Long reservationId, String status);
}