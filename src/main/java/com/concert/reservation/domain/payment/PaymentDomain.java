package com.concert.reservation.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDomain {
    private Long paymentId;
    private Long reservationId;
    private Long amount;
    private Timestamp paymentDt;

    private Long customerId;
    private Long seatOptionId;
    private Long concertOptionId;
    private LocalDate date;
}
