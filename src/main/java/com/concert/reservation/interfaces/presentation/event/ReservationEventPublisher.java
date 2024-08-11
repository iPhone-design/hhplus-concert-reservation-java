package com.concert.reservation.interfaces.presentation.event;

import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.interfaces.externalApi.ExternalReservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class ReservationEventPublisher {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void reservationSuccessHandler(ReservationEvent reservationEvent){
        try{
            ExternalReservation externalReservation = new ExternalReservation();
            externalReservation.sendReservationResult(reservationEvent);
        }
        catch (CustomException e) {
            log.error("예약 결과 전송 처리에 실패했습니다.");
        }
        catch(Exception e){
            log.error("ERROR : {}", e.getMessage());
        }
    }
}
