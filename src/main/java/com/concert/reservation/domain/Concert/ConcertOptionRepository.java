package com.concert.reservation.domain.concert;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface ConcertOptionRepository {
    List<ConcertOptionDomain> findAllAvailableConcertForReservation(LocalDateTime currentDt);
}