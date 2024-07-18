package com.concert.reservation.presentation.payment;

import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private Long reservationId;
    private Long amount;
    private PaymentStatus status;
    private LocalDateTime paymentDt;

    public static PaymentResponse toResponse(PaymentDomain paymentDomain) {
        return PaymentResponse.builder()
                              .paymentId(paymentDomain.getPaymentId())
                              .reservationId(paymentDomain.getReservationId())
                              .amount(paymentDomain.getAmount())
                              .status(paymentDomain.getStatus())
                              .paymentDt(paymentDomain.getPaymentDt())
                              .build();
    }
}

