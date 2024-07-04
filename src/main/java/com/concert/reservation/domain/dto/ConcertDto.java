package com.concert.reservation.domain.dto;

import com.concert.reservation.domain.entity.Seat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class ConcertDto {
    private Long concertId;
    private String concertName;
    private String location;
    private Timestamp openDt;
    private List<Seat> seat;
}
