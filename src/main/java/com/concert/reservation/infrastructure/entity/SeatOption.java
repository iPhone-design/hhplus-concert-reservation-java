package com.concert.reservation.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT_OPTION")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_option_id")
    private Long seatOptionId;
    @Column(name = "seat_id")
    private Long seatId;
    @Column(name = "concert_option_id")
    private Long concertOptionId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "status")
    private String status;
}
