package com.concert.reservation.domain.payment.entity;

import com.concert.reservation.domain.payment.PaymentDomain;
import com.concert.reservation.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "reservation_id")
    private Long reservationId;
    @Column(name = "amount")
    private Long amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;
    @CreationTimestamp
    @Column(name = "payment_dt")
    private LocalDateTime paymentDt;

    public PaymentDomain toDomain() {
        return PaymentDomain.builder()
                            .paymentId(this.paymentId)
                            .reservationId(this.reservationId)
                            .amount(this.amount)
                            .status(this.status)
                            .paymentDt(this.paymentDt)
                            .build();
    }

    public static Payment toEntity(PaymentDomain paymentDomain) {
        return Payment.builder()
                      .paymentId(paymentDomain.getPaymentId())
                      .reservationId(paymentDomain.getReservationId())
                      .amount(paymentDomain.getAmount())
                      .status(paymentDomain.getStatus())
                      .paymentDt(paymentDomain.getPaymentDt())
                      .build();
    }
}
