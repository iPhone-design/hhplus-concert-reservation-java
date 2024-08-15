package com.concert.reservation.infrastructure.db.reservation;

import com.concert.reservation.domain.reservation.ReservationOutboxStatus;
import com.concert.reservation.domain.reservation.entity.ReservationOutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationOutboxJpaRepository extends JpaRepository<ReservationOutboxEvent, Long>  {
    Optional<ReservationOutboxEvent> findByReservationIdAndStatus(Long reservationId, ReservationOutboxStatus status);
    List<ReservationOutboxEvent> findAllByStatus(ReservationOutboxStatus status);
}