package com.concert.reservation.infrastructure.concert;

import com.concert.reservation.domain.concert.entity.ConcertOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConcertOptionJpaRepository extends JpaRepository<ConcertOption, Long> {
    @Query("SELECT co FROM ConcertOption co WHERE co.openDt > :currentDt")
    List<ConcertOption> findAllAvailableConcertForReservation(@Param("currentDt") LocalDateTime currentDt);
}