package com.concert.reservation.interfaces.externalApi;

import com.concert.reservation.interfaces.presentation.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExternalPayment {
    public void sendPaymentResult(PaymentEvent paymentEvent) {
        log.info("ExternalPayment sendPaymentResult");
    }
}
