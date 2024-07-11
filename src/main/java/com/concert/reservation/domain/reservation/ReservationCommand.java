package com.concert.reservation.domain.reservation;

import com.concert.reservation.infrastructure.entity.Reservation;
import com.concert.reservation.presentation.reservation.ReservationRequest;
import com.concert.reservation.presentation.reservation.ReservationResponse;

public class ReservationCommand {
    public static ReservationDomain toDomain (ReservationRequest reservationRequest) {
        return ReservationDomain.builder()
                .customerId(reservationRequest.getCustomerId())
                .seatOptionId(reservationRequest.getSeatOptionId())
                .concertOptionId(reservationRequest.getConcertOptionId())
                .date(reservationRequest.getDate())
                .build();
    }

    public static ReservationDomain toDomain (Reservation reservation) {
        return ReservationDomain.builder()
                .reservationId(reservation.getReservationId())
                .seatOptionId(reservation.getSeatOptionId())
                .customerId(reservation.getCustomerId())
                .status(reservation.getStatus())
                .reservationDt(reservation.getReservationDt())
                .build();
    }

    public static Reservation toEntity (ReservationDomain reservationDomain) {
        return Reservation.builder()
                .reservationId(reservationDomain.getReservationId())
                .seatOptionId(reservationDomain.getSeatOptionId())
                .customerId(reservationDomain.getCustomerId())
                .status(reservationDomain.getStatus())
                .reservationDt(reservationDomain.getReservationDt())
                .build();
    }

    public static ReservationResponse toResponse (ReservationDomain reservationDomain) {
        return ReservationResponse.builder()
                .reservationId(reservationDomain.getReservationId())
                .seatOptionId(reservationDomain.getSeatOptionId())
                .customerId(reservationDomain.getCustomerId())
                .status(reservationDomain.getStatus())
                .reservationDt(reservationDomain.getReservationDt())
                .build();
    }
}
