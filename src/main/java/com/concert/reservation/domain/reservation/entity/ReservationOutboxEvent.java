package com.concert.reservation.domain.reservation.entity;

import com.concert.reservation.domain.reservation.ReservationOutBoxDomain;
import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION_OUTBOX_EVENT", indexes = {@Index(name = "index_outbox_event_status", columnList = "status")})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationOutboxEvent {
    @Id
    @Column(name = "reservation_outbox_event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationOutboxEventId;
    @Column(name = "reservation_id")
    private Long reservationId;
    @Column(name = "json_data")
    private String jsonData;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationOutboxStatus status;
    @CreationTimestamp
    @Column(name = "create_dt")
    LocalDateTime createDt;

    public static ReservationOutboxEvent toEntity(ReservationOutBoxDomain reservationOutBoxDomain) {
        return ReservationOutboxEvent.builder()
                                     .reservationOutboxEventId(reservationOutBoxDomain.getReservationOutboxEventId())
                                     .reservationId(reservationOutBoxDomain.getReservationId())
                                     .jsonData(reservationOutBoxDomain.getJsonData())
                                     .status(reservationOutBoxDomain.getStatus())
                                     .createDt(reservationOutBoxDomain.getCreateDt())
                                     .build();
    }

    public ReservationOutBoxDomain toDomain() {
        return ReservationOutBoxDomain.builder()
                                      .reservationOutboxEventId(this.reservationOutboxEventId)
                                      .reservationId(this.reservationId)
                                      .jsonData(this.jsonData)
                                      .status(this.status)
                                      .createDt(this.createDt)
                                      .build();
    }
}