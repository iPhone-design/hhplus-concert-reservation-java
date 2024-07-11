package com.concert.reservation.infrastructure.seat;

import com.concert.reservation.infrastructure.entity.SeatOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeatOptionJpaRepository extends JpaRepository<SeatOption, Long> {
    @Query(value = "SELECT so.*" +
                   "  FROM seat_option so INNER JOIN concert_option co ON so.concert_option_id = co.concert_option_id " +
                   " WHERE so.seat_option_id = :seat_option_id" +
                   "   AND so.concert_option_id = :concert_option_id" +
                   "   AND co.open_dt BETWEEN :start_dt AND :end_dt", nativeQuery = true)
    SeatOption findSeat(@Param("seat_option_id") Long seatOptionId, @Param("concert_option_id") Long concertOptionId, @Param("start_dt") LocalDateTime start_dt, @Param("end_dt") LocalDateTime end_dt);

    @Query(value = "SELECT so.*" +
                    " FROM seat_option so INNER JOIN concert_option co ON so.concert_option_id = co.concert_option_id " +
                    "WHERE so.status = 'AVAILABLE'" +
                    "  AND so.concert_option_id = :concert_option_id" +
                    "  AND co.open_dt BETWEEN :start_dt AND :end_dt", nativeQuery = true)
    List<SeatOption> findAllAvailableSeatForReservation(@Param("concert_option_id") Long concertOptionId, @Param("start_dt") LocalDateTime startDt, @Param("end_dt") LocalDateTime endDt);

    @Modifying
    @Query(value = "UPDATE seat_option" +
                   "   SET status = :status" +
                   " WHERE seat_option_id = :seat_option_id" +
                   "   AND concert_option_id = :concert_option_id", nativeQuery = true)
    void updateStatus(@Param("seat_option_id") Long seatOptionId, @Param("concert_option_id") Long concertOptionId, @Param("status") String status);
}