package com.concert.reservation.domain.reservation;

import com.concert.reservation.domain.reservation.entity.ReservationOutboxEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationOutBoxDomain {
    private Long reservationOutboxEventId;
    private Long reservationId;
    private String jsonData;
    private ReservationOutboxStatus status;
    LocalDateTime createDt;

    public void changeStatusToPublished() {
        this.status = ReservationOutboxStatus.PUBLISHED;
    }

    public static ReservationOutboxEvent toEntity(ReservationOutBoxDomain reservationOutBoxDomain) {
        return ReservationOutboxEvent.builder()
                                     .reservationOutboxEventId(reservationOutBoxDomain.getReservationOutboxEventId())
                                     .reservationId(reservationOutBoxDomain.getReservationId())
                                     .jsonData(reservationOutBoxDomain.getJsonData())
                                     .status(reservationOutBoxDomain.getStatus())
                                     .createDt(reservationOutBoxDomain.getCreateDt())
                                     .build();
    }
}