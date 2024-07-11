package com.concert.reservation.application.concert;

import com.concert.reservation.domain.Concert.ConcertOptionDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface ConcertOptionRepository {
    List<ConcertOptionDomain> findAllAvailableConcertForReservation(LocalDateTime currentDt);
}