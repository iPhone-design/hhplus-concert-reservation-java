package com.concert.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "PAYMENT")
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "status")
    private String status;
    @Column(name = "create_dt")
    private Timestamp createDt;
}
