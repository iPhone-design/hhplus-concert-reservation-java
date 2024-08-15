package com.concert.reservation.interfaces.externalApi;

import com.concert.reservation.domain.reservation.event.ReservationEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExternalReservation {
    public void sendReservationResult(ReservationEvent reservationEvent){
        log.info("ExternalReservation sendReservationResult");
    }
}
