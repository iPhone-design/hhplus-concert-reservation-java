package com.concert.reservation.domain.payment;

import com.concert.reservation.infrastructure.entity.Payment;
import com.concert.reservation.presentation.payment.PaymentResponse;

public class PaymentCommand {
    public static PaymentDomain toDomain (Payment payment) {
        return PaymentDomain.builder()
                .paymentId(payment.getPaymentId())
                .reservationId(payment.getReservationId())
                .amount(payment.getAmount())
                .paymentDt(payment.getPaymentDt())
                .build();
    }

    public static PaymentResponse toResponse (PaymentDomain paymentDomain) {
        return PaymentResponse.builder()
                .paymentId(paymentDomain.getPaymentId())
                .reservationId(paymentDomain.getReservationId())
                .amount(paymentDomain.getAmount())
                .paymentDt(paymentDomain.getPaymentDt())
                .build();
    }

    public static Payment toEntity (PaymentDomain paymentDomain) {
        return Payment.builder()
                .paymentId(paymentDomain.getPaymentId())
                .reservationId(paymentDomain.getReservationId())
                .amount(paymentDomain.getAmount())
                .paymentDt(paymentDomain.getPaymentDt())
                .build();
    }

}
