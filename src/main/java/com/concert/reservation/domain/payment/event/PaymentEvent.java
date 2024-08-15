package com.concert.reservation.domain.payment.event;


import com.concert.reservation.domain.payment.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private Long paymentId;
    private Long reservationId;
    private Long amount;
    private PaymentStatus status;
    private LocalDateTime paymentDt;
}
