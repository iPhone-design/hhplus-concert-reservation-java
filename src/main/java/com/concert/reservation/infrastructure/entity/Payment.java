package com.concert.reservation.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
    @Column(name = "payment_dt")
    private Timestamp paymentDt;
}
