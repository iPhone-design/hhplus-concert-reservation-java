package com.concert.reservation.infrastructure.spring.reservation;

import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import com.concert.reservation.domain.reservation.event.ReservationEvent;
import com.concert.reservation.domain.reservation.event.ReservationEventPublisher;
import com.concert.reservation.interfaces.event.ReservationEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationSpringEventPublisher implements ReservationEventPublisher {

    private final ReservationEventListener reservationEventListener;

    /**
     * 예약 성공 이벤트 발행
     *
     * @author  양종문
     * @since   2024-08-11
     */
    @Override
    public void success(ReservationEvent reservationEvent) {
        // 예약 Outbox 저장
        reservationEventListener.saveOutbox(ReservationOutBoxDomain.builder()
                                                                   .reservationId(reservationEvent.getReservationId())
                                                                   .status(ReservationOutboxStatus.INIT)
                                                                   .jsonData(reservationEvent.toJsonData())
                                                                   .build());
        
        // kafka 발행
        reservationEventListener.reservationSuccessHandler(reservationEvent);
    }
}
