package com.concert.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "CONCERT_OPTION")
@Getter
@Setter
@NoArgsConstructor
public class ConcertOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_option")
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
