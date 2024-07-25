package com.concert.reservation.infrastructure.reservation;

import com.concert.reservation.domain.reservation.entity.Reservation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByReservationId(Long reservationId);
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Reservation> findByConcertOptionIdAndSeatOptionIdAndCustomerId(Long concertOptionId, Long seatOptionId, Long customerId);

    // 특정 고객의 미완료 상태의 예약 조회
    @Query(value = """
                    SELECT reservation_id
                         , concert_option_id
                         , seat_option_id
                         , customer_id
                         , status
                         , reservation_dt
                      FROM reservation
                     WHERE status = 'INCOMPLETE'
                       AND customer_id = :customer_id
                       AND reservation_dt > :reservation_dt
                    """, nativeQuery = true)
    List<Reservation> findAllIncompleteReservationsByCustomerIdAndReservationDt(@Param("customer_id") Long customerId, @Param("reservation_dt") LocalDateTime reservationDt);
}