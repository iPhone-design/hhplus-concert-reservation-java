package com.concert.reservation.interfaces.event;

import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.domain.payment.event.PaymentEvent;
import com.concert.reservation.interfaces.externalApi.ExternalPayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class PaymentEventListener {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void paymentSuccessHandler(PaymentEvent paymentEvent){
        try{
            ExternalPayment externalPayment = new ExternalPayment();
            externalPayment.sendPaymentResult(paymentEvent);
        }
        catch (CustomException e) {
            log.error("결제 결과 전송 처리에 실패했습니다.");
        }
        catch(Exception e){
            log.error("ERROR : {}", e.getMessage());
        }
    }
}

