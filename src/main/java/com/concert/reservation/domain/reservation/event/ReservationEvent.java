package com.concert.reservation.domain.reservation.event;

import com.concert.reservation.domain.reservation.ReservationStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEvent {
    private Long reservationId;
    private Long concertOptionId;
    private Long seatOptionId;
    private Long customerId;
    private ReservationStatus status;
    private LocalDateTime reservationDt;

    public String toJsonData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(ReservationEvent.builder()
                                                                   .reservationId(this.reservationId)
                                                                   .concertOptionId(this.concertOptionId)
                                                                   .seatOptionId(this.seatOptionId)
                                                                   .customerId(this.customerId)
                                                                   .status(this.status)
                                                                   .reservationDt(this.reservationDt)
                                                                   .build());
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
