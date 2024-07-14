package com.concert.reservation.infrastructure.reservation;

import com.concert.reservation.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    @Modifying
    @Query(value = "UPDATE reservation" +
                   "   SET status = :status" +
                   " WHERE reservation_id = :reservation_id", nativeQuery = true)
    void updateStatus(@Param("reservation_id") Long reservationId, @Param("status") String status);
    Optional<Reservation> findBySeatOptionIdAndCustomerId(Long seatOptionId, Long customerId);
}