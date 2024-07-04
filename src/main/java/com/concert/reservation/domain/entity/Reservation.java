package com.concert.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "RESERVATION")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "seat_id")
    private Integer seatId;
    @Column(name = "create_dt")
    private Timestamp createDt;
    @Column(name = "update_dt")
    private Timestamp updateDt;
}
