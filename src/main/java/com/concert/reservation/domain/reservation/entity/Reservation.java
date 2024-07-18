package com.concert.reservation.domain.reservation.entity;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    @Column(name = "concert_option_id")
    private Long concertOptionId;
    @Column(name = "seat_option_id")
    private Long seatOptionId;
    @Column(name = "customer_id")
    private Long customerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;
    @CreationTimestamp
    @Column(name = "reservation_dt")
    private LocalDateTime reservationDt;

    public static Reservation toEntity(ReservationDomain reservationDomain) {
        return Reservation.builder()
                          .reservationId(reservationDomain.getReservationId())
                          .concertOptionId(reservationDomain.getConcertOptionId())
                          .seatOptionId(reservationDomain.getSeatOptionId())
                          .customerId(reservationDomain.getCustomerId())
                          .status(reservationDomain.getStatus())
                          .reservationDt(reservationDomain.getReservationDt())
                          .build();
    }

    public ReservationDomain toDomain() {
        return ReservationDomain.builder()
                                .reservationId(this.reservationId)
                                .concertOptionId(this.concertOptionId)
                                .seatOptionId(this.seatOptionId)
                                .customerId(this.customerId)
                                .status(this.status)
                                .reservationDt(this.reservationDt)
                                .build();
    }
}
