package com.concert.reservation.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDomain {
    private Long paymentId;
    private Long reservationId;
    private Long amount;
    private PaymentStatus status;
    private LocalDateTime paymentDt;

    private Long customerId;
    private Long concertOptionId;
    private Long seatOptionId;
}
