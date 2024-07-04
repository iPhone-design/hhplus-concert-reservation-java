package com.concert.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "SEAT")
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;
    @Column(name = "status")
    private String status;
    @Column(name = "create_dt")
    private Timestamp createDt;
    @Column(name = "update_dt")
    private Timestamp updateDt;
}
