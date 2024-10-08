package com.concert.reservation.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "CONCERT_OPTION")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_option_id")
    private Long concertOptionId;
    @Column(name = "concert_id")
    private Long concertId;
    @Column(name = "concert_name")
    private String concertName;
    @Column(name = "location")
    private String location;
    @Column(name = "open_dt")
    private Timestamp openDt;
}
