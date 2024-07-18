package com.concert.reservation.domain.payment;

import org.springframework.stereotype.Component;

@Component
public interface PaymentRepository {
    PaymentDomain save(PaymentDomain paymentDomain);
}