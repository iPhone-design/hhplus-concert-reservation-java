package com.concert.reservation.interfaces.event;

import com.concert.reservation.domain.reservation.ReservationKafkaProducer;
import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxWriter;
import com.concert.reservation.domain.reservation.event.ReservationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReservationEventListener {
    private final ReservationKafkaProducer reservationKafkaProducer;
    private final ReservationOutboxWriter reservationOutboxWriter;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void reservationSuccessHandler(ReservationEvent reservationEvent) {
        reservationKafkaProducer.send(reservationEvent.toJsonData());
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void saveOutbox(ReservationOutBoxDomain reservationOutBoxDomain) {
        reservationOutboxWriter.save(reservationOutBoxDomain);
    }
}