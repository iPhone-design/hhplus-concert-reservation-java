package com.concert.reservation.application.payment;

import com.concert.reservation.domain.payment.PaymentDomain;
import org.springframework.stereotype.Component;

@Component
public interface PaymentRepository {
    PaymentDomain save(PaymentDomain paymentDomain);
}