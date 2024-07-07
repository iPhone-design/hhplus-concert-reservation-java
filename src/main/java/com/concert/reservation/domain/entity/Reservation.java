package com.concert.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "RESERVATION")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;
    @Column(name = "seat_option_id")
    private Long seatOptionId;
    @Column(name = "concert_option_id")
    private Integer concertOptionId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "status")
    private String status;
    @Column(name = "reservation_dt")
    private Timestamp reservationDt;
}
